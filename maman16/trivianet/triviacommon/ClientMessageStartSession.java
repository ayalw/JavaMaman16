package maman16.trivianet.triviacommon;

import java.util.UUID;

public class ClientMessageStartSession extends TriviaMessage{
    private UUID m_uuid;

    public ClientMessageStartSession(UUID uuid) {
        super(TriviaMessageType.CLIENT_MESSAGE_START_SESSION);
        m_uuid = uuid;
    }

    public UUID getUUID() {
        return m_uuid;
    }
}
