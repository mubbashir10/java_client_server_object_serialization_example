//importing libraries
import org.junit.Test;
import static org.junit.Assert.*;

//tTest class
public class SaveNoteTest {

    @Test
    public void unitTest() {

        Server s = new Server();
        assertEquals(true, s.saveNote("sampleUser","This is a dummy comment to check test!"));

    }
}