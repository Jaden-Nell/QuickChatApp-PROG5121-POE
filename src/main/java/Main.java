import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login registeredUser = null; // Will store user after registration

        // Menu shown when program starts
        System.out.println("Welcome to QuickChat");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");

        String choice = input.nextLine().trim();

        // If user chooses register
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

            // Create Login object
            registeredUser = new Login(firstName, lastName, userName, password, cellPhoneNumber);

            // Run validation checks
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

            // Show final registration result
            System.out.println("\n=== Registration Result ===");
            System.out.println(registeredUser.registerUser());

            // Allow login after successful registration
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

        } 
        // If user chooses login first
        else if (choice.equals("2") || choice.equalsIgnoreCase("Login")) {

            System.out.println("\n=== Login ===");

            // No user stored yet
            if (registeredUser == null) {
                System.out.println("No user is registered yet. Please register first.");
            }
        } 
        // Invalid input
        else {
            System.out.println("Invalid option selected.");
        }

        input.close();
    }
}