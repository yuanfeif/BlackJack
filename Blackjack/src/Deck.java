/* This class is the card deck, which is consist of 52 different cards
*  The value of the cards vary from 1 to 13.
*  The suit are represented as integers 1(spade), 2(heart), 3(club), 4(diamond) */

import java.util.*;

public class Deck{
    private ArrayList<Card> cards;              // Use an arraylist to store a deck of cards

    public Deck(){
        this.cards = new ArrayList<>();
        for (int i = 1; i < 14; i++){           // 1-13 represent the face value from A to K
            for (int j = 1; j < 5; j++){        // 1-4 represent the suit(explained at the top)
                cards.add(new Card(i,j));
            }
        }
        // shuffle the cards
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getDeck(){
        return cards;
    }

    // draw a card from the deck
    public Card getCard(){
        // if all the cards are used, shuffle it
        if (cards.size() == 0){
            shuffle();
        }
        return cards.remove(0);
    }

    //shuffle the card
    public void shuffle(){
        cards.clear();
        for (int i = 1; i < 14; i++){
            for (int j = 1; j < 5; j++){
                cards.add(new Card(i,j));
            }
        }
        Collections.shuffle(cards);
    }

}