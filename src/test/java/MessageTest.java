import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", message.validateMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(260);
        Message message = new Message(1, "+27718693002", longMessage);
        assertEquals("Message exceeds 250 characters by 10, please reduce the size.", message.validateMessageLength());
    }

    @Test
    public void testRecipientCorrect() {
        Message message = new Message(1, "+27718693002", "Hi Mike");
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrect() {
        Message message = new Message(1, "0857597589", "Hi Keegan");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", message.checkRecipientCell());
    }

    @Test
    public void testMessageIDCreated() {
        Message message = new Message(1, "+27718693002", "Hi Mike");
        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testMessageHashContainsExpectedText() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = message.createMessageHash();

        assertTrue(hash.contains(":1:"));
        assertTrue(hash.endsWith("HITONIGHT?"));
    }

    @Test
    public void testSendMessageChoice() {
        Message message = new Message(1, "+27718693002", "Hi Mike");
        assertEquals("Message successfully sent.", message.sentMessage("1"));
    }

    @Test
    public void testDisregardMessageChoice() {
        Message message = new Message(1, "+27718693002", "Hi Mike");
        assertEquals("Press 0 to delete the message.", message.sentMessage("2"));
    }

    @Test
    public void testStoreMessageChoice() {
        Message message = new Message(1, "+27718693002", "Hi Mike");
        assertEquals("Message successfully stored.", message.sentMessage("3"));
    }
}