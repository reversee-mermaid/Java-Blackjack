package blackjack;

public class Guest extends Gamer {
    private boolean surrender = false;

    public Guest() {
        init();
        setRole("Guest");
    }

    public boolean getSurrender() {
        return this.surrender;
    }

    public void setSurrender(boolean surrender) {
        this.surrender = surrender;
    }
}
