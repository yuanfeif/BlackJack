import java.util.ArrayList;
import java.util.Scanner;

public abstract class Runner {
    public ArrayList<TEPlayer> players;
    public TEDealer dealer;
    public static Scanner scan = new Scanner(System.in);
    public int round;
    public Deck deck;

    // play the game
    public abstract void play();

    // prepartaion
    public abstract void start();

    // each round
    public abstract boolean each_round();
}
