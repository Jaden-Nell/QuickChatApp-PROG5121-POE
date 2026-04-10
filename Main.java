import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== QuickChat Registration ===");

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

        Login login = new Login(firstName, lastName, userName, password, cellPhoneNumber);

        // Username check
        if (login.checkUserName()) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        // Password check
        if (login.checkPasswordComplexity()) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        // Phone check
        if (login.checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }

        System.out.println(login.registerUser());

        // Only allow login if registration is valid
        if (login.checkUserName() && login.checkPasswordComplexity() && login.checkCellPhoneNumber()) {
            System.out.println("\n=== Login ===");

            System.out.print("Enter username: ");
            String enteredUserName = input.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = input.nextLine();

            boolean loginSuccess = login.loginUser(enteredUserName, enteredPassword);
            System.out.println(login.returnLoginStatus(loginSuccess));
        }

        input.close();
    }
}