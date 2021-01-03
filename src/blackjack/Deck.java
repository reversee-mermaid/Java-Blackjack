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
    
    public Card dealCard(int i) {
    	Card temp = deck.get(i);
    	deck.remove(i);
    	return temp;
    }

    int getSize() {
        return deck.size();
    }
}
