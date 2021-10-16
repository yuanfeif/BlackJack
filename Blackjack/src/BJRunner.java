import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName BJRunner
 * @Description This class runs the BlackJack game
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
     * client of the BlackJack game
     */
    public BJClient bjClient;

    public BJDealer bjDealer;
    public ArrayList<BJPlayer> bjPlayers;

    public BJRunner() {
        bjPlayers = new ArrayList<BJPlayer>();
        bjDealer = new BJDealer(0, "Dealer", Integer.MAX_VALUE);
        deck = new Deck();
        bjClient = new BJClient();
        round = 0;
    }

    @Override
    public void play() {
        start();

        boolean isEnd = false;
        while (!isEnd) {
            isEnd = eachRound();
        }
    }

    @Override
    public void start() {
        System.out.println("Welcome to the BlackJack Game!");
        System.out.println("Each player has 100 dollars as initial bet!");
        createPlayers();
    }

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

    @Override
    public void showBalance() {
        System.out.println("\n\n---------------------------------------\nFollowing are players balances!");
        for (BJPlayer player : bjPlayers) {
            System.out.printf("Player %s: %d\n", player.getName(), player.balance);
        }
        System.out.println();
    }

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
     * initiate hands of players and dealer
     */
    public void initiate() {
        bjDealer.cleanHand();
        for (BJPlayer player : bjPlayers) {
            player.cleanHand();
        }
    }

    /**
     * dealer deals cards to players and himself at the very beginning of each round
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

    public void playerTurn() {
        for (int i = 0; i < bjPlayers.size(); i++) {
            System.out.println("\n\n\n");
            BJPlayer player = bjPlayers.get(i);
            System.out.println("Player " + player.getName());
            System.out.println("Following is your hand: ");
            System.out.println(player.showhands());

            player.bet(player.getHand().get(0));

            playerSplit(player);
            if (player.getHand().size() > 1) {
                System.out.println("Following are your cards in different hands: " + player.showhands());
            }

            for (int j = 0; j < player.getHand().size(); j++) {

                Hand curHand = player.getHand().get(j);
                System.out.println("Following is your Hand " + j);
                System.out.println(curHand.toString());

                if (curHand.getBJHandValue() == MAX_VALUE && curHand.getCard().size() == 2) {
                    System.out.println("Black Jack!!!");
                    continue;
                }

                while (!(curHand.getIsBusted() || curHand.getIsStand())) {

                    System.out.println("Which action do you want to take?(0:hit,1:stand,2:double up)");

                    Scanner in = new Scanner(System.in);
                    int choice = in.nextInt();

                    while (choice < 0 || choice > 2) {
                        System.out.println("Please input(0-2)");
                        choice = in.nextInt();
                    }
                    switch (choice) {
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
                        case 1:
                            player.stand(curHand);
                            break;
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
