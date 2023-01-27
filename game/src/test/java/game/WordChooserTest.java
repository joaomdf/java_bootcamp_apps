package game;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class WordChooserTest {
    @Test
    void getsWordFromDictionaryList(){
        WordChooser test_chooser = new WordChooser();
        String[] test_dict = {"MAKERS", "CANDIES", "DEVELOPER", "LONDON"};
        assertTrue(Arrays.asList(test_dict).contains(test_chooser.getRandomWordFromDictionary()));
    }
}
