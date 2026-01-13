import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Wordle {

    public ArrayList<String> checkGuess(String guess, String answer) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < guess.length(); i++) {
            result.add("grey");
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                result.set(i, "green");
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (guess.charAt(i) == answer.charAt(j) && !result.get(j).equals("green")) {
                    result.set(i, "yellow");
                    answer = answer.replaceFirst(guess.substring(i,i+1), "*");
                }
            }
        }

        return result;
    }

    public String getAnswer() {
        String answer = "";
        ArrayList<String> wordsList = new ArrayList<String>();

        try {
            Scanner input = new Scanner(new File("words.txt"));
            while (input.hasNextLine()) {
                wordsList.add(input.nextLine() );
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading words file.");
        }
        int index = (int)(Math.random() * wordsList.size());
        answer = wordsList.get(index);
        System.out.println(answer);
        return answer;
    }

    public void start() {
        String answer = getAnswer();
        Scanner input = new Scanner(System.in);
        int numOfGuesses = 1;

        while (true) {
            if (numOfGuesses > 6) {
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

    public static void main(String[] args) {
        Wordle game = new Wordle();
        game.start();
    }
}
