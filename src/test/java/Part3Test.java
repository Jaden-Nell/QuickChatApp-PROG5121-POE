import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Part3Test {

    @BeforeEach
    public void setup() {
        Main.clearCollectionsForTesting();

        Main.addMessageForTesting(
                new Message("0000000001", 1, "+27834557896", "Did you get the cake?"),
                "Sent"
        );

        Main.addMessageForTesting(
                new Message("0100000002", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time."),
                "Stored"
        );

        Main.addMessageForTesting(
                new Message("0200000003", 3, "+27834484567", "Yoohoooo, I am at your gate."),
                "Disregard"
        );

        Main.addMessageForTesting(
                new Message("0838884567", 4, "0838884567", "It is dinner time!"),
                "Sent"
        );

        Main.addMessageForTesting(
                new Message("0400000005", 5, "+27838884567", "Ok, I am leaving without you."),
                "Stored"
        );
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        String sentMessages = Main.displaySentMessages();

        assertTrue(sentMessages.contains("Did you get the cake?"));
        assertTrue(sentMessages.contains("It is dinner time!"));
    }

    @Test
    public void testDisplayLongestMessage() {
        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                Main.displayLongestStoredMessage()
        );
    }

    @Test
    public void testSearchForMessageID() {
        String result = Main.searchByMessageID("0838884567");

        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testSearchMessagesByRecipient() {
        String result = Main.searchByRecipient("+27838884567");

        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        Message messageToDelete = new Message(
                "0100000002",
                2,
                "+27838884567",
                "Where are you? You are late! I have asked you to be on time."
        );

        String hash = messageToDelete.createMessageHash();

        assertEquals(
                "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
                Main.deleteByMessageHash(hash)
        );
    }

    @Test
    public void testDisplayReport() {
        String report = Main.displayReport();

        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }
}