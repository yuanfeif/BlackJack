import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName BJRunner
 * @Description This class runs the BlackJack game (including )
 * @Author Vincent Yuan
 * @Date 2021/10/14 21:14
 */
public class BJRunner extends Runner {

    /**
     * initial bet amount
     */
    public static final int BET = 100;

    /**
     * maximum value to be not busted
     */
    public static final int MAX_VALUE = 21;

    /**
     * dealer will stop hit when the hand value is larger than DEALER_STOP_VALUE
     */
    public static final int DEALER_STOP_VALUE = 17;

    /**
     * client of BlackJack game
     */
    public BJClient bjClient;

    /**
     * dealer of BlackJack game
     */
    public BJDealer bjDealer;

    /**
     * list of players of BlackJack game
     */
    public ArrayList<BJPlayer> bjPlayers;

    public BJRunner() {
        bjPlayers = new ArrayList<BJPlayer>();
        bjDealer = new BJDealer(0, "Dealer", Integer.MAX_VALUE);
        deck = new Deck();
        bjClient = new BJClient();
        round = 0;
    }

    /**
     * @Description This method runs a game
     * @Param None
     * @Return void
     */
    @Override
    public void play() {
        start();

        boolean isEnd = false;
        while (!isEnd) {
            isEnd = eachRound();
        }
    }

    /**
     * @Description This method is the preparation of a game
     * @Param None
     * @Return void
     */
    @Override
    public void start() {
        System.out.println("Welcome to the BlackJack Game!");
        System.out.println("Each player has 100 dollars as initial bet!");
        createPlayers();
    }

    /**
     * @Description This method creates players when the game starts.
     * @Param None
     * @Return void
     */
    @Override
    public void createPlayers() {
        // add the players
        System.out.println("How many players do you want?");
        int playerNum = scan.nextInt();
        String playerName;
        for (int i = 1; i <= playerNum; i++) {
            System.out.println("Enter the name of Player " + i);
            playerName = scan.next();
//            Player bjPlayer = new BJPlayer(i, playerName, BET);
            bjPlayers.add(new BJPlayer(i, playerName, BET));
        }
    }

    /**
     * @Description This method shows the final result including balance of each player.
     * @Param None
     * @Return void
     */
    @Override
    public void showBalance() {
        System.out.println("\n\n---------------------------------------\nFollowing are players balances!");
        for (BJPlayer player : bjPlayers) {
            System.out.printf("Player %s: %d\n", player.getName(), player.balance);
        }
        System.out.println();
    }

