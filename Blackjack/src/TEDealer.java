public class TEDealer extends Dealer{

    // constructor
    public TEDealer(int id, String name, int balance){
        super(id,name,balance);
    }

    // deal cards to players
    @Override
    public void deal(Player player, Hand hand, boolean known, Deck deck) {
        Card card = deck.getCard();
        if (!known){
            card.setKnown(false);
            System.out.printf("Player %s get a face down card.\n",player.getName());
        }else{
            System.out.printf("Player %s get a face up card.\n",player.getName());
        }
        hand.addCard(card);
        System.out.printf("Player %s: %s\n",player.getName(),player.showhands());
    }

    // deal cards to the dealer
    public void deal_dealer(Dealer dealer,Hand hand,boolean known, Deck deck){
        Card card = deck.getCard();
        if (!known){
            card.setKnown(false);
            System.out.printf("Dealer %s get a face down card.\n",dealer.getName());
        }else{
            System.out.printf("Dealer %s get a face up card.\n",dealer.getName());
        }
        hand.addCard(card);
        System.out.printf("Dealer %s: %s\n",dealer.getName(),dealer.showhands());
    }

    @Override
    public void hit() {
        System.out.printf("Dealer %s hits!\n",this.getName());
    }

    @Override
    public Card turnFace() {
        return null;
    }


}