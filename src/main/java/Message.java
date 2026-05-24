import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

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

    // Creates the message hash using ID, message number, first word and last word
    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Returns the correct message depending on the user's send choice
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

    // Returns message details in the required display order
    public String printMessageDetails() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Stores message details in a JSON file
    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("stored_messages.json", true);

            writer.write("{\n");
            writer.write("  \"MessageID\": \"" + messageID + "\",\n");
            writer.write("  \"MessageHash\": \"" + createMessageHash() + "\",\n");
            writer.write("  \"Recipient\": \"" + recipient + "\",\n");
            writer.write("  \"Message\": \"" + message + "\"\n");
            writer.write("}\n\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while storing the message.");
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