import java.util.ArrayList;

public abstract class Player{
    protected int id;                   // Player's id
    protected String name;              // Player's name
    public int balance;              // Player's money
    public int bet;   // Player's bet in each round
    public ArrayList<Hand> hand;                // Player's hand card in each round
    public boolean bust=false;                // whether the player busts

    // constructor
    public Player(int id, String name, int balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
    }

    // return the Id
    public int getId(){
        return id;
    }

    // set the Id
    public void setId(int id){
        this.id = id;
    }

    // return the name
    public String getName(){
        return name;
    }

    // set the name
    public void setName(String name){
        this.name = name;
    }

    // return the hand
    public ArrayList<Hand> getHand(){
        return hand;
    }

    // remove all the cards in a hand
    public void cleanHand() {
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
    }

    public abstract void bet(int[] bets);           // Players can make a bet

    public abstract void hit(Deck deck);                     // Player can choose to hit

    public abstract void stand();                   // Player can choose to stand

    public abstract void doubleUp();                // Player can choose to double up

    // convert all the cards in a hand to string
    public String showhands(){
        String str = "";
        for (Hand h: hand){
            str += h.toString();
        }
        return str+"\n";
    }
}
