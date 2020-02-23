package com.systemmeltdown.robot.util;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * A web server that serves log files and the log viewer files. Add to smart dashboard
 * for an enable button.
 */
public class LogServer implements Sendable {
    private static final int READ_SIZE = 8192;
    private int m_port = 8000;
    private HttpServer m_server;
    private Path m_appPath;
    private Path m_logPath;

    public LogServer(String appPath, String logPath) {
        SendableRegistry.setName(this, getClass().getSimpleName());
        m_appPath = Paths.get(appPath);
        m_logPath = Paths.get(logPath);
    }

    public int getPort() {
        return m_port;
    }

    public void setPort(int port) {
        if(m_server != null) {
            if(m_server.getAddress().getPort() != port) {
                stop();
                m_port = port;
                start();
            }
        } else {
            m_port = port;
        }
    }

    public boolean isServerActive() {
        return m_server != null;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType(BuiltInWidgets.kToggleSwitch.getWidgetName());
        builder.addBooleanProperty("enable",
            this::isServerActive,
            enable -> {
                if(isServerActive() && !enable) {
                    stop();
                } else if(!isServerActive() && enable) {
                    start();
                }
            });
        builder.addDoubleProperty("port", this::getPort, port -> setPort((int)port));
    }

    private void start() {
        try {
            // the server listens on all IPs and has no connection backlog
            m_server = HttpServer.create(new InetSocketAddress(m_port), 0);
            m_server.createContext("/chart/", this::serveApp);
            m_server.createContext("/log/", this::serveLogs);
            m_server.createContext("/", this::listLogs);
            m_server.start();
            System.out.println("Server started");
        } catch(IOException exc) {
            m_server = null;
            System.out.println(exc.toString());
            // log error
        }
    }

    private void stop() {
        m_server.stop(0);
        m_server = null;
    }

    private void serveApp(HttpExchange exchange) {
        try {
            File file = getFilePath(exchange, m_appPath);
            respondFile(exchange, file);
        } catch(Exception exc) {
            respondError(exchange, exc);
        }
    }

    private void serveLogs(HttpExchange exchange) {
        try {
            File file = getFilePath(exchange, m_logPath);
            respondFile(exchange, file);
        } catch(Exception exc) {
            respondError(exchange, exc);
        }
    }

    /** Display the files in the log directory in an HTML table */
    private void listLogs(HttpExchange exchange) {
        try {
            String pageHtml = "<html><body><table>";

            File logDirectory = new File(m_logPath.toString());
            for(File file : logDirectory.listFiles()) {
                if(file.isFile()) {
                    String relative = m_logPath.relativize(file.toPath()).toString();

                    // Link to open viewer
                    String chartLink = String.format("<a href=\"/chart/index.html?log=/log/%s\">Chart</a>", relative);

                    // Link to get raw file
                    String rawLink = String.format("<a href=\"/log/%s\">Raw</a>", relative);

                    pageHtml += String.format(
                        "<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                        chartLink,
                        rawLink,
                        relative);
                }
            }

            pageHtml += "</table></body></html>";

            respondString(exchange, 200, pageHtml, "text/html");
        } catch(Exception exc) {
            respondError(exchange, exc);
        }
    }

    /**
     * Respond with a file, or 404 if it's not found. If the file is a zip, serve the first
     * entry instead.
     */
    private void respondFile(HttpExchange exchange, File file) throws IOException {
        if(!file.canRead()) {
            respondError(exchange, 404, "Not found");
        } else if(getExtension(file.toPath()).equals("zip")) {
            ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
            ZipEntry zipEntry = zip.getNextEntry();
            if(zipEntry == null) {
                respondError(exchange, 500, "Empty zip file");
            } else {
                // zipEntry.getSize returns -1, so we have to read the whole entry
                byte[] entryBytes = zip.readAllBytes();
                Headers headers = exchange.getResponseHeaders();
                headers.set("Content-Type", getContentType(Paths.get(zipEntry.getName())));
                exchange.sendResponseHeaders(200, entryBytes.length);

                OutputStream outStream = exchange.getResponseBody();
                outStream.write(entryBytes);
                outStream.close();
                zip.closeEntry();
            }
            zip.close();
        } else {
            Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", getContentType(file.toPath()));
            exchange.sendResponseHeaders(200, file.length());            

            OutputStream outStream = exchange.getResponseBody();
            FileInputStream inStream = new FileInputStream(file);
            copyStream(file.length(), outStream, inStream);
            outStream.close();
            inStream.close();
        }
    }

    /**
     * Copy inStream to outStream in READ_SIZE chunks
     * @param fileLength
     * @param outStream
     * @param inStream
     * @throws IOException
     */
    private void copyStream(long fileLength, OutputStream outStream, InputStream inStream) throws IOException {
        byte[] buf = new byte[READ_SIZE];
        long remainingBytes = fileLength;
        while(remainingBytes > buf.length) {
            inStream.read(buf);
            outStream.write(buf);
            remainingBytes -= buf.length;
        }
        if(remainingBytes > 0) {
            inStream.read(buf, 0, (int)remainingBytes);
            outStream.write(buf, 0, (int)remainingBytes);
        }
    }

    /** Respond with an error message */
    private void respondError(HttpExchange exchange, int code, String message) {
        try {
            respondString(exchange, code, message, "text/plain");
        } catch(IOException exc) {
            System.out.println("The error handler caught an exception: " + exc.toString());
        }
    }

    /** Respond with a server error code and stack trace */
    private void respondError(HttpExchange exchange, Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        respondError(exchange, 500, stringWriter.toString());
    }

    /** Respond with a string */
    private void respondString(HttpExchange exchange, int code, String message, String contentType) throws IOException {
        final byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", contentType + "; charset=utf-8");
        exchange.sendResponseHeaders(code, messageBytes.length);
        OutputStream out = exchange.getResponseBody();
        out.write(messageBytes);
        out.close();
    }

    /**
     * Convert the part of the URI after the handler context to a path relative to root
     * @param exchange
     * @param root
     * @return 
     */
    private File getFilePath(HttpExchange exchange, Path root) {
        Path requestContext = Paths.get(exchange.getHttpContext().getPath());
        URI uri = exchange.getRequestURI();

        // remove the context prefix
        Path relative = requestContext.relativize(Paths.get(uri.getPath()));

        // remove internal . and ..
        relative = relative.normalize();

        // security check
        if(relative.isAbsolute() || relative.startsWith("..")) {
            return null;
        }

        Path absolute = root.resolve(relative).toAbsolutePath();
        return new File(absolute.toString());
    }

    /**
     *  Get the HTTP content type from the file extension. Default is text/plain.
     */
    private String getContentType(Path filepath) {
        String extension = getExtension(filepath);

        switch(extension) {
        case "html":
            return "text/html";
        case "json":
            return "text/json";
        case "js":
            return "text/javascript";
        case "png":
            return "image/png";
        case "ico":
            return "image/x-icon";
        case "css":
            return "text/css";
        default:
            return "text/plain";
        }
    }

    private String getExtension(Path filepath) {
        String filename = filepath.getFileName().toString();

        // get extension
        int index = filename.lastIndexOf('.');
        String extension = "";
        if(index >= 0) {
            extension = filename.substring(index + 1).toLowerCase();
        }
        return extension;
    }
}