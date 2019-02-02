package maman16.students.udpserver;

import java.net.InetAddress;

/**
 * Data structure to maintain connection status (history of last one connection)
 */
public class StudentConnectionStatus {

    /**
     * Client address
     */
    private InetAddress m_address;

    /**
     * Client port
     */
    private int m_port;

    /**
     * Is currently connected
     */
    private boolean m_isConnected;

    /**
     * Constructor
     * @param address
     * @param port
     * @param isConnected
     */
    public StudentConnectionStatus(InetAddress address, int port, boolean isConnected) {
        m_address = address;
        m_port = port;
        m_isConnected = isConnected;
    }

    public InetAddress getAddress() {
        return m_address;
    }
    public int getPort() {
        return m_port;
    }
    public boolean getIsConnected() {
        return m_isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        m_isConnected = isConnected;
    }

    public void setAddress(InetAddress address) {
        m_address = address;
    }

    public void setPort(int port) {
        m_port = port;
    }
}
