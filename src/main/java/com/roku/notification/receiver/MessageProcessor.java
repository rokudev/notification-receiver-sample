package com.roku.notification.receiver;

/**
 * TODO replace the code here with your logic for processing messages.
 */
public class MessageProcessor {
    private static final String RPAY_MESSAGE_TYPE = "roku.rpay.push";
    private static final String INVALIDATE_PUBLIC_KEY_MESSAGE_TYPE = "roku.invalidate_public_keys";

    public void processMessage(MessageRecord messageRecord) {
        switch (messageRecord.getMessageKey()) {
            case RPAY_MESSAGE_TYPE:
                processRPayMessage(messageRecord);
                break;
            case INVALIDATE_PUBLIC_KEY_MESSAGE_TYPE:
                System.out.println("Received a message for updating public keys. Nothing further to do.");
                break;
            default:
                System.out.println("Received a message whose type is unknown.");
                break;
        }
    }

    private void processRPayMessage(MessageRecord messageRecord) {
        /* messageRecord.getMessage() will return the RPay JSON message as a String.
           The RPay JSON message will look like this:

           {
                "customerId":"4e5812f5b00b4f0f768d22a7de170",
                "transactionType":"Sale",
                "transactionId":"d9dbdfecc41cbb881ab750135029b",
                "channelId":"555331",
                "channelName":"Roku Developers",
                "productCode":"yN4JEfTRP3IpbuWiJ_MonthlySub",
                "productName":"Monthly Subscription",
                "price":9.99,
                "tax":0.0,
                "total":9.99,
                "currency":"usd",
                "isFreeTrial":false,
                "expirationDate":"2020-04-05T18:45:04.3142198Z",
                "originalTransactionId":"d9dbdfec-c5cc-41cb-b881-ab555555029b",
                "comments":"New order processed.",
                "eventDate":"2020-03-05T18:45:04.8762198Z",
                "creditsApplied":0.00
           }
        */
        System.out.println("Received an RPay message: " + messageRecord.toString());
    }

    public void processError(String jwtMessage, RuntimeException e) {
        // If you want Roku to resend the message, you can handle the error
        // in such a way that an HTTP 4xx or 5xx status code is returned in the HTTP response.
        System.out.println("Failed to process message: " + jwtMessage);
        System.out.println("The error is: " + e.getMessage());
    }
}
