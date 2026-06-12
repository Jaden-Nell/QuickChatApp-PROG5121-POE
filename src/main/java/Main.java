import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    // Collections used for Part 3
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login registeredUser = null; // Stores user after successful registration

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

        while (!menuChoice.equals("3")) {

            System.out.println("\nWelcome to QuickChat.");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
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

                System.out.println("Coming Soon.");

            } else if (menuChoice.equals("3")) {

                System.out.println("Goodbye.");

            } else {

                System.out.println("Invalid option selected.");
            }
        }
    }
}