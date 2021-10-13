import java.util.ArrayList;

public abstract class Player{
    protected int id;                   // Player's id
    protected String name;              // Player's name
    protected int balance;              // Player's money
    protected int bet;   // Player's bet in each round
    public ArrayList<Hand> hand;                // Player's hand card in each round

    public Player(){}

    public Player(int id, String name, int balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public int getBet(){
        return bet;
    }

    public void setBet(int bet){
        this.bet = bet;
    }

    public ArrayList<Hand> getHand(){
        return hand;
    }

    public void cleanHand() {
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
    }

    public abstract void bet(Player player, int money);           // Players can make a bet

    public abstract void hit(Dealer dealer, Player player, Hand hand, boolean known, Deck deck);                     // Player can choose to hit

    public abstract void stand();                   // Player can choose to stand

    public abstract void doubleUp(Dealer dealer, Player player, Hand hand, boolean known, Deck deck);                // Player can choose to double up

    public String showhands(){
        String str = "";
        for (Hand h: hand){
            str += h.toString();
        }
        return str+"\n";
    }
}
