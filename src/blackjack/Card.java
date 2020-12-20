package blackjack;

public class Card {
    private final String shape;
    private final String value;
    private boolean faceUp;

    public Card(String shape, String value) {
        this.shape = shape;
        this.value = value;
        this.faceUp = true;
    }

    public int getValue() {
        int res;
        switch (value) {
            case "A" :
                res = 1;
                break;
            case "J":
            case "Q":
            case "K":
                res = 10;
                break;
            default: res = Integer.parseInt(value);
        }
        return res;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    @Override
    public String toString() {
        if(faceUp) {
            return String.format("【%s %s】", shape, value);
        } else {
            return "【 ? 】";
        }
    }
}
