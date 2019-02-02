package maman16.trivianet.triviaserver;

import maman16.trivianet.triviacommon.*;

import java.io.*;
import java.net.Socket;

/**
 * Handler thread to manage client session, send it questions and score.
 */
public class TriviaClientHandler extends Thread {

    /**
     * TCP socket with specific client.
     */
    protected Socket m_socket;

    /**
     * Stream for TriviaMessage objects.
     */
    private ObjectInputStream m_in;

    /**
     * Stream for TriviaMessage objects.
     */
    private ObjectOutputStream m_out;

    /**
     * Game engine to evaluate questions, answers and score.
     */
    private TriviaGameEngine m_engine;

    /**
     * Current question sent to client.
     */
    private Question m_currentQuestion;

    /**
     * Constructor
     * @param clientSocket
     */
    public TriviaClientHandler(Socket clientSocket) {
        m_socket = clientSocket;
    }

    /**
     * Main work loop - respond to different types of TriviaMessage objects arriving from client.
     */
    public void run() {
        System.out.println("Starting client handler: " + getId());
        try {
            m_out = new ObjectOutputStream(m_socket.getOutputStream());
            m_in = new ObjectInputStream(m_socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isSessionAlive = true;
        m_engine = new TriviaGameEngine("input.txt");
        m_currentQuestion = m_engine.getNextQuestion();
        TriviaMessage incomingMsg;
        TriviaMessage outgoingMsg = null;
        while (isSessionAlive) {
            try {
                incomingMsg = (TriviaMessage) m_in.readObject();
                if (incomingMsg.getMessageType() == TriviaMessageType.CLIENT_MESSAGE_START_SESSION) {
                    isSessionAlive = true;
                    outgoingMsg = new ServerMessageResponseOk();
                }
                if (incomingMsg.getMessageType() == TriviaMessageType.CLIENT_MESSAGE_HAS_UNUSED_QUESTIONS) {
                    if (m_engine.hasUnusedQuestion()) {
                        outgoingMsg = new ServerMessageResponseYes();
                    }
                    else {
                        outgoingMsg = new ServerMessageResponseNo();
                    }
                }
                if (incomingMsg.getMessageType() == TriviaMessageType.CLIENT_MESSAGE_END_SESSION) {
                    isSessionAlive = false;
                    outgoingMsg = new ServerMessageResponseOk();
                }
                if (incomingMsg.getMessageType() == TriviaMessageType.CLIENT_MESSAGE_REQUEST_QUESTION) {
                    Question q = m_engine.getNextQuestion();
                    if (q == null) {
                        outgoingMsg = new ServerMessageNoMoreQuestions();
                    }
                    else {
                        outgoingMsg = new ServerMessageProvideQuestion(q);
                    }
                }
                m_out.writeObject(outgoingMsg);
                m_out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                isSessionAlive = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                isSessionAlive = false;
            }
        }
    }
}
