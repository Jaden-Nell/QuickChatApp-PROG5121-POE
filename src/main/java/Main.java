import java.util.Scanner;

public class Main {
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
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }

            if (registeredUser.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.");
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }

            if (registeredUser.checkCellPhoneNumber()) {
                System.out.println("Cell phone number successfully added.");
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
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
            }

        } else if (choice.equals("2") || choice.equalsIgnoreCase("Login")) {
            System.out.println("\n=== Login ===");

            if (registeredUser == null) {
                System.out.println("No user is registered yet. Please register first.");
            } else {
                System.out.print("Enter username: ");
                String enteredUserName = input.nextLine();

                System.out.print("Enter password: ");
                String enteredPassword = input.nextLine();

                boolean loginSuccess = registeredUser.loginUser(enteredUserName, enteredPassword);
                System.out.println(registeredUser.returnLoginStatus(loginSuccess));
            }

        } else {
            System.out.println("Invalid option selected.");
        }

        input.close();
    }
}