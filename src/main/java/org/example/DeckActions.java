package org.example;

public class DeckActions {
    private Deck deck;

    public DeckActions() {
        deck = new Deck();
        deck.shuffle();
    }

    public Card dealCard() {
        return deck.dealCard();
    }
}
