import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// runner for the trianta ena
public class TERunner extends Runner {
    public static final int BET = 100;         // initial bet amount (3*BET for dealer)
    public static final int MAX_VALUE = 31;    // maximum value to be not busted
    public TEClient client;                    // client of the game

    // constructor
    public TERunner(){
        players = new ArrayList<TEPlayer>();
        deck = new Deck();
        client = new TEClient();
        round = 0;
    }

    // play the game
    public void play(){
        // preparation
        start();
        boolean end=false; // judge whether the game comes to an end
        while (true){
            // each round of the game
            end = each_round();
            // if there is someone out of money
            if (end){
                break;
            }
        }

    }

    // prepartaion
    public void start(){
        System.out.println("Welcome to the Trianta Ena Game!");
        System.out.println("Each player has 100 dollars as initial bet and banker will have 300 dollars!");
        Create_Players();
    }

    // each round of the game
    @Override
    public boolean each_round() {
        round += 1;
        System.out.printf("--- Starting round %d ---\n",round);
        // remove all the cards in a hand/ initialize the player and the dealer
        dealer.cleanHand();
        for (TEPlayer player: players){
            player.cleanHand();
        }
        // dealer starts to deal cards
        deal();

        // player take actions in turn
        for (TEPlayer player: players){
            if (!player.fold){
                playerturn(player);
            }
        }

        // dealer takes action
        try
        {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        System.out.printf("Dealer %s: %s\n",dealer.getName(),dealer.showhands());
        // while the hand value is smaller than 27, hit!
        while (dealer.getHand().getHandValue()<27) {
            dealer.hit();
            try
            {
                Thread.sleep(5000);
            } catch (Exception e) {
            }

            dealer.deal_dealer(dealer, dealer.getHand(), true, deck);
            // judge whether it is busted
            if (dealer.getHand().getHandValue() > MAX_VALUE) {
                dealer.getHand().setIsBusted();
                System.out.printf("Dealer %s, you are busted\n", dealer.getName());
                break;
            }
        }

        // sleep
        try
        {
            Thread.sleep(5000);
        } catch (Exception e) {
        }


        // print the current player hand
        System.out.println("The hand of the current players are as follows:");
        for (TEPlayer player: players){
            System.out.printf("Player %s: %s\n",player.getName(),player.showhands());
        }
        System.out.printf("Dealer %s: %s\n",dealer.getName(),dealer.showhands());

        // check winning
        client.check_win(dealer,players);

        // print the current
        showbalance();

        // sleep
        try
        {
            Thread.sleep(5000);
        } catch (Exception e) {
        }

        // judge whether there is some player out of money
        for (TEPlayer player: players){
            if (player.balance <= 0){
                return true;
            }
        }

        if (dealer.Dbalance <= 0){
            return true;
        }

        // change the banker
        change_dealer();

        return false;
    }

    // create players
    public void Create_Players(){
        // add the players
        System.out.println("How many players do you want?");
        int player_num = scan.nextInt();
        String player_name;
        for (int i=1;i<=player_num;i++){
            System.out.println("Enter the name of Player "+i);
            player_name = scan.next();
            players.add(new TEPlayer(i,player_name,BET));
        }

        // add the dealer
        System.out.println("Who is the dealer?");
        player_name = scan.next();
        dealer = new TEDealer(player_num+1,player_name,3*BET);
    }

    // deal cards
    public void deal(){
        // deal the first card
        System.out.printf("%s is dealing cards...\n",dealer.getName());
        // to players
        for (Player player:players){
            dealer.deal(player,player.hand.get(0),false,deck);
        }
        // to the dealer
        dealer.deal_dealer(dealer,dealer.getHand(),true,deck);
        // check whether the player wants to fold or bet
        for (TEPlayer player:players){
            bet_fold(player);
        }

        // deal the second and the three card
        for (TEPlayer player:players){
            if (!player.fold) {
                dealer.deal(player, player.hand.get(0), true, deck);
                dealer.deal(player, player.hand.get(0), true, deck);
            }
        }
    }

    // check whether bet or fold
    void bet_fold(TEPlayer player){
        System.out.printf("Player %s, Do you want to bet or fold?(0:fold, 1:bet)\n",player.getName());
        int ans = scan.nextInt();
        // bet the money
        if (ans == 1){
            System.out.println("How much do you want to bet?");
            while(true){
                ans = scan.nextInt();
                if(ans>0 && ans<=player.balance){
                    player.bet = ans;
                    player.balance -= ans;
                    break;
                }
                System.out.println("Bet should be greater than 0 and smaller than your balance!");
            }
        // fold
        }else{
            player.fold = true;
        }
    }

    // player take their action
    public void playerturn(TEPlayer player){
        while (true) {
            System.out.printf("Player %s: %s\n", player.getName(), player.showhands());
            System.out.printf("Player %s, which action do you want to take?(0:hit,1:stand)\n", player.getName());
            int ans = scan.nextInt();
            // hit
            if (ans == 0) {
                player.hit(deck);
                dealer.deal(player, player.hand.get(0), true, deck);
            // stand
            } else {
                player.stand();
                break;
            }
            // check whether the player is busted
            if (player.hand.get(0).getHandValue() > MAX_VALUE) {
                player.hand.get(0).setIsBusted();
                System.out.printf("Player %s, you are busted\n", player.getName());
                dealer.Dbalance+=player.bet;
                break;
            }
        }
    }

    // show the balance of players and the dealer
    public void showbalance(){
        for (TEPlayer player: players) {
            System.out.printf("Player %s: %d\n", player.getName(), player.balance);
        }
        System.out.printf("Dealer %s: %d\n", dealer.getName(), dealer.Dbalance);
        System.out.println();
    }

    // change the banker if someone has the money exceeding that of the banker
    public void change_dealer(){
        // a new copy of the players
        ArrayList<TEPlayer> new_players = new ArrayList<TEPlayer>();
        for (TEPlayer player: players){
            new_players.add(player);
        }
        // sort the new_players
        Collections.sort(new_players,new playerComparator());
        for (TEPlayer player: new_players){
            // if the balance is smaller than dealer
            if (player.balance <= dealer.Dbalance){
                break;
            // if the balance is bigger than dealer, ask him
            }else{
                System.out.printf("Player %s has the cash amount exceeding that of the Banker %s\n",player.getName(),dealer.getName());
                System.out.printf("Player %s, Do you want to be the banker?(0:no,1:yes)\n",player.getName());
                int ans = scan.nextInt();
                if (ans == 1){
                    players.remove(player);
                    players.add(new TEPlayer(dealer.getId(),dealer.getName(),dealer.Dbalance));
                    dealer = new TEDealer(player.getId(),player.getName(),player.balance);
                    return;
                }
            }
        }
    }

    // a new comparator for comparing the player's balance
    public class playerComparator implements Comparator<TEPlayer>{
        @Override
        public int compare(TEPlayer p1, TEPlayer p2) {
            return(p2.balance-p1.balance);
        }
    }
}
