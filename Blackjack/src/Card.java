/**
 * @ClassName Card
 * @Description This class is in charge of game cards
 * @Author Andy Sheng
 * @Date 2021/10/12
 */

public class Card {

    /**
     * The face value of the card
     */
    private int value;

    /**
     * The suit of the card
     */
    private int suit;

    /**
     * The card is public(1) or private(0)
     */
    private boolean known = true;

    /**
     * Used for toString method
     */
    private String[] suits = {"\033[10m\u2660\033[0m", "\033[31m\u2665\033[0m", "\033[10m\u2663\033[0m", "\033[31m\u2666\033[0m"};

    // constructor
    public Card(int value, int suit) {
        this.suit = suit;
        this.value = value;
    }

    // get the card value
    public int getValue() {
        return this.value;
    }

    // get the card suit
    public int getSuit() {
        return this.suit;
    }

    // set the card value
    public void setValue(int value) {
        this.value = value;
    }

    // set the card suit
    public void setSuit(int suit) {
        this.suit = suit;
    }

    // get whether the card is known
    public boolean getKnown() {
        return known;
    }

    // set the card to be known
    public void setKnown(boolean known) {
        this.known = known;
    }

    // Override toString() method to print the card
    public String toString() {
        return suits[suit - 1] + Integer.toString(value);
    }

//    /* Override toString() method to print the card */
//    public String toString(){
//        if (known){
//            return suits[suit-1]+Integer.toString(value);
//        }else{
//            return "??";
//        }
//    }
}