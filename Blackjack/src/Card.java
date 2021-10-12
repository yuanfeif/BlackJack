public class Card{
    private int value;              // The face value of the card
    private int suit;               // The suit of the card
    private boolean known = true;   // The card is public(1) or private(0)
    private String[] suits = {"spade", "heart", "club", "diamond"};

    public Card(int value, int suit){
        this.suit = suit;
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public int getSuit(){
        return this.suit;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setSuit(int suit){
        this.suit = suit;
    }

    public boolean getKnown(){
        return known;
    }

    public void setKnown(boolean known){
        this.known = known;
    }

    /* Override toString() method to print the card */
    public String toString(){
        if (known){
            return suits[suit-1]+Integer.toString(value);
        }else{
            return "??";
        }
    }
}