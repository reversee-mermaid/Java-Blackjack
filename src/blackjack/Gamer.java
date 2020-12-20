package blackjack;
import java.util.ArrayList;

public class Gamer {
    protected String role;
    protected ArrayList<Card> deck;
    protected boolean bust = false;

    void init() {
        deck = new ArrayList<>();
    }

    //getter setter
    public boolean getBust() {
        return this.bust;
    }

    public void setBust(boolean bust) {
        this.bust = bust;
    }

    public String getRole() {
        return role;
    }

    protected void setRole(String role) {
        this.role = role;
    }
}

