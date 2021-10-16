import java.util.ArrayList;
/**
 * @ClassName Player
 * @Description It is the abstract class for the game player
 * @Author Andy Sheng
 * @Date 2021/10/12
 */

public abstract class Player {

    /**
     * Player's id
     */
    protected int id;

    /**
     * Player's name
     */
    protected String name;

    /**
     * Player's id
     */
    public int balance;

    /**
     * Player's money
     */
    public ArrayList<Hand> hand;

    /**
     * Whether the player busts
     */
    public boolean bust = false;

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

    /**
     * @Description: This method removes all the cards in a player's hand
     * @Param: none
     * @Return: void
     */
    public void cleanHand() {
        this.hand = new ArrayList<Hand>();
        this.hand.add(new Hand());
    }

    /**
     * @Description: This method makes a bet in each round.
     * @Param: Hand curhand
     * @Return: void
     */
    public abstract void bet(Hand curHand);

    /**
     * @Description: Player can choose to hit.
     * @Param: Dealer dealer, Deck deck, Hand curHand
     * @Return: void
     */
    public abstract void hit(Dealer dealer, Deck deck, Hand curHand);

    /**
     * @Description: Player can choose to stand.
     * @Param: Hand curHand
     * @Return: void
     */
    public abstract void stand(Hand curHand);

    /**
     * @Description: Player can choose to double up.
     * @Param: Hand curHand
     * @Return: void
     */
    public abstract void doubleUp(Hand curHand);

    /**
     * @Description: Show all the cards in a player's hand by toString().
     * @Param: none
     * @Return: String
     */
    public String showhands() {
        String str = "";
        for (Hand h : hand) {
            str += h.toString();
        }
        return str + "\n";
    }
}
