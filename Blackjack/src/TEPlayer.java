import java.util.ArrayList;
import java.util.Scanner;

public class TEPlayer extends Player{
    public boolean fold = false;
    private Scanner scan = new Scanner(System.in);

    public TEPlayer(int id, String name, int balance){
        super(id,name,balance);
    }

    @Override
    public void bet(Player player, int money) {
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
                System.out.println("Invalid betting. Bet must be an integer.");
                continue;
            }
            if (money <= 0 || money > balance){
                System.out.println("Invalid betting. Must between 0 to balance");
                continue;
            }
            break;
        }
        player.balance -= money;
        player.setBet(money);
    }

    @Override
    public void hit(Dealer dealer, Player player, Hand hand, boolean known, Deck deck) {
        dealer.deal(player, hand, known, deck);
    }

    @Override
    public void stand() {}

    @Override
    public void doubleUp(Dealer dealer, Player player, Hand hand, boolean known, Deck deck) {
        bet(player, bet);
        dealer.deal(player, hand, known, deck);
    }


}
