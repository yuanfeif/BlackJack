public class TEDealer extends Dealer{

    public TEDealer(int id, String name, int balance){
        super(id,name,balance);
    }

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
        System.out.printf("Player %s: %s",player.getName(),player.showhands());
    }

    public void deal_dealer(Dealer dealer,Hand hand,boolean known, Deck deck){
        Card card = deck.getCard();
        if (!known){
            card.setKnown(false);
            System.out.printf("Dealer %s get a face down card.\n",dealer.getName());
        }else{
            System.out.printf("Dealer %s get a face up card.\n",dealer.getName());
        }
        hand.addCard(card);
        System.out.printf("Dealer %s: %s",dealer.getName(),dealer.showhands());
    }

    @Override
    public void hit(Dealer dealer,Hand hand,boolean known, Deck deck) {
        deal_dealer(dealer,hand,known,deck);
    }

    @Override
    public void turnFace() {
        for (Card card: Dhand.getCard()){
            card.setKnown(true);
        }
    }
}