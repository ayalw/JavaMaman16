package maman16.trivianet.triviaclient;

import maman16.trivianet.triviacommon.ClientMessageRequestQuestion;
import maman16.trivianet.triviacommon.TriviaMessage;

import java.io.*;
import java.net.Socket;

public class ClientMain {

    public static void main(String[] args) {
        System.out.println("Welcome to Trivia Client!");
        String hostname = "localhost";
        int port_no = 3333;
        TriviaMessage message = new ClientMessageRequestQuestion();
        Socket s = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try {
            s = new Socket(hostname, port_no);
            in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
            out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
            out.writeObject(message);
            out.flush();
            System.out.println("Sent obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
