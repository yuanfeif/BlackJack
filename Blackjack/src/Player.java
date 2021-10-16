import java.util.ArrayList;

public abstract class Player {
    protected int id;                   // Player's id
    protected String name;              // Player's name
    public int balance;              // Player's money
    public ArrayList<Hand> hand;                // Player's hand card in each round
    public boolean bust = false;                // whether the player busts

    // constructor
    public Player(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
    }

    // return the Id
    public int getId() {
        return id;
    }

    // set the Id
    public void setId(int id) {
        this.id = id;
    }

    // return the name
    public String getName() {
        return name;
    }

    // set the name
    public void setName(String name) {
        this.name = name;
    }

    // return the hand
    public ArrayList<Hand> getHand() {
        return hand;
    }

    // remove all the cards in a hand
    public void cleanHand() {
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
    }

    /**
     * @Description Players can make a bet
     * @param curHand
     * @Return void
     */
    public abstract void bet(Hand curHand);

    /**
     * @Description Players can choose to hit
     * @param dealer
     * @param deck
     * @param curHand
     * @Return void
     */
    public abstract void hit(Dealer dealer, Deck deck, Hand curHand);

    /**
     * @Description Players can stand
     * @param curHand
     * @Return void
     */
    public abstract void stand(Hand curHand);

    /**
     * @Description Players can choose to double up
     * @param curHand
     * @Return void
     */
    public abstract void doubleUp(Hand curHand);                // Player can choose to double up

    /**
     * @Description convert all the cards in a hand to a string
     * @Param None
     * @return void
     */
    public String showhands() {
        String str = "";
        for (Hand h : hand) {
            str += h.toString();
        }
        return str + "\n";
    }
}
