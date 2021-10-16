import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName Runner
 * @Description It is the abstract class running the whole process of the game.
 * @Author Peter Guo
 * @Date 2021/10/13
 */
public abstract class Runner {

    /**
     * Scanner to scan the input
     */
    public static Scanner scan = new Scanner(System.in);

    /**
     * The round counter
     */
    protected int round;

    /**
     * The deck of cards
     */
    public Deck deck;

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
