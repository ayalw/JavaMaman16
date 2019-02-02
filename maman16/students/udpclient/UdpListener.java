package maman16.students.udpclient;

import java.io.IOException;
import java.net.*;

public class UdpListener extends Thread {

    private UdpClientController m_controller;
    private IUdpMessageHandler m_handler;
    private boolean m_isAlive;

    public UdpListener(UdpClientController controller, IUdpMessageHandler handler) {
        super();
        m_controller = controller;
        m_handler = handler;
        m_isAlive = false;
    }

    @Override
    public void run() {
        m_isAlive = true;
        while (m_isAlive) {
            try {
                DatagramSocket socket = m_controller.getSocket();
                byte[] buf = new byte[512];
                InetAddress address = InetAddress.getByName(m_controller.getServerAddress());
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, m_controller.getServerPort());
                socket.receive(packet);
                String connectedStudents = new String(buf);
                m_controller.setConnectedStudents(connectedStudents);
                m_handler.onUdpMessageArrived(connectedStudents);

            } catch (SocketException e) {
                e.printStackTrace();
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
