package maman16.trivianet.triviacommon;

public class TriviaMessage {

    private TriviaMessageType m_messageType;

    public TriviaMessage(TriviaMessageType messageType) {
        m_messageType = messageType;
    }

    public TriviaMessageType getMessageType() {
        return m_messageType;
    }
}
