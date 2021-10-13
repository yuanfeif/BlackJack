import java.util.ArrayList;
import java.util.Scanner;

// run the whole process of the game
public abstract class Runner {
    public ArrayList<TEPlayer> players;                      // all the players in a game
    public TEDealer dealer;                                  // the dealer
    public static Scanner scan = new Scanner(System.in);     // scan the input
    protected int round;                                     // record the round number
    public Deck deck;                                        // the deck of the game

    // play the game
    public abstract void play();

    // preparation
    public abstract void start();

    // each round
    public abstract boolean each_round();
}
