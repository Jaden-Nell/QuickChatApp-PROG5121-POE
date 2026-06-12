import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;

    public Message(int messageNumber, String recipient, String message) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    public Message(String messageID, int messageNumber, String recipient, String message) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }

        return id.toString();
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String validateMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extraCharacters = message.length() - 250;
            return "Message exceeds 250 characters by " + extraCharacters + ", please reduce the size.";
        }
    }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(String choice) {
        if (choice.equalsIgnoreCase("Send Message") || choice.equals("1")) {
            return "Message successfully sent.";
        } else if (choice.equalsIgnoreCase("Disregard Message") || choice.equals("2")) {
            return "Press 0 to delete the message.";
        } else if (choice.equalsIgnoreCase("Store Message") || choice.equals("3")) {
            return "Message successfully stored.";
        } else {
            return "Invalid option selected.";
        }
    }

    public String printMessageDetails() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("stored_messages.json", true);

            writer.write("{\n");
            writer.write("  \"MessageID\": \"" + messageID + "\",\n");
            writer.write("  \"MessageHash\": \"" + createMessageHash() + "\",\n");
            writer.write("  \"Recipient\": \"" + recipient + "\",\n");
            writer.write("  \"Message\": \"" + message.replace("\"", "\\\"") + "\"\n");
            writer.write("}\n\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while storing the message.");
        }
    }

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