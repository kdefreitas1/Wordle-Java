import java.util.Scanner;
import java.util.ArrayList;

public class Wordle {

    public ArrayList<String> checkGuess(String guess, String answer) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < 5; i++) {
            result.add("grey");
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guess.substring(i, i+1).equals(answer.substring(i, i+1))) {
                result.set(i, "green");
            } else if (answer.contains(guess.substring(i, i+1))) {
                result.set(i, "yellow");
            }
        }

        return result;
    }

    public void start() {
        String answer = "apple";
        Scanner input = new Scanner(System.in);
        int numOfGuesses = 1;

        while (true) {
            if (numOfGuesses > 5) {
                System.out.println("You lose. The answer was " + answer + ".");
                break;
            }

            System.out.print(numOfGuesses + ". Enter your guess: ");
            String guess = input.nextLine();

            if (guess.length() != 5) {
                System.out.println("Please enter a 5 letter word.");
                continue;
            }

            ArrayList<String>result = checkGuess(guess, answer);
            
            for (String color : result) {
                System.out.print(color + " ");
            }

            System.out.println();

            if (guess.equals(answer)) {
                System.out.println("Correct. Game over.");
                break;
            }

            numOfGuesses++;
        }

        input.close();
    }
}
