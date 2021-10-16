import java.util.ArrayList;

/**
 * @ClassName BJClient
 * @Description
 * @Author Vincent Yuan
 * @Date 2021/10/14 21:15
 */
public class BJClient extends Client {
    @Override
    public void checkWin(Dealer dealer, ArrayList<? extends Player> players) {
        boolean isDealerBlackJack = isBlackJack(dealer.getHand());
        boolean isDealerBust = dealer.getHand().getIsBusted();
        int dealerHandValue = 0;
        if (!isDealerBust) {
            dealerHandValue = dealer.getHand().getBJHandValue();
        }
        for (Player player : players) {
            BJPlayer tmpPlayer = (BJPlayer) player;
            for (Hand h : tmpPlayer.getHand()) {
                boolean isHandBlackJack = isBlackJack(h);
                boolean isHandBust = h.getIsBusted();
                boolean isHandStand = h.getIsStand();
                int handValue = 0;
                if (!isHandBust) {
                    handValue = h.getBJHandValue();
                }

                //player wins
                //player is blackjack, but dealer is not
                if (isHandBlackJack && !isDealerBlackJack) {
                    tmpPlayer.balance += h.getBet() * 2;
                    continue;
                }
                //player is not bust, but dealer is bust
                if (isDealerBust && !isHandBust) {
                    tmpPlayer.balance += h.getBet() * 2;
                    continue;
                }
                //player and dealer are not bust, player's hand value is larger than dealer's
                if (!isDealerBust && !isHandBust && (handValue > dealerHandValue)) {
                    tmpPlayer.balance += h.getBet() * 2;
                }
            }
        }
    }

    public boolean isBlackJack(Hand hand) {
        return hand.getBJHandValue() == 21 && hand.getCard().size() == 2;
    }
}
