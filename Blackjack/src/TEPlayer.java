import java.util.ArrayList;

/**
 * @ClassName TEplayer
 * @Description This class is in charge of players of Trianta Ena
 * @Author Peter Guo
 * @Date 2021/10/13
 */

public class TEPlayer extends Player {
    /**
     * Whether the player chooses to fold
     */
    public boolean fold = false;

    // constructor
    public TEPlayer(int id, String name, int balance) {
        super(id, name, balance);
    }

    /**
     * The player chooses to bet
     */
    public void bet(Hand curHand) {}

    /**
     * The player chooses to hit
     */
    @Override
    public void hit(Dealer dealer, Deck deck, Hand hand) {
        System.out.printf("Player %s hits!\n", this.getName());
    }

    /**
     * The player chooses to stand
     */
    @Override
    public void stand(Hand hand) {
        System.out.printf("Player %s stands!\n", this.getName());
        System.out.printf("Player %s: %s\n", this.getName(), showhands());
    }

    /**
     * The player chooses to double up
     */
    @Override
    public void doubleUp(Hand curHand) {
    }

    /**
     * Remove all the cards of the player
     */
    public void cleanHand() {
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
        this.fold = false;
    }
}
