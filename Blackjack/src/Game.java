import java.util.Random;
import java.util.Scanner;

// Select the game
public class Game {
    private static Scanner scan = new Scanner(System.in);  // scan the input

    private TERunner runner2;                              // Trianta runner

    // constructor
    public Game(){
        runner2 = new TERunner();
    }

    // select the game
    public void select(){
        while (true){
            // welcome message
            int type = Welcome();

            // judge to enter which game
            // if it is blackjack
            if (type == 1){

            // if it is trianta ena
            }else{
                runner2 = new TERunner();
                runner2.play();
                break;
            }
        }
        // ask for another game
        System.out.println("Do you want to play another game?(0:no,1:yes)");
        int ans = scan.nextInt();
        if (ans == 1){
            select();
        }
        System.out.println("Thanks for playing!");
    }

    // Welcome message and choose the game
    public int Welcome(){
        System.out.println("Welcome to the game. Which game do you want to play?（1:BlackJack, 2:Trianta Ena）");
        int ans = 0;
        ans = scan.nextInt();
        while (ans!=1 && ans!=2){
            System.out.println("The answer should be 1 or 2! Try again!");
            ans = scan.nextInt();
        }
        return ans;
    }
}
