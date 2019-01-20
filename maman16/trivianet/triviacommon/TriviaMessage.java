package maman16.trivianet.triviacommon;

import java.io.Serializable;

public class TriviaMessage implements Serializable {

    private TriviaMessageType m_messageType;

    public TriviaMessage(TriviaMessageType messageType) {
        m_messageType = messageType;
    }

    public TriviaMessageType getMessageType() {
        return m_messageType;
    }

    @Override
    public String toString() {
        return "[Type: " + m_messageType + "]";
    }
}
