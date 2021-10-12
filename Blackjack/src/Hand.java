import java.util.ArrayList;

public class Hand{
    public static int MAX_HV;               // The maximum of the hand value
    private ArrayList<Card> cards;          // The cards consisting of the hand
    private boolean isBusted;               // Whether the hand is busted

    public Hand() {
        this.cards = new ArrayList<Card>();
        this.isBusted = false;
    }

    public static void setMax(int max){
        MAX_HV = max;
    }

    public ArrayList<Card> getCard(){
        return cards;
    }

    public boolean getIsBusted(){
        return this.isBusted;
    }

    public void setIsBusted(){
        isBusted = true;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public int getHandValue(){
        int handValue = 0;
        int numOfA = 0;
        for (Card card: cards){
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

    public int getHandSize(){
        return cards.size();
    }

    public String toString(){
        String str = "[";
        for (Card card: cards){
            str += "["+card.toString()+"]";
        }
        return str + "]";
    }
}