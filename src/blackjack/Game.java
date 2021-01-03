package blackjack;

import java.util.Scanner;
import static blackjack.Print.*;

public class Game {
    private Scanner scan;
    private Deck deck;
    private Guest guest;
    private Dealer dealer;
    private String winner;
    private String options;

    public Game (Scanner scan) {
        //initialize
        init(scan);

        newGame();

        //TODO create to method : decideWinner()
        if(winner == null) {
            if(getTotal(guest) > getTotal(dealer)) {
                winner = guest.getRole();
            } else if(getTotal(guest) < getTotal(dealer)) {
                winner = dealer.getRole();
            }
        }

        //TODO rename show() => printResult()
        show();
    }

    private void init(Scanner scan) {
        this.scan = scan;
        this.deck = new Deck();
        this.guest = new Guest();
        this.dealer = new Dealer();
        this.options = "1)Hit 2)Stand 0)Surrender";
    }

    private void newGame() {
        dealCard();

        //guest turn
        guestTurn();
        if(guest.getSurrender()) {
            winner = dealer.getRole();
            return;
        } else if(guest.getBust()) {
            winner = dealer.getRole();
        }

        //dealer turn
        if(winner != null) return;
        dealerTurn();
        if(dealer.getBust()) {
            winner = guest.getRole();
        }
    }

    //TODO refactoring function in common 
    //between guestTurn() and dealerTurn()
    private void guestTurn() {
        while (true) {
            //show deck
            show("Guest");

            //check total
            int total = getTotal(guest);
            if(total > 21) {
                guest.setBust(true);
                bust(guest);
                break;
            } else if(total == 21) {
                blackjack(guest);
                break;
            }

            //select option
            System.out.println(options);
            int input = scan.nextInt();

            if(input == 0) {
                guest.setSurrender(true);
                surrender(guest);
                break;

            } else if(input == 2) {
                stand(guest);
                break;

            } else if(input == 1) {
                hit(guest);
                dealCard(guest);
            } else {
                wrongInput();
            }
        }
    }

    private void dealerTurn() {
        //TODO move faceUp into newGame()
        dealer.deck.get(1).setFaceUp(true);

        while (true) {
            //show deck
            show("Dealer");

            //check total
            int total = getTotal(dealer);
            if (total > 21) {
                dealer.setBust(true);
                bust(dealer);
                break;
            } else if (total == 21) {
                blackjack(dealer);
                break;
            }

            // hit or stand
            if(total < 17) {
                hit(dealer);
                dealCard(dealer);
            } else {
                stand(dealer);
                break;
            }
        }
    }

    private void dealCard() {
       for (int i = 0; i < 2; i++) {
           dealCard(guest);
           dealCard(dealer);
       }
       dealer.deck.get(1).setFaceUp(false);
   }

    private void dealCard(Gamer gamer) {
        int random = (int) (Math.random() * deck.getSize());

        //TODO combine deck.getCard() and deck.remove() to deck.dealCard()
        gamer.deck.add(deck.getCard(random));
        deck.remove(random);
    }

    private int getTotal(Gamer gamer) {
        int res = 0;
        for (Card c : gamer.deck) {
            res += c.getValue();
        }
        return res;
    }

    private void show() {
        String res = String.format(
                "\n============Result============\n" +
                "Dealer (%d)\n\t" + dealer.deck + "\n" +
                "User (%d)\n\t" + guest.deck + "\n" +
                "=============================="
                , getTotal(dealer), getTotal(guest)
        );

        message(res);
        message(winner != null ? "★ Winner : " + winner : "Push");
    }

    private void show(String label) {
        String res = String.format(
                "\n------------%s------------\n" +
                "Dealer\n\t" + dealer.deck + "\n" +
                "User\n\t" + guest.deck + "\n"
                ,label
        );

        message(res);
    }
}