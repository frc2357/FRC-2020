package com.systemmeltdown.robot.utils;

import edu.wpi.first.wpilibj.SerialPort;

public class RangefinderVL6180X {
    public enum Status {
        
        ERROR_NONE(0),   ///< Success!
        ERROR_SYSERR_1(1),   ///< System error
        ERROR_SYSERR_5(5),   ///< Sysem error
        ERROR_ECEFAIL(6),   ///< Early convergence estimate fail
        ERROR_NOCONVERGE(7),   ///< No target detected
        ERROR_RANGEIGNORE(8),   ///< Ignore threshold check failed
        ERROR_SNR(11),  ///< Ambient conditions too high
        ERROR_RAWUFLOW(12),  ///< Raw range algo underflow
        ERROR_RAWOFLOW(13),  ///< Raw range algo overflow
        ERROR_RANGEUFLOW(14),  ///< Raw range algo underflow
        ERROR_RANGEOFLOW(15),  ///< Raw range algo overflow
        ERROR_UNKNOWN(16);

        private int m_status;

        private Status(int status) {
            m_status = status;
        }

        public static Status fromCode(int code) {
            switch(code) {
            case 0:
                return ERROR_NONE;
            case 1:
                return ERROR_SYSERR_1;
            case 5:
                return ERROR_SYSERR_5;
            case 6:
                return ERROR_ECEFAIL;
            case 7:
                return ERROR_NOCONVERGE;
            case 8:
                return ERROR_RANGEIGNORE;
            case 11:
                return ERROR_SNR;
            case 12:
                return ERROR_RAWUFLOW;
            case 13:
                return ERROR_RAWOFLOW;
            case 14:
                return ERROR_RANGEUFLOW;
            case 15:
                return ERROR_RANGEOFLOW;
            default:
                return ERROR_UNKNOWN;
            }
        }

        public int getStatusCode() {
            return m_status;
        }
    }

    private SerialPort m_serialPort;
    private byte m_lastValue;
    private Status m_lastStatus;

    public RangefinderVL6180X(SerialPort.Port devicePort) {
        m_serialPort = new SerialPort(9600, devicePort);
        m_serialPort.setTimeout(0);
        m_serialPort.setReadBufferSize(1);
        m_serialPort.disableTermination();
        m_lastValue = 0;
        m_lastStatus = Status.ERROR_UNKNOWN;
    }

    /** Read a value from the device. Return true if a value was read */
    public boolean update() {
        byte[] buf = m_serialPort.read(1);
        if(buf.length > 0) {
            m_lastValue = buf[0];
            m_lastStatus = Status.fromCode(m_lastValue & 0x0F);
            return true;
        }

        return false;
    }

    public Status getLastStatus() {
        return m_lastStatus;
    }

    /** Get the range in mm */
    public byte getLastRange() {
        return m_lastValue;
    }

    /** Clear any stale data in the receive buffer */
    public void flush() {
        m_serialPort.reset();
    }
}