    /**
     * @Description This method represents each round of a game.
     * @Param None
     * @Return void
     */
    @Override
    public boolean eachRound() {
        round += 1;
        System.out.printf("--- Starting round %d ---\n", round);

        initiate();

        dealCards();

        playerTurn();

        dealerTurn();

        bjClient.checkWin(bjDealer, bjPlayers);

        showBalance();

        deletePlayersToPrepareForNextRound();

        if (bjPlayers.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description Initiate hands of players and dealer
     * @Param None
     * @Return void
     */
    public void initiate() {
        bjDealer.cleanHand();
        for (BJPlayer player : bjPlayers) {
            player.cleanHand();
        }
    }

    /**
     * @Description Dealer deals cards to players and himself at the very beginning of each round
     * @Param None
     * @Return void
     */
    public void dealCards() {
        for (int i = 0; i < 2; i++) {
            for (BJPlayer player : bjPlayers) {
                bjDealer.deal(player, player.getHand().get(0), true, deck);
            }
            if (i == 0) {
                bjDealer.dealToDealer(bjDealer, bjDealer.getHand(), true, deck);
            } else {
                bjDealer.dealToDealer(bjDealer, bjDealer.getHand(), false, deck);
            }
        }
    }

    /**
     * @Description Player turn of each round
     * @Param None
     * @Return void
     */
    public void playerTurn() {
        //each player plays in an order
        for (int i = 0; i < bjPlayers.size(); i++) {
            System.out.println("\n\n\n");
            BJPlayer player = bjPlayers.get(i);
            System.out.println("Player " + player.getName());
            System.out.println("Following is your hand: ");
            System.out.println(player.showhands());

            //player bets
            player.bet(player.getHand().get(0));

            //If player can split a hand, he can split.
            playerSplit(player);
            if (player.getHand().size() > 1) {
                System.out.println("Following are your cards in different hands: " + player.showhands());
            }

            //each player plays hands in an order
            for (int j = 0; j < player.getHand().size(); j++) {

                Hand curHand = player.getHand().get(j);
                System.out.println("Following is your Hand " + j);
                System.out.println(curHand.toString());

                //check if black jack exists
                if (curHand.getBJHandValue() == MAX_VALUE && curHand.getCard().size() == 2) {
                    System.out.println("Black Jack!!!");
                    continue;
                }

                //If not bust or stand, a player can keep playing.
                while (!(curHand.getIsBusted() || curHand.getIsStand())) {

                    System.out.println("Which action do you want to take?(0:hit,1:stand,2:double up)");

                    Scanner in = new Scanner(System.in);
                    int choice = in.nextInt();

                    while (choice < 0 || choice > 2) {
                        System.out.println("Please input(0-2)");
                        choice = in.nextInt();
                    }
                    switch (choice) {
                        //normal hit
                        case 0:
                            player.hit(bjDealer, deck, curHand);
                            System.out.println("Following is your Hand " + j);
                            System.out.println(curHand.toString());
                            if (curHand.getBJHandValue() > MAX_VALUE) {
                                curHand.setIsBusted();
                                System.out.println("Bust!");
                            } else if (curHand.getBJHandValue() == MAX_VALUE) {
                                System.out.println("You get the value of 21!");
                                player.stand(curHand);
                            }
                            break;
                        //stand
                        case 1:
                            player.stand(curHand);
                            break;
                        //double up
                        case 2:
                            player.doubleUp(curHand);
                            player.hit(bjDealer, deck, curHand);
                            System.out.println("Following is your Hand " + j);
                            System.out.println(curHand.toString());
                            if (curHand.getBJHandValue() > MAX_VALUE) {
                                curHand.setIsBusted();
                                System.out.println("Bust!");
                            } else if (curHand.getBJHandValue() == MAX_VALUE) {
                                System.out.println("You get the value of 21!");
                                player.stand(curHand);
                            }
                            break;
                        default:
                            System.out.println("Please input(0-2)");
                    }
                }
            }
        }
    }

    /**
     * @Description player split instruction
     * @param bjPlayer
     * @Return void
     */
    public void playerSplit(BJPlayer bjPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < bjPlayer.getHand().size(); j++) {
                Hand h = bjPlayer.getHand().get(j);
                if (h.canBeSplited()) {
                    System.out.println("You have a hand with two cards having the same value.");
                    System.out.println("Would you like to split?(Y/N)");
                    Scanner in = new Scanner(System.in);
                    String splitOrNot = in.next();
                    boolean yes = "y".equals(splitOrNot) || "Y".equals(splitOrNot);
                    boolean no = "n".equals(splitOrNot) || "N".equals(splitOrNot);
                    while (!(yes || no)) {
                        System.out.println("Please input(Y/N)");
                        splitOrNot = in.next();
                        yes = "y".equals(splitOrNot) || "Y".equals(splitOrNot);
                        no = "n".equals(splitOrNot) || "N".equals(splitOrNot);
                    }
                    if (yes) {
                        bjPlayer.split(bjDealer, deck, h);
                    }
                }
            }
        }
    }

    /**
     * @Description Dealer turn of each round
     * @Param None
     * @Return void
     */
    public void dealerTurn() {
        System.out.println("\n\n\n");
        bjDealer.getHand().getCard().get(1).setKnown(true);
        System.out.println("This is the hand of the Dealer: " + bjDealer.showhands());
        if (bjDealer.getHand().getBJHandValue() == MAX_VALUE && bjDealer.getHand().getCard().size() == 2) {
            System.out.println("Black Jack!!!");
        } else {
            while (bjDealer.getHand().getBJHandValue() <= DEALER_STOP_VALUE) {
                bjDealer.dealToDealer(bjDealer, bjDealer.getHand(), true, deck);
            }
            if (bjDealer.getHand().getBJHandValue() > MAX_VALUE) {
                System.out.println("Bust!");
                bjDealer.getHand().setIsBusted();
            }
        }
        System.out.println("Following is dealer's hand: ");
        System.out.println(bjDealer.showhands());
    }

    /**
     * @Description At the end of each round, the runner will delete players who do not have enough balance
     *              and ask others if they are willing to continue.
     * @Param None
     * @Return void
     */
    public void deletePlayersToPrepareForNextRound() {
        for (int i = 0; i < bjPlayers.size(); i++) {
            BJPlayer player = bjPlayers.get(i);
            if (player.balance <= 0) {
                bjPlayers.remove(i);
            } else {
                System.out.println("Player " + player.getName() + " do you want to continue?(Y/N)");
                Scanner in = new Scanner(System.in);
                String continueOrNot = in.next();
                boolean yes = "y".equals(continueOrNot) || "Y".equals(continueOrNot);
                boolean no = "n".equals(continueOrNot) || "N".equals(continueOrNot);
                while (!(yes || no)) {
                    System.out.println("Please input(Y/N)");
                    continueOrNot = in.next();
                    yes = "y".equals(continueOrNot) || "Y".equals(continueOrNot);
                    no = "n".equals(continueOrNot) || "N".equals(continueOrNot);
                }
                if (no) {
                    bjPlayers.remove(i);
                }
            }
        }
    }
}
