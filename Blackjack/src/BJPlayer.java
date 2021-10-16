import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName BJPlayer
 * @Description represents a special player called in BlackJack Game
 * @Author Vincent Yuan
 * @Date 2021/10/14 21:15
 */
public class BJPlayer extends Player {


    public BJPlayer(int id, String name, int balance) {
        super(id, name, balance);
    }

    /**
     * @Description BlackJack Players can make a bet
     * @param curHand
     * @Return void
     */
    @Override
    public void bet(Hand curHand) {
        System.out.println("How much do you want to bet?");
        Scanner in = new Scanner(System.in);
        int betInput = in.nextInt();
        while (betInput < 0 || betInput > balance) {
            System.out.println("Bet should be greater than 0 and smaller than your balance!");
            betInput = in.nextInt();
        }
        curHand.setBet(betInput);
        balance -= curHand.getBet();
    }

    /**
     * @Description BlackJack Players can choose to hit
     * @param dealer
     * @param deck
     * @param curHand
     * @Return void
     */
    @Override
    public void hit(Dealer dealer, Deck deck, Hand curHand) {
        dealer.deal(this, curHand, true, deck);
    }

    /**
     * @Description BlackJack Players can stand
     * @param hand
     * @Return void
     */
    @Override
    public void stand(Hand hand) {
        System.out.println("Player " + name + " choose to stand!");
        hand.setIsStand();
    }

    /**
     * @Description BlackJack Players can choose to double up
     * @param curHand
     * @Return void
     */
    @Override
    public void doubleUp(Hand curHand) {
        int bet = curHand.getBet();
        if (balance < bet) {
            System.out.println("Sorry, you do not have enough money to double up!");
        } else {
            balance -= bet;
            curHand.setBet(2 * bet);
        }
    }

    /**
     * @Description special operation of BlackJack game, a player can split a hand has two same cards
     * @param dealer
     * @param deck
     * @param curHand
     * @Return void
     */
    public void split(Dealer dealer, Deck deck, Hand curHand) {

        Hand newHand = new Hand();
        newHand.addCard(curHand.getCard().get(1));
        newHand.setBet(curHand.getBet());

        curHand.getCard().remove(1);

        newHand.addCard(deck.getCard());
        curHand.addCard(deck.getCard());

        hand.add(newHand);
    }

}
