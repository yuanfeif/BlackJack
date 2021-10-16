import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ClassName Client
 * @Description It is the client used to judge who is the winner and distribute the money.
 * @Author Peter Guo
 * @Date 2021/10/13
 */
public abstract class Client implements Comparator<Hand> {

    /**
     * @Description: This method checks if there is a winner.
     * @Param: Dealer dealer, ArrayList<? extends Player> players
     * @Return: void
     */
    public abstract void checkWin(Dealer dealer, ArrayList<? extends Player> players);

    /**
     * @Description: This method compares the two hands by overriding the compare() method.
     * @Param: Hand h1, Hand h2
     * @Return: int
     */
    public int compare(Hand h1, Hand h2) {
        // they are not the same value
        if (h1.getHandValue() != h2.getHandValue()) {
            return (h1.getHandValue() - h2.getHandValue());

            // they are the same value
        } else {
            // there is no possibility of natural trianta
            if (h1.getHandValue() != 31) {
                return 0;

                // There might be a natural trianta
            } else {
                // if h1 is a natural trianta
                if (h1.Ace) {
                    // if h2 is a natural trianta
                    if (h2.Ace) {
                        return 0;
                        // if h2 is not a natural trianta
                    } else {
                        return 1;
                    }
                    // if h1 is not a natural trianta
                } else {
                    if (h2.Ace) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }
}
