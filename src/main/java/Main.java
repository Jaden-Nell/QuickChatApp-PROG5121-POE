import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login registeredUser = null;

        System.out.println("Welcome to QuickChat");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");

        String choice = input.nextLine().trim();

        if (choice.equals("1") || choice.equalsIgnoreCase("Register")) {

            System.out.println("\n=== Registration ===");

            System.out.print("Enter your first name: ");
            String firstName = input.nextLine();

            System.out.print("Enter your last name: ");
            String lastName = input.nextLine();

            System.out.print("Enter your username: ");
            String userName = input.nextLine();

            System.out.print("Enter your password: ");
            String password = input.nextLine();

            System.out.print("Enter your cell phone number (with international code): ");
            String cellPhoneNumber = input.nextLine();

            registeredUser = new Login(firstName, lastName, userName, password, cellPhoneNumber);

            if (registeredUser.checkUserName()) {
                System.out.println("Username successfully captured.");
            } else {
                System.out.println("Username is not correctly formatted.");
            }

            if (registeredUser.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.");
            } else {
                System.out.println("Password is not correctly formatted.");
            }

            if (registeredUser.checkCellPhoneNumber()) {
                System.out.println("Cell phone number successfully added.");
            } else {
                System.out.println("Cell phone number incorrectly formatted.");
            }

            System.out.println("\n=== Registration Result ===");
            System.out.println(registeredUser.registerUser());

            if (registeredUser.checkUserName()
                    && registeredUser.checkPasswordComplexity()
                    && registeredUser.checkCellPhoneNumber()) {

                System.out.println("\nYou may now log in.");

                System.out.print("Enter username: ");
                String enteredUserName = input.nextLine();

                System.out.print("Enter password: ");
                String enteredPassword = input.nextLine();

                boolean loginSuccess = registeredUser.loginUser(enteredUserName, enteredPassword);
                System.out.println(registeredUser.returnLoginStatus(loginSuccess));

                if (loginSuccess) {
                    showQuickChatMenu(input);
                }
            }

        } else if (choice.equals("2") || choice.equalsIgnoreCase("Login")) {

            System.out.println("\n=== Login ===");

            if (registeredUser == null) {
                System.out.println("No user is registered yet. Please register first.");
            }

        } else {
            System.out.println("Invalid option selected.");
        }

        input.close();
    }

    public static void showQuickChatMenu(Scanner input) {
        String menuChoice = "";

        while (!menuChoice.equals("4")) {

            System.out.println("\nWelcome to QuickChat.");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Stored Messages");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");

            menuChoice = input.nextLine().trim();

            if (menuChoice.equals("1")) {

                System.out.print("How many messages would you like to send? ");
                int totalMessages = Integer.parseInt(input.nextLine());
                int sentMessageCount = 0;

                for (int i = 1; i <= totalMessages; i++) {

                    System.out.println("\nMessage " + i);

                    System.out.print("Enter recipient number: ");
                    String recipient = input.nextLine();

                    System.out.print("Enter your message: ");
                    String messageText = input.nextLine();

                    Message message = new Message(i, recipient, messageText);

                    messageIDs.add(message.getMessageID());
                    messageHashes.add(message.createMessageHash());

                    System.out.println(message.checkRecipientCell());
                    System.out.println(message.validateMessageLength());

                    System.out.println("Message ID: " + message.getMessageID());
                    System.out.println("Message Hash: " + message.createMessageHash());

                    System.out.println("\nWhat would you like to do with this message?");
                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");
                    System.out.print("Choose an option: ");

                    String sendChoice = input.nextLine().trim();

                    System.out.println(message.sentMessage(sendChoice));

                    if (sendChoice.equals("1") || sendChoice.equalsIgnoreCase("Send Message")) {
                        sentMessages.add(message);
                        sentMessageCount++;
                        System.out.println("\n" + message.printMessageDetails());

                    } else if (sendChoice.equals("2") || sendChoice.equalsIgnoreCase("Disregard Message")) {
                        disregardedMessages.add(message);

                    } else if (sendChoice.equals("3") || sendChoice.equalsIgnoreCase("Store Message")) {
                        storedMessages.add(message);
                        message.storeMessage();
                    }
                }

                System.out.println("\nTotal messages sent: " + sentMessageCount);

            } else if (menuChoice.equals("2")) {

                System.out.println(displaySentMessages());

            } else if (menuChoice.equals("3")) {

                showStoredMessagesMenu(input);

            } else if (menuChoice.equals("4")) {

                System.out.println("Goodbye.");

            } else {

                System.out.println("Invalid option selected.");
            }
        }
    }

    public static void showStoredMessagesMenu(Scanner input) {
        String storedChoice = "";

        while (!storedChoice.equals("7")) {
            System.out.println("\n=== Stored Messages Menu ===");
            System.out.println("1. Display sender and recipient of stored messages");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search by Message ID");
            System.out.println("4. Search by recipient");
            System.out.println("5. Delete by message hash");
            System.out.println("6. Display full report");
            System.out.println("7. Back to QuickChat menu");
            System.out.print("Choose an option: ");

            storedChoice = input.nextLine().trim();

            if (storedChoice.equals("1")) {
                System.out.println(displayStoredSendersAndRecipients());

            } else if (storedChoice.equals("2")) {
                System.out.println(displayLongestStoredMessage());

            } else if (storedChoice.equals("3")) {
                System.out.print("Enter Message ID: ");
                String id = input.nextLine();
                System.out.println(searchByMessageID(id));

            } else if (storedChoice.equals("4")) {
                System.out.print("Enter recipient number: ");
                String recipient = input.nextLine();
                System.out.println(searchByRecipient(recipient));

            } else if (storedChoice.equals("5")) {
                System.out.print("Enter message hash: ");
                String hash = input.nextLine();
                System.out.println(deleteByMessageHash(hash));

            } else if (storedChoice.equals("6")) {
                System.out.println(displayReport());

            } else if (storedChoice.equals("7")) {
                System.out.println("Returning to QuickChat menu.");

            } else {
                System.out.println("Invalid option selected.");
            }
        }
    }

    public static String displaySentMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }

        StringBuilder output = new StringBuilder("\n=== Sent Messages ===\n");

        for (Message message : sentMessages) {
            output.append(message.printMessageDetails()).append("\n");
            output.append("----------------------------------\n");
        }

        return output.toString();
    }

    public static String displayStoredSendersAndRecipients() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }

        StringBuilder output = new StringBuilder("\n=== Stored Message Senders and Recipients ===\n");

        for (Message message : storedMessages) {
            output.append("Sender: User\n");
            output.append("Recipient: ").append(message.getRecipient()).append("\n");
            output.append("----------------------------------\n");
        }

        return output.toString();
    }

    public static String displayLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }

        Message longestMessage = storedMessages.get(0);

        for (Message message : storedMessages) {
            if (message.getMessage().length() > longestMessage.getMessage().length()) {
                longestMessage = message;
            }
        }

        return longestMessage.getMessage();
    }

    public static String searchByMessageID(String messageID) {
        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);
        allMessages.addAll(disregardedMessages);

        for (Message message : allMessages) {
            if (message.getMessageID().equals(messageID)) {
                return "Recipient: " + message.getRecipient()
                        + "\nMessage: " + message.getMessage();
            }
        }

        return "Message ID not found.";
    }

    public static String searchByRecipient(String recipient) {
        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);

        StringBuilder output = new StringBuilder();

        for (Message message : allMessages) {
            if (message.getRecipient().equals(recipient)) {
                output.append(message.getMessage()).append("\n");
            }
        }

        if (output.length() == 0) {
            return "No messages found for this recipient.";
        }

        return output.toString();
    }

    public static String deleteByMessageHash(String hash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            Message message = storedMessages.get(i);

            if (message.createMessageHash().equals(hash)) {
                storedMessages.remove(i);
                return "Message: \"" + message.getMessage() + "\" successfully deleted.";
            }
        }

        return "Message hash not found.";
    }

    public static String displayReport() {
        if (sentMessages.isEmpty()) {
            return "No sent messages to report.";
        }

        StringBuilder report = new StringBuilder("\n=== Sent Messages Report ===\n");

        for (Message message : sentMessages) {
            report.append("Message Hash: ").append(message.createMessageHash()).append("\n");
            report.append("Recipient: ").append(message.getRecipient()).append("\n");
            report.append("Message: ").append(message.getMessage()).append("\n");
            report.append("----------------------------------\n");
        }

        return report.toString();
    }

    public static void clearCollectionsForTesting() {
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
    }

    public static void addMessageForTesting(Message message, String flag) {
        messageIDs.add(message.getMessageID());
        messageHashes.add(message.createMessageHash());

        if (flag.equalsIgnoreCase("Sent")) {
            sentMessages.add(message);
        } else if (flag.equalsIgnoreCase("Stored")) {
            storedMessages.add(message);
        } else if (flag.equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(message);
        }
    }
}