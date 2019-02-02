package maman16.students.udpclient;

import java.io.IOException;
import java.net.*;

public class UdpClientController {
    private String m_serverAddress;
    private int m_serverPort;
    private String m_studentName;
    private boolean m_isConnected;
    private String m_connectedStudents;
    private DatagramSocket m_socket;

    public UdpClientController(String serverAddress, int serverPort, String studentName) {
        m_serverAddress = serverAddress;
        m_serverPort = serverPort;
        m_studentName = studentName;
        m_isConnected = false;
    }

    public String getServerAddress() {
        return m_serverAddress;
    }

    public int getServerPort() {
        return m_serverPort;
    }

    public boolean isConnected() {
        return m_isConnected;
    }

    public String getStudentName() {
        return m_studentName;
    }

    public DatagramSocket getSocket() {
        return m_socket;
    }

    public synchronized void setConnectedStudents(String connectedStudents) {
        m_connectedStudents = connectedStudents;
    }

    public synchronized String getConnectedStudents() {
        return m_connectedStudents;
    }

    public void connect(IUdpMessageHandler handler) {
        byte[] buf = ("+"+m_studentName).getBytes();
        try {
            InetAddress address = InetAddress.getByName(m_serverAddress);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, m_serverPort);
            m_socket = new DatagramSocket();
            m_socket.send(packet);
            startListening(handler);
            m_isConnected = true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        byte[] buf = ("-"+m_studentName).getBytes();
        try {
            InetAddress address = InetAddress.getByName(m_serverAddress);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, m_serverPort);
            m_socket = new DatagramSocket();
            m_socket.send(packet);
            m_isConnected = false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startListening(IUdpMessageHandler handler) {
        UdpListener listener = new UdpListener(this, handler);
        listener.start();
    }
}
