import java.util.ArrayList;
import java.util.Scanner;

public class Hand{
    public static int MAX_HV;               // The maximum of the hand value
    private ArrayList<Card> cards;          // The cards consisting of the hand
    private boolean isBusted;               // Whether the hand is busted
    public boolean Ace = false;             // Whether there is an Ace is count as 1
    public boolean hasAce = false;          // Whether there is an Ace

    // constructor
    public Hand() {
        this.cards = new ArrayList<Card>();
        this.isBusted = false;
    }

    // set the max value
    public static void setMax(int max){
        MAX_HV = max;
    }

    // get all the cards in a hand
    public ArrayList<Card> getCard(){
        return cards;
    }

    // check if the hand is busted
    public boolean getIsBusted(){
        return this.isBusted;
    }

    // set the hand to be busted
    public void setIsBusted(){
        isBusted = true;
    }

    // add the card to the hand
    public void addCard(Card card){
        cards.add(card);
    }

    // calculate the sum of the card value in a hand
    public int getHandValue(){
        Scanner scan = new Scanner(System.in);
        int ans = -1;
        int handValue = 0;
        // for each card
        for (Card card: cards){
            // if it is J,Q,K, the value is 10
            if (card.getValue() >= 11 && card.getValue() <= 13) {
                handValue += 10;
            // if it is A, the player has to choose the value
            }else if(card.getValue()==1) {
                hasAce = true;
                handValue += 11;
                if (Ace == false){
                    System.out.println("Do you want to use Ace as face value 1?(0:no,1:yes)");
                    ans = scan.nextInt();
                    if (ans==1){
                        Ace = true;
                    }
                }
            // otherwise, the face value is itself
            }else{
                handValue += card.getValue();
            }
        }

        if (Ace){
            handValue -= 10;
        }

        return handValue;
    }

    // get the number of cards in a hand
    public int getHandSize(){
        return cards.size();
    }

    // convert all the cards in a hand to string
    public String toString(){
        String str = "[";
        for (Card card: cards){
            str += "["+card.toString()+"]";
        }
        return str + "]";
    }
}