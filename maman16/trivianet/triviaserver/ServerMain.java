package maman16.trivianet.triviaserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    /**
     * Main server application class with working loop.
     * For every incoming TCP message, start a handler thread to send questions to the client.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Starting Trivia Server...");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3333);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socket = null;
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new TriviaClientHandler(socket).start();
        }
    }
}
