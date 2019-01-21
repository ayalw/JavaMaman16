package maman16.trivianet.triviaclient;

import maman16.trivianet.triviacommon.ClientMessageEndSession;
import maman16.trivianet.triviacommon.ClientMessageRequestQuestion;
import maman16.trivianet.triviacommon.ClientMessageStartSession;
import maman16.trivianet.triviacommon.TriviaMessage;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ClientMain {

    public static void main(String[] args) {

        TriviaFrame frame = new TriviaFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
