package game;

import java.util.Random;

public class WordChooser {
    private static final String[] dictionary = {"MAKERS", "CANDIES", "DEVELOPER", "LONDON"};
    public String getRandomWordFromDictionary() {
        Random randomizer = new Random();
        return dictionary[randomizer.nextInt(dictionary.length)];
    }
}
