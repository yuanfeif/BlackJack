/**
 * @ClassName TEDealer
 * @Description This class is the dealer for trianta ena
 * @Author Peter Guo
 * @Date 2021/10/13
 */
public class TEDealer extends Dealer {

    // constructor
    public TEDealer(int id, String name, int balance) {
        super(id, name, balance);
    }

    /**
     * The dealer deals cards to players.
     */
    @Override
    public void deal(Player player, Hand hand, boolean known, Deck deck) {
        Card card = deck.getCard();
        // If the dealt card is face-down
        if (!known) {
            card.setKnown(false);
            System.out.printf("Player %s get a face down card.\n", player.getName());
            // If the dealt card is face-up
        } else {
            System.out.printf("Player %s get a face up card.\n", player.getName());
        }
        hand.addCard(card);
        System.out.printf("Player %s: %s\n", player.getName(), player.showhands());
    }

    /**
     * The dealer deals cards to itself.
     */
    @Override
    public void dealToDealer(Dealer dealer, Hand hand, boolean known, Deck deck) {
        Card card = deck.getCard();
        // If the dealt card is face-down
        if (!known) {
            card.setKnown(false);
            System.out.printf("Dealer %s get a face down card.\n", dealer.getName());
            // If the dealt card is face-up
        } else {
            System.out.printf("Dealer %s get a face up card.\n", dealer.getName());
        }
        hand.addCard(card);
        System.out.printf("Dealer %s: %s\n", dealer.getName(), dealer.showhands());
    }

    /**
     * The dealer hits because of lower than 27.
     */
    @Override
    public void hit() {
        System.out.printf("Dealer %s hits!\n", this.getName());
    }

    /**
     * The dealer turns the face-down card up.
     */
    @Override
    public Card turnFace() {
        return null;
    }
}