package maman16.students.udpclient;

import javax.swing.*;

public class UdpClientMain {
    public static void main(String[] args) {
        String serverAddress;
        int serverPort;
        String studentName = null;
        if (args.length < 4) {
            System.out.println("Usage: UdpClientMain [Server Address] [Server Port] [First Name] [Last Name]");
            //System.exit(0);
            System.out.println("Using default configuration...");
            serverAddress = "localhost";
            serverPort = 4445;
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
