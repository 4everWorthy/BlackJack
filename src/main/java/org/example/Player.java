package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int balance;
    private List<String> hand;

    public Player(int balance) {
        this.balance = balance;
        this.hand = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public void adjustBalance(int amount) {
        balance += amount;
    }

    public List<String> getHand() {
        return new ArrayList<>(hand);
    }

    public void addCardToHand(String card) {
        hand.add(card);
    }

    public void resetHand() {
        hand.clear();
    }

    public int calculateHandValue() {
        int value = 0;
        int aces = 0;

        for (String card : hand) {
            String rank = card.split(" ")[0];
            switch (rank) {
                case "Ace":
                    aces++;
                    value += 11;
                    break;
                case "King":
                case "Queen":
                case "Jack":
                case "10":
                    value += 10;
                    break;
                default:
                    value += Integer.parseInt(rank);
            }
        }

        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }
}
