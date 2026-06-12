import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    // Collections used for Part 3
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();

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

                if (sentMessages.isEmpty()) {

                    System.out.println("No messages have been sent yet.");

                } else {

                    System.out.println("\n=== Sent Messages ===");

                    for (Message message : sentMessages) {

                        System.out.println(message.printMessageDetails());
                        System.out.println("----------------------------------");
                    }
                }

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
                System.out.println("Stored message sender and recipient feature coming next.");
            } else if (storedChoice.equals("2")) {
                System.out.println("Longest stored message feature coming next.");
            } else if (storedChoice.equals("3")) {
                System.out.println("Search by Message ID feature coming next.");
            } else if (storedChoice.equals("4")) {
                System.out.println("Search by recipient feature coming next.");
            } else if (storedChoice.equals("5")) {
                System.out.println("Delete by message hash feature coming next.");
            } else if (storedChoice.equals("6")) {
                System.out.println("Full report feature coming next.");
            } else if (storedChoice.equals("7")) {
                System.out.println("Returning to QuickChat menu.");
            } else {
                System.out.println("Invalid option selected.");
            }
        }
    }
}