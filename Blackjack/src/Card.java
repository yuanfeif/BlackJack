// the card in the game
public class Card{
    private int value;              // The face value of the card
    private int suit;               // The suit of the card
    private boolean known = true;   // The card is public(1) or private(0)
    private String[] suits = {"spade", "heart", "club", "diamond"}; // used for toString

    // constructor
    public Card(int value, int suit){
        this.suit = suit;
        this.value = value;
    }

    // get the card value
    public int getValue(){
        return this.value;
    }

    // get the card suit
    public int getSuit(){
        return this.suit;
    }

    // set the card value
    public void setValue(int value){
        this.value = value;
    }

    // set the card suit
    public void setSuit(int suit){
        this.suit = suit;
    }

    // get whether the card is known
    public boolean getKnown(){
        return known;
    }

    // set the card to be known
    public void setKnown(boolean known){
        this.known = known;
    }

    /* Override toString() method to print the card */
    public String toString(){
        return suits[suit-1]+Integer.toString(value);
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