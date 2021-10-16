public abstract class Dealer {
    protected int Did;                   // Dealer's id
    protected String Dname;              // Dealer's name
    public int Dbalance;              // Dealer's money
    protected Hand Dhand;                // Dealer's hand card in each round
    protected Deck deck;                 // Dealer holds the deck of cards

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

    // deal the cards to the player
    public abstract void deal(Player player, Hand hand, boolean known, Deck deck);            // Dealer can deal cards to all gamblers

    // deal the cards to the dealer
    public abstract void dealToDealer(Dealer dealer, Hand hand, boolean known, Deck deck);

    public abstract void hit();             // Dealer can automatically hit

    public abstract Card turnFace();        // Dealer can turn the face-down card up

    // return the string of all the cards in a hand
    public String showhands() {
        String str = "";
        str += Dhand.toString();
        return str + "\n";
    }


}

