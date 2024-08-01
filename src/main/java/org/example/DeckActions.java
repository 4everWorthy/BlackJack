package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckActions {
    private List<String> deck;

    public DeckActions() {
        deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
        Collections.shuffle(deck);
    }

    public String dealCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("The deck is empty");
        }
        return deck.remove(0);
    }
}
