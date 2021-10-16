import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ClassName TEclient
 * @Description This class is the client for trianta ena
 * @Author Peter Guo
 * @Date 2021/10/13
 */
public class TEClient extends Client {

    /**
     * Check if there is a winner
     */
    @Override
    public void checkWin(Dealer dealer, ArrayList<? extends Player> players) {
        // If the dealer is busted
        if (dealer.getHand().getIsBusted()) {
            for (Player tmpPlayer : players) {
                TEPlayer player = (TEPlayer) tmpPlayer;

                // If the player still in the game without busted, it wins
                if (!player.fold && !player.hand.get(0).getIsBusted()) {
                    player.balance += 2 * player.getHand().get(0).getBet();
                    dealer.Dbalance -= player.getHand().get(0).getBet();
                }
            }
            // If the dealer is not busted
        } else {
            for (Player tmpPlayer : players) {
                TEPlayer player = (TEPlayer) tmpPlayer;

                // If the player still in the game without busted
                if (!player.fold && !player.hand.get(0).getIsBusted()) {
                    // If the dealer wins with a higher value
                    if (compare(player.hand.get(0), dealer.getHand()) <= 0) {
                        dealer.Dbalance += player.getHand().get(0).getBet();
                        // If player wins with a higher value
                    } else {
                        player.balance += 2 * player.getHand().get(0).getBet();
                        dealer.Dbalance -= player.getHand().get(0).getBet();
                    }
                }
            }
        }
    }
}
