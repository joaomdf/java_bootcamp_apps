package game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {
    @Test
    public void testGetPlayerName(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("LONDON");
        Game test_game = new Game(mockedChooser, "Adam Little");
        assertEquals("Adam Little", test_game.getPlayerName());
    }
    @Test
    public void testGetsWordToGuessWithRandomWord() {
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("LONDON");
        Masker mockedMasker = mock(Masker.class);
        when(mockedMasker.codify()).thenReturn("L_____");
        Game game = new Game(mockedChooser,"The pope");
        assertEquals("L_____", game.getWordToGuess(mockedMasker));
    }
    @Test
    public void testAttemptCounter(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
        Game game = new Game(mockedChooser, "The Dalai Lama");
        assertEquals(10, game.getRemainingGuesses());
    }

    @Test
    public void testGuessLetterWrongLetter(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("LONDON");
        Game test_game = new Game(mockedChooser, "David Bowie");
        Character letter = 'A';
        Boolean guess_result = test_game.guessLetter(letter);
        assertEquals(9, test_game.getRemainingGuesses());
        assertEquals(false, guess_result);
        assertEquals(false, guess_result);
        assertTrue(test_game.getPreviousGuesses().contains(letter));
    }
    @Test
    public void testGuessLetterCorrectLetter(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("LONDON");
        Game test_game = new Game(mockedChooser, "Madonna");
        Character letter = 'O';
        Boolean guess_result = test_game.guessLetter(letter);
        assertEquals(10, test_game.getRemainingGuesses());
        assertEquals(true, guess_result);
        assertTrue(test_game.getPreviousGuesses().contains(letter));
    }
    @Test
    public void testGuessLetterCorrectLetterAndGetWordToGuess(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("LONDON");
        Game test_game = new Game(mockedChooser, "Kendrick Lamar");
        Character letter = 'O';
        Masker mockedMasker = mock(Masker.class);
        when(mockedMasker.codify()).thenReturn("LO__O_");
        Boolean guess_result = test_game.guessLetter(letter);
        assertEquals(10, test_game.getRemainingGuesses());
        assertEquals(true, guess_result);
        assertTrue(test_game.getPreviousGuesses().contains(letter));
        assertEquals("LO__O_", test_game.getWordToGuess(mockedMasker));
    }

    @Test
    public void testGuessLetterIncorrectLetterAndGetWordToGuess(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
        Game test_game = new Game(mockedChooser, "Me");
        Masker mockedMasker = mock(Masker.class);
        when(mockedMasker.codify()).thenReturn("D________");
        assertEquals(false, test_game.guessLetter('A'));
        assertEquals(9, test_game.getRemainingGuesses());
        assertTrue(test_game.getPreviousGuesses().contains('A'));
        assertEquals("D________", test_game.getWordToGuess(mockedMasker));
    }

    @Test
    public void testGuessLetterIncorrectAndCorrect(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
        Game test_game = new Game(mockedChooser, "Rupaul");
        Masker mockedMasker1 = mock(Masker.class);
        when(mockedMasker1.codify()).thenReturn("D________");
        assertEquals(false, test_game.guessLetter('A'));
        assertEquals(9, test_game.getRemainingGuesses());
        assertTrue(test_game.getPreviousGuesses().contains('A'));
        assertEquals("D________", test_game.getWordToGuess(mockedMasker1));
        Masker mockedMasker2 = mock(Masker.class);
        when(mockedMasker2.codify()).thenReturn("DE_E___E_");
        assertEquals(true, test_game.guessLetter('E'));
        assertEquals(9, test_game.getRemainingGuesses());
        assertTrue(test_game.getPreviousGuesses().contains('E'));
        assertEquals("DE_E___E_", test_game.getWordToGuess(mockedMasker2));
        assertEquals(false, test_game.guessLetter('U'));
        assertEquals(8, test_game.getRemainingGuesses());
        assertTrue(test_game.getPreviousGuesses().contains('U'));
        assertEquals("DE_E___E_", test_game.getWordToGuess(mockedMasker2));
        Masker mockedMasker3 = mock(Masker.class);
        when(mockedMasker3.codify()).thenReturn("DEVE___E_");
        assertEquals(true, test_game.guessLetter('V'));
        assertEquals(8, test_game.getRemainingGuesses());
        assertTrue(test_game.getPreviousGuesses().contains('V'));
        assertEquals("DEVE___E_", test_game.getWordToGuess(mockedMasker3));
    }

    @Test
    void gameLost(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
        Game test_game = new Game(mockedChooser, "Katya");
        test_game.guessLetter('A');
        test_game.guessLetter('B');
        test_game.guessLetter('C');
        test_game.guessLetter('F');
        test_game.guessLetter('A');
        test_game.guessLetter('G');
        test_game.guessLetter('H');
        test_game.guessLetter('I');
        test_game.guessLetter('J');
        test_game.guessLetter('K');
        assertEquals(true, test_game.isGameLost());
    }
    @Test
    void gameNotLostAllAttempts(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
        Game test_game = new Game(mockedChooser, "Trixie");
        test_game.guessLetter('A');
        test_game.guessLetter('B');
        test_game.guessLetter('C');
        test_game.guessLetter('F');
        test_game.guessLetter('A');
        test_game.guessLetter('G');
        test_game.guessLetter('H');
        test_game.guessLetter('I');
        test_game.guessLetter('J');
        test_game.guessLetter('E');
        test_game.guessLetter('V');
        test_game.guessLetter('L');
        test_game.guessLetter('O');
        test_game.guessLetter('P');
        test_game.guessLetter('R');
        assertEquals(false, test_game.isGameLost());
    }
    @Test
    void gameWon(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
        Game test_game = new Game(mockedChooser, "Obama");
        test_game.guessLetter('E');
        test_game.guessLetter('V');
        test_game.guessLetter('L');
        test_game.guessLetter('O');
        test_game.guessLetter('P');
        test_game.guessLetter('R');
        assertEquals(true, test_game.isGameWon());
        assertEquals(false, test_game.isGameLost());
    }

    @Test
    void gameWonAllAttempts(){
        WordChooser mockedChooser = mock(WordChooser.class);
        when(mockedChooser.getRandomWordFromDictionary()).thenReturn("DEVELOPER");
        Game test_game = new Game(mockedChooser,"Trump");
        test_game.guessLetter('A');
        test_game.guessLetter('B');
        test_game.guessLetter('C');
        test_game.guessLetter('F');
        test_game.guessLetter('A');
        test_game.guessLetter('G');
        test_game.guessLetter('H');
        test_game.guessLetter('I');
        test_game.guessLetter('J');
        test_game.guessLetter('E');
        test_game.guessLetter('V');
        test_game.guessLetter('L');
        test_game.guessLetter('O');
        test_game.guessLetter('P');
        test_game.guessLetter('R');
        assertEquals(true, test_game.isGameWon());
        assertEquals(false, test_game.isGameLost());
    }
}