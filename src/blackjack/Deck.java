package blackjack;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        init();
    }

    void init() {
        ArrayList<Card> deck = new ArrayList<>();
        String[] shapes = {"♠", "♥", "♣", "◆"};
        String[] values = {"A", "2", "3", "4", "5"
                , "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String shape : shapes) {
            for (String value : values) {
                deck.add(new Card(shape, value));
            }
        }

        this.deck = deck;
    }

    public void remove(int i) {
        deck.remove(i);
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    int getSize() {
        return deck.size();
    }
}
