import java.util.ArrayList;
import java.util.Scanner;

// run the whole process of the game
public abstract class Runner {
    public static Scanner scan = new Scanner(System.in);     // scan the input
    protected int round;                                     // record the round number
    public Deck deck;                                        // the deck of the game

    /**
     * @Description: This method runs a game
     * @Param: None
     * @Return: void
     */
    public abstract void play();

    /**
     * @Description: This method is the preparation of a game
     * @Param: None
     * @Return: void
     */
    public abstract void start();

    /**
     * @Description: This method represents each round of a game.
     * @Param: None
     * @Return: void
     */
    public abstract boolean eachRound();

    /**
     * @Description: This method creates players when the game starts.
     * @Param: None
     * @Return: void
     */
    public abstract void createPlayers();


    /**
     * @Description: This method shows the final result including balance of each player.
     * @Param: None
     * @Return: void
     */
    public abstract void showBalance();
}
