import java.util.Random;
import java.util.Scanner;
/**
 * @ClassName Game
 * @Description It is the class for the game entrance and selector
 * @Author Vincent Yuan
 * @Date 2021/10/14
 */
public class Game {

    /**
     * Scanner to scan the input
     */
    private static Scanner scan = new Scanner(System.in);

    /**
     * The runner for blackjack
     */
    private BJRunner runner1;

    /**
     * The runner for Trianta Ena
     */
    private TERunner runner2;

    // constructor
    public Game() {
        runner1 = new BJRunner();
        runner2 = new TERunner();
    }

    /**
     * Users select the game at the very beginning.
     */
    public void select() {
        while (true) {
            // welcome message
            int type = Welcome();

            // judge to enter which game
            // if it is blackjack
            if (type == 1) {
                runner1.play();
                break;

                // if it is trianta ena
            } else {
                runner2 = new TERunner();
                runner2.play();
                break;
            }
        }
        // ask for another game
        System.out.println("Do you want to play another game?(0:no,1:yes)");
        int ans = scan.nextInt();
        if (ans == 1) {
            select();
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * Show the welcome message and the message while choosing the game.
     */
    public int Welcome() {
        System.out.println("Welcome to the game. Which game do you want to play?（1:BlackJack, 2:Trianta Ena）");
        int ans = 0;
        ans = scan.nextInt();
        while (ans != 1 && ans != 2) {
            System.out.println("The answer should be 1 or 2! Try again!");
            ans = scan.nextInt();
        }
        return ans;
    }
}
