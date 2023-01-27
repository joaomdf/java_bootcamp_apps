package game;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Main {
    private static Integer current_player;
    private static Integer number_of_players;
    public static void changePlayer(){
        if (current_player == number_of_players - 1){
            current_player = 0;
        }
        else{
            current_player ++;
        }
    }
    public static void main(String[] args) {
        ArrayList<Game> games = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean change;

        System.out.println("How many players are there? ");
        number_of_players = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= number_of_players; i ++){
            System.out.printf("Enter name for player %s:", i);
            games.add(new Game(new WordChooser(), input.nextLine()));
        }
        System.out.println("\nWelcome! Today the word to guess is:");
        for (int i = 0; i < number_of_players; i ++){
            System.out.printf("%s: %s\n", games.get(i).getPlayerName(), games.get(i).getWordToGuess(new Masker(games.get(i).getChosenWord(),games.get(i).getPreviousGuesses())));
        }

        Random randomizer = new Random();
        current_player = randomizer.nextInt(number_of_players + 1) - 1;

        while (games.get(current_player).getRemainingGuesses() > 0) {
            change = true;
            System.out.printf("\n%s: Enter one letter to guess (%s attempts remaining):", games.get(current_player).getPlayerName(), games.get(current_player).getRemainingGuesses());
            String this_guess = input.nextLine().toUpperCase();
            if (games.get(current_player).getPreviousGuesses().contains(this_guess.charAt(0))){
                System.out.println("You've already guessed that letter! Try again!");
                change = false;
            }
            else if (games.get(current_player).guessLetter(this_guess.charAt(0))) {
                System.out.println("Right!");
                if (games.get(current_player).isGameWon()){
                    System.out.println("Yeaaah! You won!");
                    return;
                }
            } else {
                System.out.println("Wrong...");
                if (games.get(current_player).isGameLost()) {
                    System.out.println("Oh, no! You lost!");
                    return;
                }
            }
            System.out.printf("%s\n", games.get(current_player).getWordToGuess(new Masker(games.get(current_player).getChosenWord(),games.get(current_player).getPreviousGuesses())));
            if (change) {
                changePlayer();
            }
        }
    }
}
