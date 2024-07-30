package org.example;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int balance;

    public Player(int initialBalance) {
        hand = new ArrayList<>();
        balance = initialBalance;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getBalance() {
        return balance;
    }

    public void adjustBalance(int amount) {
        balance += amount;
    }

    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            String cardValue = card.getValue();
            if (cardValue.equals("Jack") || cardValue.equals("Queen") || cardValue.equals("King")) {
                value += 10;
            } else if (cardValue.equals("Ace")) {
                aceCount++;
                value += 11;
            } else {
                value += Integer.parseInt(cardValue);
            }
        }

        while (aceCount > 0 && value > 21) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public void resetHand() {
        hand.clear();
    }
}
