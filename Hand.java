import java.util.ArrayList;

public class Hand{
    public static int MAX_HV;               // The maximum of the hand value
    private ArrayList<Card> hand;           // The cards consisting of the hand
    private boolean isBJ;                   // Whether the hand is blackjack
    private boolean isBusted;               // Whether the hand is busted
    private boolean isSplit;                // Whether the hand is splitable

    public Hand() {
        this.hand = new ArrayList<Card>();
        this.isBusted = false;
        this.isBJ = false;
        this.isSplit = false;
    }

    public static void setMax(int max){
        MAX_HV = max;
    }

    public ArrayList<Card> getCard(){
        return hand;
    }

    public boolean getIsBusted(){
        setIsBusted();
        return this.isBusted;
    }

    public boolean getIsBJ(){
        setIsBJ();
        return this.isBJ;
    }

    public boolean getIsSplit(){
        setIsSplit();
        return this.isSplit;
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public int getHandValue(){
        int handValue = 0;
        int numOfA = 0;
        for (Card card: hand){
            if (card.getValue() >= 11 && card.getValue() <= 13) {
                handValue += 10;
            }else {
                handValue += card.getValue();
                if (card.getValue() == 1){
                    numOfA++;
                    handValue += 10;
                }
            }
        }
        while(handValue > MAX_HV && numOfA > 0){
            handValue -= 10;
            numOfA --;
        }

        return handValue;
    }

    public void setIsBusted(){
        if (getIsBusted() > 21) {
            isBusted = true;
        }
    }

    public void setIsBJ(){
        if (hand.size() == 2 && getHandValue() == 21){
            this.isBJ = true;
        }
    }

    public void setIsSplit(){
        if (hand.size() == 2 && hand.getCard()[0] == hand.getCard()[1]){
            this.isSplit = true;
        }
    }

    public int getHandSize(){
        return hand.size();
    }

}