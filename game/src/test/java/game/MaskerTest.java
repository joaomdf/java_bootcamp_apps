package game;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class MaskerTest {
    @Test
    public void testCodify() {
        ArrayList<Character> test_guesses = new ArrayList<>();
        Masker test_masker = new Masker("DEVELOPER",test_guesses);
        assertEquals("D________", test_masker.codify());
        test_guesses.add('A');
        assertEquals("D________", test_masker.codify());
        test_guesses.add('E');
        assertEquals("DE_E___E_", test_masker.codify());
        test_guesses.add('U');
        assertEquals("DE_E___E_", test_masker.codify());
        test_guesses.add('V');
        assertEquals("DEVE___E_", test_masker.codify());
    }
}
