import java.util.ArrayList;

public class TEPlayer extends Player {
    public boolean fold = false;     // whether the player is fold

    // constructor
    public TEPlayer(int id, String name, int balance) {
        super(id, name, balance);
    }

    @Override
    public void bet(Hand curHand) {

    }

    // hit
    @Override
    public void hit(Dealer dealer, Deck deck, Hand hand) {
        System.out.printf("Player %s hits!\n", this.getName());
    }

    // stand
    @Override
    public void stand(Hand hand) {
        System.out.printf("Player %s stands!\n", this.getName());
        System.out.printf("Player %s: %s\n", this.getName(), showhands());
    }

    @Override
    public void doubleUp(Hand curHand) {
    }

    // remove all the cards in a hand
    public void cleanHand() {
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
        this.fold = false;
    }
}
