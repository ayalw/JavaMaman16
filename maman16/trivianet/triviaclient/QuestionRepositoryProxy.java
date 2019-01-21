package maman16.trivianet.triviaclient;

import maman16.trivianet.triviacommon.*;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class QuestionRepositoryProxy implements QuestionProvider {

    private ObjectOutputStream m_out;

    public QuestionRepositoryProxy() {
        System.out.println("Welcome to Trivia Client!");
        String hostname = "localhost";
        int port_no = 3333;
        UUID uuid = java.util.UUID.randomUUID();
        TriviaMessage message;
        Socket s = null;
        ObjectInputStream in = null;
        //ObjectOutputStream out = null;
        try {
            s = new Socket(hostname, port_no);
            in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
            m_out = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

            // Start session
            message = new ClientMessageStartSession(uuid);
            m_out.writeObject(message);
            m_out.flush();

//            // Request question
//            message = new ClientMessageRequestQuestion();
//            out.writeObject(message);
//            out.flush();
//
//            // End session
//            message = new ClientMessageEndSession();
//            out.writeObject(message);
//            out.flush();

            System.out.println("Sent obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question getRandomQuestion() {
            TriviaMessage message = new ClientMessageRequestQuestion();
        try {
            m_out.writeObject(message);
            m_out.flush();
            //m_in.readObject();//TODO
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;//TODO
    }

    @Override
    public boolean hasUnusedQuestions() {
        return false;
    }
}
