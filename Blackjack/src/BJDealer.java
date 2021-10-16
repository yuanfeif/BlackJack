/**
 * @ClassName BJDealer
 * @Description
 * @Author Vincent Yuan
 * @Date 2021/10/14 21:15
 */
public class BJDealer extends Dealer {
    public BJDealer(int id, String name, int balance) {
        super(id, name, balance);
    }

    /**
     * @Description BlackJack dealer deals cards to the player
     * @param player
     * @param hand
     * @param known
     * @param deck
     * @Return void
     */
    @Override
    public void deal(Player player, Hand hand, boolean known, Deck deck) {
        Card card = deck.getCard();
        if (!known) {
            card.setKnown(false);
            System.out.printf("Player %s get a face down card.\n", player.getName());
        } else {
            System.out.printf("Player %s get a face up card. The card is: " + card.toString() + "\n", player.getName());
        }
        hand.addCard(card);
    }

    /**
     * @Description BlackJack dealer deals cards to himself
     * @param dealer
     * @param hand
     * @param known
     * @param deck
     * @Return void
     */
    @Override
    public void dealToDealer(Dealer dealer, Hand hand, boolean known, Deck deck) {
        Card card = deck.getCard();
        if (!known) {
            card.setKnown(false);
            System.out.println("Dealer gets a face down card.");
        } else {
            System.out.println("Dealer gets a face up card. The card is: " + card.toString());
        }
        hand.addCard(card);
    }

    @Override
    public void hit() {

    }

    @Override
    public Card turnFace() {
        return null;
    }
}
