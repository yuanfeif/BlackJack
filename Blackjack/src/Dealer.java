/**
 * @ClassName Dealer
 * @Description It is the abstract class for the game dealer
 * @Author Andy Sheng
 * @Date 2021/10/12
 */

public abstract class Dealer {
    /**
     * Dealer's id
     */
    protected int Did;

    /**
     * Dealer's name
     */
    protected String Dname;

    /**
     * Dealer's money
     */
    public int Dbalance;

    /**
     * Dealer's hand card in each round
     */
    protected Hand Dhand;

    /**
     * Dealer holds the deck of cards
     */
    protected Deck deck;

    // constructor
    public Dealer(int id, String name, int balance) {
        this.Did = id;
        this.Dname = name;
        this.Dbalance = balance;
        this.Dhand = new Hand();
        this.deck = new Deck();
    }

    // get the id
    public int getId() {
        return Did;
    }

    // set the id
    public void setId(int id) {
        this.Did = id;
    }

    // get the name
    public String getName() {
        return Dname;
    }

    // set the name
    public void setName(String name) {
        this.Dname = name;
    }

    // get the balance
    public int getBalance() {
        return Dbalance;
    }

    // set the balance
    public void setBalance(int balance) {
        this.Dbalance = balance;
    }

    // get the hand
    public Hand getHand() {
        return Dhand;
    }

    // clean all the cards in the hand
    public void cleanHand() {
        Dhand = new Hand();
    }

    // return the deck
    public Deck getDeck() {
        return deck;
    }


    /**
     * @Description: This method deals the cards to a player.
     * @Param: Player player, Hand hand, boolean known, Deck deck
     * @Return: void
     */
    public abstract void deal(Player player, Hand hand, boolean known, Deck deck);

    /**
     * @Description: This method deals the cards to the dealer.
     * @Param: Dealer dealer, Hand hand, boolean known, Deck deck
     * @Return: void
     */
    public abstract void dealToDealer(Dealer dealer, Hand hand, boolean known, Deck deck);

    /**
     * @Description: The dealer can hit automatically.
     * @Param: none
     * @Return: void
     */
    public abstract void hit();

    /**
     * @Description: Dealer can turn the face-down card up.
     * @Param: none
     * @Return: Card
     */
    public abstract Card turnFace();

    /**
     * @Description: Override toString() method to print the string of all the cards in a hand.
     * @Param: none
     * @Return: String
     */
    public String showhands() {
        String str = "";
        str += Dhand.toString();
        return str + "\n";
    }
}

