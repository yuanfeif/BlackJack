public abstract class Dealer{
    protected int Did;                   // Dealer's id
    protected String Dname;              // Dealer's name
    protected int Dbalance;              // Dealer's money
    protected Hand Dhand;                // Dealer's hand card in each round
    protected Deck deck;                 // Dealer holds the deck of cards
    protected int Dbet;                  // Dealer's bet in each round

    public Dealer(){}

    public Dealer(int id, String name, int balance){
        this.Did = id;
        this.Dname = name;
        this.Dbalance = balance;
        this.Dhand = new Hand();
        this.deck = new Deck();
    }

    public int getId(){
        return Did;
    }

    public void setId(int id){
        this.Did = id;
    }

    public String getName(){
        return Dname;
    }

    public void setName(String name){
        this.Dname = name;
    }

    public int getBalance(){
        return Dbalance;
    }

    public void setBalance(int balance){
        this.Dbalance = balance;
    }

    public Hand getHand(){
        return Dhand;
    }

    public void cleanHand() {
        Dhand = new Hand();
    }

    public Deck getDeck(){
        return deck;
    }

    public abstract void deal(Player player, Hand hand, boolean known, Deck deck);            // Dealer can deal cards to all gamblers

    public abstract void deal_dealer(Dealer dealer,Hand hand,boolean known, Deck deck);

    public int getBet(){
        return Dbet;
    }

    public void setBet(int bet){
        this.bet = bet;
    }

    public abstract void hit(Dealer dealer,Hand hand,boolean known, Deck deck);             // Dealer can automatically hit

    public abstract void turnFace();        // Dealer can turn the face-down card up

    public String showhands(){
        String str = "";
        str += Dhand.toString();
        return str+"\n";
    }


}

