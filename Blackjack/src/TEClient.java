import java.util.ArrayList;
import java.util.Comparator;

// client for trianta ena
public class TEClient extends Client {
    @Override
    public void checkWin(Dealer dealer, ArrayList<? extends Player> players) {
        // dealer is busted
        if (dealer.getHand().getIsBusted()) {
            for (Player tmpPlayer : players) {
                TEPlayer player = (TEPlayer) tmpPlayer;
                if (!player.fold && !player.hand.get(0).getIsBusted()) {
                    player.balance += 2 * player.getHand().get(0).getBet();
                    dealer.Dbalance -= player.getHand().get(0).getBet();
                }
            }
            // dealer is not busted
        } else {
            for (Player tmpPlayer : players) {
                TEPlayer player = (TEPlayer) tmpPlayer;
                if (!player.fold && !player.hand.get(0).getIsBusted()) {
                    // if dealer wins
                    if (compare(player.hand.get(0), dealer.getHand()) <= 0) {
                        dealer.Dbalance += player.getHand().get(0).getBet();
                        // if player wins
                    } else {
                        player.balance += 2 * player.getHand().get(0).getBet();
                        dealer.Dbalance -= player.getHand().get(0).getBet();
                    }
                }
            }
        }
    }
}
