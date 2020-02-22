package com.systemmeltdown.robot.util;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        builder.setSmartDashboardType(BuiltInWidgets.kToggleButton.getWidgetName());
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
            m_server.createContext("/app/", this::serveApp);
            m_server.createContext("/log/", this::serveLogs);
            m_server.createContext("/", this::listLogs);
            m_server.start();
        } catch(IOException exc) {
            m_server = null;
            // log error
        }
    }

    private void stop() {
        m_server.stop(0);
        m_server = null;
    }

    private void serveApp(HttpExchange exchange) throws IOException {
        File file = getFilePath(exchange, m_appPath);
        respondFile(exchange, file);
    }

    private void serveLogs(HttpExchange exchange) throws IOException {
        File file = getFilePath(exchange, m_logPath);
        respondFile(exchange, file);
    }

    /** Display the files in the log directory in an HTML table */
    private void listLogs(HttpExchange exchange) throws IOException {
        String pageHtml = "<html><body><table>";

        File logDirectory = new File(m_logPath.toString());
        for(File file : logDirectory.listFiles()) {
            if(file.isFile()) {
                String relative = m_logPath.relativize(file.toPath()).toString();

                // Link to open viewer
                // >>> Change this <<<
                String chartLink = String.format("<a href=\"/app/index.html?log=%s\">Chart</a>", relative);

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
    }

    /** Respond with a file, or 404 if it's not found */
    private void respondFile(HttpExchange exchange, File file) throws IOException {
        if(!file.canRead()) {
            respondError(exchange, 404, "Not found");
        } else {
            Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", getContentType(file.toPath()));
            exchange.sendResponseHeaders(200, file.length());            

            // Copy the file in chunks of READ_SIZE bytes
            OutputStream outStream = exchange.getResponseBody();
            FileInputStream inStream = new FileInputStream(file);
            byte[] buf = new byte[READ_SIZE];
            long remainingBytes = file.length();
            while(remainingBytes > buf.length) {
                inStream.read(buf);
                outStream.write(buf);
                remainingBytes -= buf.length;
            }
            if(remainingBytes > 0) {
                inStream.read(buf, 0, (int)remainingBytes);
                outStream.write(buf, 0, (int)remainingBytes);
            }
            outStream.close();
            inStream.close();
        }
    }

    /** Respond with an error message */
    private void respondError(HttpExchange exchange, int code, String message) throws IOException {
        respondString(exchange, code, message, "text/plain");
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
        String filename = filepath.getFileName().toString();

        // get extension
        int index = filename.lastIndexOf('.');
        String extension = "";
        if(index >= 0) {
            extension = filename.substring(index + 1).toLowerCase();
        }

        switch(extension) {
        case "html":
            return "text/html";
        case "json":
            return "text/json";
        default:
            return "text/plain";
        }
    }
}