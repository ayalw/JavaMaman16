package maman16.trivianet.triviaclient;

import maman16.trivianet.triviacommon.*;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class QuestionRepositoryProxy implements QuestionProvider {

    private Socket m_socket;
    private ObjectOutputStream m_out;
    private ObjectInputStream m_in;

    public QuestionRepositoryProxy() {
        System.out.println("Welcome to Trivia Client!");
        String hostname = "localhost";
        int port_no = 3333;
        UUID uuid = java.util.UUID.randomUUID();
        TriviaMessage message;
        try {
            m_socket = new Socket(hostname, port_no);
            m_in = new ObjectInputStream(new BufferedInputStream(m_socket.getInputStream()));
            m_out = new ObjectOutputStream(new BufferedOutputStream(m_socket.getOutputStream()));

            // Start session
            message = new ClientMessageStartSession(uuid);
            m_out.writeObject(message);
            m_out.flush();

            m_in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question getRandomQuestion() {
            TriviaMessage message = new ClientMessageRequestQuestion();
        try {
            m_out.writeObject(message);
            m_out.flush();
            TriviaMessage triviaMessage = (TriviaMessage) m_in.readObject();
            if (triviaMessage.getMessageType() == TriviaMessageType.SERVER_MESSAGE_PROVIDE_QUESTION) {
                ServerMessageProvideQuestion msg = (ServerMessageProvideQuestion)triviaMessage;
                return msg.getQuestion();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;//TODO
    }

    @Override
    public boolean hasUnusedQuestions() {
        TriviaMessage msg = new ClientMessageHasUnusedQuestions();
        try {
            m_out.writeObject(msg);
            m_out.flush();
            TriviaMessage incomingMsg = (TriviaMessage) m_in.readObject();
            if (incomingMsg.getMessageType() == TriviaMessageType.SERVER_MESSAGE_RESPONSE_YES) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
