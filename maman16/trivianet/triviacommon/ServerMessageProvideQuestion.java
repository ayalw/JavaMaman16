package maman16.trivianet.triviacommon;

import java.io.Serializable;

public class ServerMessageProvideQuestion extends TriviaMessage {

    private Question m_question;

    public ServerMessageProvideQuestion(Question question) {
        super(TriviaMessageType.SERVER_MESSAGE_PROVIDE_QUESTION);
        m_question = question;
    }

    public Question getQuestion() {
        return m_question;
    }
}
