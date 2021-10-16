import java.util.ArrayList;
import java.util.Scanner;

// run the whole process of the game
public abstract class Runner {
    public static Scanner scan = new Scanner(System.in);     // scan the input
    protected int round;                                     // record the round number
    public Deck deck;                                        // the deck of the game

    // play the game
    public abstract void play();

    // preparation
    public abstract void start();

    // each round
    public abstract boolean eachRound();

    /**
     * @Description: This method creates players when the game starts.
     * @Param: None
     * @Return: void
     */
    public abstract void createPlayers();

    public abstract void showBalance();
}
