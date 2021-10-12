import java.util.ArrayList;
import java.util.Scanner;

public class BJDealer extends Dealer{
    private Scanner scan;

    public BJDealer(){}

    public BJDealer(int id, String name, int balance){
        super(id, name, balance);
        scan = new Scanner(System.in);
    }

    @Override
    public Card deal(){
        if (deck.getDeck().size() == 0) {
            deck.shuffle();
        }
        return deck.getDeck().remove(0);
    }

    @Override
    public void hit() {
        Dhand.addCard(deal());
    }

    @Override
    public void turnFace(){
        for (Card card: Dhand){
            card.setKnown(true);
        }
    }


}