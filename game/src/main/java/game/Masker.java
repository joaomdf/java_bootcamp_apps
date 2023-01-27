package game;
import java.util.ArrayList;
public class Masker {
    private ArrayList<Character> prev_guesses;
    private String chosen_word;

    public Masker(String chosen_word, ArrayList<Character> prev_guesses){
        this.chosen_word = chosen_word;
        this.prev_guesses = prev_guesses;
    }
    public String codify() {
        StringBuilder coded_word = new StringBuilder();
        coded_word.append(this.chosen_word.charAt(0));
        for (int i = 1; i < this.chosen_word.length(); i++) {
            if (this.prev_guesses.contains(this.chosen_word.charAt(i)) ) {
                coded_word.append(this.chosen_word.charAt(i));
            } else {
                coded_word.append("_");
            }
        }
        return coded_word.toString();
    }
}
