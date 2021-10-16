import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName Hand
 * @Description This class is in charge of the hand of players
 * @Author Andy Sheng
 * @Date 2021/10/12
 */
public class Hand {

    /**
     * The maximum of the hand value
     */
    public static int MAX_HV;

    /**
     * The cards consisting of the hand
     */
    private ArrayList<Card> cards;

    /**
     * Every hand is along with a bet
     */
    private int bet;

    /**
     * Whether the hand is busted
     */
    private boolean isBusted;

    /**
     * Whether there is an Ace is count as 1
     */
    public boolean Ace = false;

    /**
     * Whether there is an Ace
     */
    public boolean hasAce = false;

    /**
     * Whether the game is a draw
     */
    private boolean isStand;

    // constructor
    public Hand() {
        this.cards = new ArrayList<Card>();
        this.isBusted = false;
        this.isStand = false;
    }

    // set the max value
    public static void setMax(int max) {
        MAX_HV = max;
    }

    // get all the cards in a hand
    public ArrayList<Card> getCard() {
        return cards;
    }

    // get the bet of the hand
    public int getBet() {
        return bet;
    }

    // set the bet of the hand
    public void setBet(int bet) {
        this.bet = bet;
    }

    // check if the hand is busted
    public boolean getIsBusted() {
        return this.isBusted;
    }

    // set the hand to be busted
    public void setIsBusted() {
        isBusted = true;
    }

    // add the card to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    // check if the player stands
    public boolean getIsStand() {
        return isStand;
    }

    // set the player to stand
    public void setIsStand() {
        isStand = true;
    }
  
    // get the number of cards in a hand
    public int getHandSize() {
        return cards.size();
    }

    /**
     * Calculate the sum of the card value in a hand.
     */
    public int getHandValue() {
        Scanner scan = new Scanner(System.in);

        // Player chooses to count Ace as 1(1) or 11(0)
        int ans = -1;

        // Record the face value of the hand
        int handValue = 0;

        for (Card card : cards) {
            // If it is J,Q,K, the value is 10
            if (card.getValue() >= 11 && card.getValue() <= 13) {
                handValue += 10;
                // If it is A, the player has to choose the value
            } else if (card.getValue() == 1) {
                hasAce = true;
                handValue += 11;
                if (Ace == false) {
                    System.out.println("Do you want to use Ace as face value 1?(0:no,1:yes)");
                    ans = scan.nextInt();
                    if (ans == 1) {
                        Ace = true;
                    }
                }
                // Otherwise, the face value is itself
            } else {
                handValue += card.getValue();
            }
        }

        // If the Ace is counted as 1
        if (Ace) {
            handValue -= 10;
        }

        return handValue;
    }

    /**
     * calculate the sum of the card value in a hand in the blackjack game
     */
    public int getBJHandValue() {
        // Set the maximum value of a hand as 21
        setMax(21);

        // The value of the hand
        int result = 0;

        // The number of Ace in the hand
        int numOfA = 0;

        for (Card card : cards) {
            // If it is J,Q,K, the value is 10
            if (card.getValue() >= 11 && card.getValue() <= 13) {
                result += 10;
                // If it is A, the value is 11
            } else if (card.getValue() == 1) {
                result += 11;
                numOfA++;
                // Otherwise, the face value is itself
            } else {
                result += card.getValue();
            }
        }

        // When counting Ace as 11 leads to a bust, transfer Ace to 1
        while (result > MAX_HV && numOfA > 0) {
            result -= 10;
            numOfA--;
        }
        return result;
    }

    /**
     * Override the toString() method to convert all the cards in a hand to string
     */
    public String toString() {
        String str = "[";
        for (Card card : cards) {
            str += "[" + card.toString() + "]";
        }
        return str + "]";
    }

    /**
     * Check whether the hand can be splited
     */
    public boolean canBeSplited() {
        return (cards.size() == 2) && (cards.get(0).getValue() == cards.get(1).getValue());
    }
}