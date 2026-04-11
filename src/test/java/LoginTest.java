import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testUsernameCorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!", "Ch&sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordCorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrect() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccess() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }

    @Test
    public void testLoginFail() {
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("wrong", "password"));
    }
}