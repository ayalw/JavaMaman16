package maman16.trivianet.triviaclient;

import maman16.trivianet.triviacommon.ClientMessageEndSession;
import maman16.trivianet.triviacommon.ClientMessageRequestQuestion;
import maman16.trivianet.triviacommon.ClientMessageStartSession;
import maman16.trivianet.triviacommon.TriviaMessage;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ClientMain {

    public static void main(String[] args) {
        System.out.println("Welcome to Trivia Client!");
        String hostname = "localhost";
        int port_no = 3333;
        UUID uuid = java.util.UUID.randomUUID();
        TriviaMessage message;
        Socket s = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try {
            s = new Socket(hostname, port_no);
            in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
            out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

            // Start session
            message = new ClientMessageStartSession(uuid);
            out.writeObject(message);
            out.flush();

            // Request question
            message = new ClientMessageRequestQuestion();
            out.writeObject(message);
            out.flush();

            // End session
            message = new ClientMessageEndSession();
            out.writeObject(message);
            out.flush();

            System.out.println("Sent obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
