package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Deck implements DeckActionsInterface {
    private ArrayList<Card> cards;
    private Set<Card> dealtCards;

    public Deck() {
        cards = new ArrayList<>();
        dealtCards = new HashSet<>();
        for (Suits suit : Suits.values()) {
            for (Values value : Values.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public Card dealCard() {
        for (Card card : cards) {
            if (!dealtCards.contains(card)) {
                dealtCards.add(card);
                return card;
            }
        }
        return null; // All cards have been dealt
    }

    @Override
    public int getRemainingCards() {
        return cards.size() - dealtCards.size();
    }
}
