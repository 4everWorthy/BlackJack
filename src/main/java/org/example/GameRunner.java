package org.example;

import java.util.Scanner;

public class GameRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeckActions deckActions = new DeckActions();
        Player player = new Player(100); // Initial balance of 100
        Player dealer = new Player(0);

        while (true) {
            System.out.println("Your balance: $" + player.getBalance());
            System.out.print("Enter your bet (multiple of 5): ");
            int bet = scanner.nextInt();

            if (bet > player.getBalance() || bet <= 0 || bet % 5 != 0) {
                System.out.println("Invalid bet amount. Bet must be a multiple of 5 and within your balance.");
                continue;
            }

            player.resetHand();
            dealer.resetHand();

            player.addCardToHand(deckActions.dealCard());
            player.addCardToHand(deckActions.dealCard());
            dealer.addCardToHand(deckActions.dealCard());
            dealer.addCardToHand(deckActions.dealCard());

            System.out.println("Your hand: " + player.getHand());
            System.out.println("Dealer's hand: [" + dealer.getHand().get(0) + ", ?]");

            boolean playerTurn = true;
            boolean doubledDown = false;

            while (playerTurn) {
                System.out.print("Do you want to hit, stand, or double down? (h/s/d): ");
                String action = scanner.next();

                if (action.equalsIgnoreCase("h")) {
                    player.addCardToHand(deckActions.dealCard());
                    System.out.println("Your hand: " + player.getHand());

                    if (player.calculateHandValue() > 21) {
                        System.out.println("You bust! Dealer wins.");
                        player.adjustBalance(-bet);
                        playerTurn = false;
                    }
                } else if (action.equalsIgnoreCase("d") && !doubledDown) {
                    if (bet * 2 > player.getBalance()) {
                        System.out.println("You don't have enough balance to double down.");
                        continue;
                    }

                    bet *= 2;
                    player.addCardToHand(deckActions.dealCard());
                    System.out.println("Your hand: " + player.getHand());
                    doubledDown = true;

                    if (player.calculateHandValue() > 21) {
                        System.out.println("You bust! Dealer wins.");
                        player.adjustBalance(-bet);
                        playerTurn = false;
                    } else {
                        playerTurn = false;
                    }
                } else if (action.equalsIgnoreCase("s")) {
                    playerTurn = false;
                } else {
                    System.out.println("Invalid input. Please enter 'h' to hit, 's' to stand, or 'd' to double down.");
                }
            }

            System.out.println("Dealer's hand: " + dealer.getHand());

            if (player.calculateHandValue() <= 21) {
                while (dealer.calculateHandValue() < 17) {
                    dealer.addCardToHand(deckActions.dealCard());
                }

                System.out.println("Dealer's hand: " + dealer.getHand());

                int playerValue = player.calculateHandValue();
                int dealerValue = dealer.calculateHandValue();

                if (dealerValue > 21 || playerValue > dealerValue) {
                    System.out.println("You win!");
                    player.adjustBalance(bet);
                } else if (playerValue < dealerValue) {
                    System.out.println("Dealer wins.");
                    player.adjustBalance(-bet);
                } else {
                    System.out.println("It's a tie.");
                }
            }

            System.out.print("Do you want to play again? (y/n): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("y")) {
                break;
            }
        }

        System.out.println("Thanks for playing! Your final balance: $" + player.getBalance());
        scanner.close();
    }
}
