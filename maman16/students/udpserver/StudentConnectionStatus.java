package maman16.students.udpserver;

import java.net.InetAddress;

public class StudentConnectionStatus {

    private InetAddress m_address;
    private int m_port;
    private boolean m_isConnected;

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
}
