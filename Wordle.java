import java.util.Scanner;

public class Wordle {
    
    public void start() {
        String answer = "apple";
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your guess: ");
        String guess = input.nextLine();

        input.close();
    }
}
