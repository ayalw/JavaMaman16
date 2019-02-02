package maman16.students.udpserver;

import maman16.students.udpcommon.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * UDP server implementation.
 */
public class StudentsUdpServer {

    /**
     * Mapping of known students and their connection status.
     */
    private Map<String, StudentConnectionStatus> m_studentConnectionStatus;

    /**
     * Server socket
     */
    private DatagramSocket m_socket;

    /**
     * Constructor
     * @param namesFile
     */
    public StudentsUdpServer(String namesFile) {
        m_studentConnectionStatus = new HashMap<>();
        URL url = getClass().getResource("StudentNames.txt");
        File file = new File(url.getPath());
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String line = input.nextLine();
                if (Utils.isNullOrWhitespace(line)) continue;
                String studentName = line;
                StudentConnectionStatus status = new StudentConnectionStatus(null, 0, false);
                m_studentConnectionStatus.put(studentName, status);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start work loop - listen for incoming client packets.
     * Upon incoming packet, locate the correct student map entry, update it and send response with list of all connections.
     */
    public void startRunning() {
        System.out.println("Starting UDP server...");
        try {
            m_socket = new DatagramSocket(4445);
            DatagramPacket packet;
            boolean moreclients = true;
            while (moreclients) {
                byte[] buf = new byte[256];
                //receive request
                packet = new DatagramPacket(buf, buf.length);
                m_socket.receive(packet);
                String message = new String(buf).split("\0")[0];
                char connectionFlag = message.charAt(0);
                String name = message.substring(1);
                if (!m_studentConnectionStatus.containsKey(name)) { // Unrecognized student - ignore!
                    continue;
                }
                if (connectionFlag == '+') { // Student connect message
                    m_studentConnectionStatus.get(name).setIsConnected(true);
                    m_studentConnectionStatus.get(name).setAddress(packet.getAddress());
                    m_studentConnectionStatus.get(name).setPort(packet.getPort());
                }
                if (connectionFlag == '-') { // Student disconnect message
                    m_studentConnectionStatus.get(name).setIsConnected(false);
                }

                // Create string with all connected student names
                StringBuilder response = new StringBuilder("Connected students: ");
                for (Map.Entry<String, StudentConnectionStatus> cursor : m_studentConnectionStatus.entrySet()) {
                    if (cursor.getValue().getIsConnected() == true) {
                        response.append(cursor.getKey() + ", ");
                    }
                }
                byte[] responseBuf = response.toString().getBytes();

                // Send the string to each of the connected students
                for (Map.Entry<String, StudentConnectionStatus> cursor : m_studentConnectionStatus.entrySet()) {
                    if (cursor.getValue().getIsConnected() == true) {
                        DatagramPacket responsePacket = new DatagramPacket(responseBuf,
                                responseBuf.length,
                                cursor.getValue().getAddress(),
                                cursor.getValue().getPort());
                        m_socket.send(responsePacket);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
