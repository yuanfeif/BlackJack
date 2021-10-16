import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @ClassName TERunner
 * @Description It is the class running the whole process of the game Trianta Ena.
 * @Author Peter Guo
 * @Date 2021/10/13
 */
public class TERunner extends Runner {

    /**
     * Initial bet amount (3*BET for dealer)
     */
    public static final int BET = 100;

    /**
     * Maximum value of a hand without busted
     */
    public static final int MAX_VALUE = 31;

    /**
     * Client of the game
     */
    public TEClient client;

    /**
     * All the players in a game
     */
    public ArrayList<TEPlayer> players;

    /**
     * The dealer of a game
     */
    public TEDealer dealer;

    // constructor
    public TERunner() {
        players = new ArrayList<TEPlayer>();
        deck = new Deck();
        client = new TEClient();
        round = 0;
    }

    /**
     * The main process of the game.
     */
    @Override
    public void play() {
        // Start the game by showing the welcome message first
        start();

        // Whether the game comes to an end
        boolean end = false;
        while (true) {
            // Each round of the game
            end = eachRound();

            // If there is someone out of money
            if (end) {
                break;
            }
        }

    }

    /**
     * Start the game by showing welcome message and generating players
     */
    @Override
    public void start() {
        System.out.println("Welcome to the Trianta Ena Game!");
        System.out.println("Each player has 100 dollars as initial bet and banker will have 300 dollars!");
        createPlayers();
    }

    /**
     * This mothod is in charge of each round in a game.
     */
    @Override
    public boolean eachRound() {
        round += 1;
        System.out.printf("--- Starting round %d ---\n", round);
        // initialize the player and the dealer by clearing their cards
        dealer.cleanHand();
        for (TEPlayer player : players) {
            player.cleanHand();
        }

        // dealer starts to deal cards
        deal();

        // player take actions in turn
        for (TEPlayer player : players) {
            if (!player.fold) {
                playerturn(player);
            }
        }

        // dealer takes action afterwards
        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
        System.out.printf("Dealer %s: %s\n", dealer.getName(), dealer.showhands());

        // while the hand value is smaller than 27, hit automatically
        while (dealer.getHand().getHandValue() < 27) {
            dealer.hit();
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }

            dealer.dealToDealer(dealer, dealer.getHand(), true, deck);
            // judge whether it is busted
            if (dealer.getHand().getHandValue() > MAX_VALUE) {
                dealer.getHand().setIsBusted();
                System.out.printf("Dealer %s, you are busted\n", dealer.getName());
                break;
            }
        }

