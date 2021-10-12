import java.util.ArrayList;

public abstract class Player{
    protected int id;                       // Player's id
    protected String name;                  // Player's name
    protected int balance;                  // Player's money
    protected int bet;                      // Player's bet in each round
    protected Hand hand;                    // Player's hand card in each round

    public Player(){}

    public Player(int id, String name, int balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.hand = new Hand();
        this.bet = 0;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getName(){
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

    public void setBet(int bet) { this.bet = bet; }

    public Hand getHand(){
        return hand;
    }


    public abstract void bet(int money);                    // Players can make a bet

    public abstract void hit(Dealer dealer);                // Player can choose to hit

    public abstract void stand();                           // Player can choose to stand

    public abstract void doubleUp(Dealer dealer);           // Player can choose to double up

    public abstract void split(Dealer dealer);              // Player can choose to split
}
