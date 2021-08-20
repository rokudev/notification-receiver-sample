package com.roku.notification.receiver;

public class MessageRecord {

    private String messageKey;
    private String messageType;
    private String message;

    public MessageRecord(String messageKey, String messageType, String message) {
        this.messageKey = messageKey;
        this.messageType = messageType;
        this.message = message;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageRecord{" +
                "messageKey='" + messageKey + '\'' +
                ", messageType='" + messageType + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
