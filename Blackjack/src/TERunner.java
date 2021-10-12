import java.util.ArrayList;
import java.util.Scanner;

public class TERunner extends Runner {
    public static final int BET = 100;

    public TERunner(){
        players = new ArrayList<TEPlayer>();
        deck = new Deck();
    }

    public void play(){
        round = 0;
        // preparation
        start();
        boolean end=false; // judge whether the game comes to an end
        while (true){
            // each round of the game
            end = each_round();
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

    @Override
    public boolean each_round() {
        System.out.printf("--- Starting round %d ---\n",round);
        dealer.cleanHand();
        for (TEPlayer player: players){
            player.cleanHand();
        }
        // dealer starts to deal cards
        deal();

        // player take actions in turn

        return true;
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
                    break;
                }
                System.out.println("Bet should be greater than 0 and smaller than your balance!");
            }
        // fold
        }else{
            player.fold = true;
        }
    }

}
