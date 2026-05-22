import java.util.Random;

public class Message {

    // Message details
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;

    // Constructor
    public Message(int messageNumber, String recipient, String message) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    // Generates a random 10-digit message ID
    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }

        return id.toString();
    }

    // Checks that message ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Checks if recipient number starts with +27 and has the correct format
    public String checkRecipientCell() {
        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Checks if the message is 250 characters or less
    public String validateMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extraCharacters = message.length() - 250;
            return "Message exceeds 250 characters by " + extraCharacters + ", please reduce the size.";
        }
    }

    // Getter methods
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}