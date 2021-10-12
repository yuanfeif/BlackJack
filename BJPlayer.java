import java.util.ArrayList;
import java.util.Scanner;

public class BJPlayer extends Player{
    private Scanner scan;
    private Hand splitHand;

    public BJPlayer(int id, String name, int balance){
        super(id, name, balance);
        scan = new Scanner(System.in);
    }

    public void bet(int money){
        while(true){
            System.out.println("Please enter the money you want to bet");
            String betting = scan.nextLine();
            if (betting.equals("q")){     // Exit button
                System.out.println("Goodbye! See you next time!");
                System.exit(0);
            }
            try{
                money = Integer.parseInt(betting);
            } catch (Exception e){
                System.out.println("Invalid betting. Bet must be an integer.")
                        continue;
            }
            if (money <= 0 || money > balance){
                System.out.println("Invalid betting. Must between 0 to balance");
                continue;;
            }
            break;
        }
        balance -= money;
        setBet(money);
    }

    public void hit(Dealer dealer) {
        hand.addCard(dealer.deal());
    }

    public void stand() {}

    public void doubleUp(Dealer dealer){
        bet(bet);
        hit(dealer);
    }

    public void split(Dealer dealer){
        splitHand = new Hand();
        splitHand.addCard(hand.getCard()[0]);
        hand.getCard().remove(0);
        hit(dealer);
        splitHand.addCard(dealer.deal());
        bet(bet);
    }

}
