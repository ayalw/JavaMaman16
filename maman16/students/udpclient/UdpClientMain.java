package maman16.students.udpclient;

import javax.swing.*;

public class UdpClientMain {
    /**
     * Main class to run the GUI
     * @param args
     */
    public static void main(String[] args) {
        String serverAddress = null;
        int serverPort = 0;
        String studentName = null;
        if (args.length < 4) {
            System.out.println("Usage: UdpClientMain [Server Address] [Server Port] [First Name] [Last Name]");
            System.out.println("Example: UdpClientMain localhost 4445 Mick Jagger");
        }
        else {
            serverAddress = args[0];
            serverPort = Integer.parseInt(args[1]);
            studentName = args[2] + " " + args[3];
        }
        System.out.println("Using server address: " + serverAddress);
        System.out.println("Using server port: " + serverPort);

        UdpClientController controller = new UdpClientController(serverAddress, serverPort, studentName);
        StudentClientFrame frame = new StudentClientFrame(controller);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
