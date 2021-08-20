package com.roku.notification.receiver;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JwtMessageService {
    private JwtMessageVerifier verifier;
    private MessageProcessor messageProcessor;

    public JwtMessageService(JwtMessageVerifier verifier, MessageProcessor messageProcessor) {
        this.verifier = verifier;
        this.messageProcessor = messageProcessor;
    }

    /**
     * This method uses JwtMessageVerifier to verify JWT message signatures to ensure that the JWT
     * messages are created by Roku. Once a JWT message's signature is verified, this method converts
     * the JWT message to a MessageRecord uses MessageProcessor to process the MessageRecord.
     *
     * @param jwtMessage the JWT message to verify. A JWT message looks like this:
     */
    public void processMessage(String jwtMessage) {
        try {
            DecodedJWT decodedJWT = verifier.verifyJwt(jwtMessage);
            MessageRecord messageRecord = toMessageRecord(decodedJWT);
            messageProcessor.processMessage(messageRecord);
        } catch (RuntimeException e) {
            messageProcessor.processError(jwtMessage, e);
        }
    }

    private MessageRecord toMessageRecord(DecodedJWT decodedJWT) {
        String messageType = decodedJWT.getClaim("x-Roku-message-type").asString();
        String messageKey = decodedJWT.getClaim("x-Roku-message-key").asString();
        String encodedMessage = decodedJWT.getClaim("x-Roku-message").asString();
        String message = new String(Base64.getDecoder().decode(encodedMessage), StandardCharsets.UTF_8);
        return new MessageRecord(messageKey, messageType, message);
    }
}
