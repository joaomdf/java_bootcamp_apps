package game;
import java.util.ArrayList;

public class Game {
    private String chosen_word;
    private Integer attempts = 10;
    private ArrayList<Character> prev_guesses;
    private String player_name;

    public Game(WordChooser word_chooser, String playerName){
        this.chosen_word = word_chooser.getRandomWordFromDictionary();
        this.prev_guesses = new ArrayList<>();
        this.player_name = playerName;
    }
    public Boolean guessLetter(Character guess) {
        if (chosen_word.indexOf(guess) == 0 && chosen_word.indexOf(guess) == chosen_word.lastIndexOf(guess)){
            prev_guesses.add(guess);
            attempts --;
            return false;
        }
        else if (chosen_word.indexOf(guess) != -1){
            prev_guesses.add(guess);
            return true;
        }
        else {
            prev_guesses.add(guess);
            attempts --;
            return false;
        }
    }
    public String getWordToGuess(Masker masker) {
        return masker.codify();
    }

    public Integer getRemainingGuesses(){
        return attempts;
    }
    public ArrayList<Character> getPreviousGuesses(){
        return prev_guesses;
    }
    public String getChosenWord() {
        return chosen_word;
    }
    public String getPlayerName() {return player_name;}
    public boolean isGameLost() {
        return attempts < 1;
    }

    public boolean isGameWon() {
        return attempts > 0 && !getWordToGuess(new Masker(chosen_word,prev_guesses)).contains("_");
    }
}