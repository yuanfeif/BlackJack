public class Card{
    private int value;              // The face value of the card
    private int suit;               // The suit of the card
    private boolean known = true;   // The card is public(1) or private(0)

    public Card(int value, int suit){
        this.face = face;
        this.value = value;
    }

    public int getValue(){
        return this.face;
    }

    public int getSuit(){
        return this.value;
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
        return "Card{ " + suit + ',' + value ' }';
    }
}