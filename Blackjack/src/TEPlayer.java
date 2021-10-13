import java.util.ArrayList;

public class TEPlayer extends Player{
    public boolean fold = false;     // whether the player is fold

    // constructor
    public TEPlayer(int id, String name, int balance){
        super(id,name,balance);
    }

    @Override
    public void bet(int[] bets) {

    }

    // hit
    @Override
    public void hit(Deck deck) {
        System.out.printf("Player %s hits!\n",this.getName());
    }

    // stand
    @Override
    public void stand() {
        System.out.printf("Player %s stands!\n",this.getName());
        System.out.printf("Player %s: %s\n",this.getName(),showhands());
    }

    @Override
    public void doubleUp() {
    }
}