        // use sleep to make the information shown clearly
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }


        // print the current player hand
        System.out.println("The hand of the current players are as follows:");
        for (TEPlayer player : players) {
            System.out.printf("Player %s: %s\n", player.getName(), player.showhands());
        }
        System.out.printf("Dealer %s: %s\n", dealer.getName(), dealer.showhands());

        // check winning
        client.checkWin(dealer, players);

        // print the current
        showBalance();

        // use sleep to make the information shown clearly
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }

        // judge whether there is some player out of money
        for (TEPlayer player : players) {
            if (player.balance <= 0) {
                return true;
            }
        }

        // judge whether the dealer is out of money
        if (dealer.Dbalance <= 0) {
            return true;
        }

        // change the banker if necessary at the end of each round
        change_dealer();

        return false;
    }

    /**
     * This mothod generates players.
     */
    @Override
    public void createPlayers() {
        // set the number of players
        System.out.println("How many players do you want?");
        int player_num = scan.nextInt();
        String player_name;

        // generate players with specific names
        for (int i = 1; i <= player_num; i++) {
            System.out.println("Enter the name of Player " + i);
            player_name = scan.next();
            players.add(new TEPlayer(i, player_name, BET));
        }

        // generate the dealer with name
        System.out.println("Who is the dealer?");
        player_name = scan.next();
        dealer = new TEDealer(player_num + 1, player_name, 3 * BET);
    }

    /**
     * This mothod deals cards to every players on the table.
     */
    public void deal() {
        // dealer starts to deal the cards
        System.out.printf("%s is dealing cards...\n", dealer.getName());

        // deal the first card to players
        for (Player player : players) {
            dealer.deal(player, player.hand.get(0), false, deck);
        }

        // deal the first card to the dealer
        dealer.dealToDealer(dealer, dealer.getHand(), true, deck);

        // check whether the player wants to fold or bet
        for (TEPlayer player : players) {
            bet_fold(player);
        }

        // deal the second and the three card
        for (TEPlayer player : players) {
            if (!player.fold) {
                dealer.deal(player, player.hand.get(0), true, deck);
                dealer.deal(player, player.hand.get(0), true, deck);
            }
        }
    }

    /**
     * This mothod is in charge of player betting/folding.
     */
    void bet_fold(TEPlayer player) {
        System.out.printf("Player %s, Do you want to bet or fold?(0:fold, 1:bet)\n", player.getName());
        int ans = scan.nextInt();
        // if chooses betting, set the money
        if (ans == 1) {
            System.out.println("How much do you want to bet?");
            while (true) {
                ans = scan.nextInt();
                // bet must between 0 and balance
                if (ans > 0 && ans <= player.balance) {
                    player.hand.get(0).setBet(ans);
                    player.balance -= ans;
                    break;
                }
                System.out.println("Bet should be greater than 0 and smaller than your balance!");
            }
            // fold is chosen
        } else {
            player.fold = true;
        }
    }


    /**
     * This mothod is in charge of player's action in their turn.
     */
    public void playerturn(TEPlayer player) {
        while (true) {
            System.out.printf("Player %s: %s\n", player.getName(), player.showhands());
            System.out.printf("Player %s, which action do you want to take?(0:hit,1:stand)\n", player.getName());
            int ans = scan.nextInt();

            // player chooses to hit
            if (ans == 0) {
                player.hit(dealer, deck, player.hand.get(0));
                dealer.deal(player, player.hand.get(0), true, deck);

                // player chooses to stand
            } else {
                player.stand(player.hand.get(0));
                break;
            }

            // check whether the player is busted
            if (player.hand.get(0).getHandValue() > MAX_VALUE) {
                player.hand.get(0).setIsBusted();
                System.out.printf("Player %s, you are busted\n", player.getName());
                dealer.Dbalance += player.hand.get(0).getBet();
                break;
            }
        }
    }

    /**
     * This mothod shows the balance of players and the dealer.
     */
    @Override
    public void showBalance() {
        for (TEPlayer player : players) {
            System.out.printf("Player %s: %d\n", player.getName(), player.balance);
        }
        System.out.printf("Dealer %s: %d\n", dealer.getName(), dealer.Dbalance);
        System.out.println();
    }

    /**
     * This mothod is responsible for changing the banker if someone has the money exceeding that of the banker
     */
    public void change_dealer() {
        // make a copy of the current players
        ArrayList<TEPlayer> new_players = new ArrayList<TEPlayer>();
        for (TEPlayer player : players) {
            new_players.add(player);
        }

        // sort the new_players according to their balance
        Collections.sort(new_players, new playerComparator());
        for (TEPlayer player : new_players) {
            // if the balance is smaller than dealer
            if (player.balance <= dealer.Dbalance) {
                break;

                // if the balance is bigger than dealer, it can choose to be banker or not
            } else {
                System.out.printf("Player %s has the cash amount exceeding that of the Banker %s\n", player.getName(), dealer.getName());
                System.out.printf("Player %s, Do you want to be the banker?(0:no,1:yes)\n", player.getName());
                int ans = scan.nextInt();
                // if it wants to be the banker, do the switch
                if (ans == 1) {
                    players.remove(player);
                    players.add(new TEPlayer(dealer.getId(), dealer.getName(), dealer.Dbalance));
                    dealer = new TEDealer(player.getId(), player.getName(), player.balance);
                    return;
                }
            }
        }
    }

    /**
     * This mothod compares the balance of two players by overriding the compare() method
     */
    public class playerComparator implements Comparator<TEPlayer> {
        @Override
        public int compare(TEPlayer p1, TEPlayer p2) {
            return (p2.balance - p1.balance);
        }
    }
}
