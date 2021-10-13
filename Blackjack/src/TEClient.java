import java.util.ArrayList;
import java.util.Comparator;

// client for trianta ena
public class TEClient extends Client  {
    @Override
    public void check_win(TEDealer dealer, ArrayList<TEPlayer> players) {
        // dealer is busted
        if (dealer.getHand().getIsBusted()){
            for (TEPlayer player:players){
                if (!player.fold && !player.hand.get(0).getIsBusted()){
                    player.balance += 2*player.bet;
                    dealer.Dbalance -= player.bet;
                }
            }
        // dealer is not busted
        }else{
            for (TEPlayer player:players){
                if (!player.fold && !player.hand.get(0).getIsBusted()){
                    // if dealer wins
                    if (compare(player.hand.get(0),dealer.getHand())<=0){
                        dealer.Dbalance += player.bet;
                    // if player wins
                    }else{
                        player.balance += 2*player.bet;
                        dealer.Dbalance -= player.bet;
                    }
                }
            }
        }
    }
}
