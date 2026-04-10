public class LoginTest {
    public static void main(String[] args) {

        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&sec@ke99!", "+27838968976");

        // Username test
        if (login.checkUserName()) {
            System.out.println("Username test passed");
        } else {
            System.out.println("Username test failed");
        }

        // Password test
        if (login.checkPasswordComplexity()) {
            System.out.println("Password test passed");
        } else {
            System.out.println("Password test failed");
        }

        // Phone test
        if (login.checkCellPhoneNumber()) {
            System.out.println("Phone test passed");
        } else {
            System.out.println("Phone test failed");
        }

        // Login test
        boolean loginSuccess = login.loginUser("kyl_1", "Ch&sec@ke99!");
        if (loginSuccess) {
            System.out.println("Login test passed");
        } else {
            System.out.println("Login test failed");
        }
    }
}