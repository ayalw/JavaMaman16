package maman16.trivianet.triviaserver;

import maman16.trivianet.triviacommon.TriviaMessage;
import maman16.trivianet.triviacommon.TriviaMessageType;

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
        boolean isSessionAlive = true;
        while (isSessionAlive) {
            try {
                TriviaMessage triviaMessage = (TriviaMessage) is.readObject();
                handleTriviaMessage(triviaMessage);
                if (triviaMessage.getMessageType() == TriviaMessageType.CLIENT_MESSAGE_END_SESSION) {
                    isSessionAlive = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                isSessionAlive = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                isSessionAlive = false;
            }
        }
    }

    private void handleTriviaMessage(TriviaMessage triviaMessage) {
        System.out.println("Got client message: " + triviaMessage);
        return;
    }

}
