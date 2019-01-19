package maman16.trivianet.triviaserver;

import maman16.trivianet.triviacommon.TriviaMessage;

import java.io.*;
import java.net.Socket;

public class TriviaClientHandler extends Thread {

    protected Socket m_socket;

    public TriviaClientHandler(Socket clientSocket) {
        m_socket = clientSocket;
    }

    public void run() {
        System.out.println("Starting client handler: " + getId());
        ObjectOutputStream os;
        ObjectInputStream is = null;
        try {
            os = new ObjectOutputStream(m_socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is = new ObjectInputStream(m_socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                TriviaMessage triviaMessage = (TriviaMessage) is.readObject();
                handleTriviaMessage(triviaMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleTriviaMessage(TriviaMessage triviaMessage) {
        return;
    }

}
