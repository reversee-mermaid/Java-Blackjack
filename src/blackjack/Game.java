package blackjack;

import java.util.Scanner;

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

        decideWinner();

        printResult();
    }

    private void init(Scanner scan) {
        this.scan = scan;
        this.deck = new Deck();
        this.guest = new Guest();
        this.dealer = new Dealer();
        this.options = "1)Hit 2)Stand 0)Surrender";
    }

    private void decideWinner() {
    	if(winner == null) {
            if(getTotal(guest) > getTotal(dealer)) {
                winner = guest.getRole();
            } else if(getTotal(guest) < getTotal(dealer)) {
                winner = dealer.getRole();
            }
        }
    }
    
    private void newGame() {
        dealCard();

        turn(guest);
        dealer.deck.get(1).setFaceUp(true);
        
        if(guest.getSurrender()) {
            winner = dealer.getRole();
            return;
        } else if(guest.getBust()) {
            winner = dealer.getRole();
        }

        if(winner != null) return;
                
        turn(dealer);
        if(dealer.getBust()) {
            winner = guest.getRole();
        }
    }
    
    private void turn(Gamer gamer) {
        while(true) {
            printStatus(gamer.getRole());
            
            if(checkTotal(gamer)) {
                break;
            }

            if(gamer instanceof Guest) {
                System.out.println(options);
                if(guestTurn(scan.nextInt())) break;
            }

            if(gamer instanceof Dealer) {
                if(dealerTurn()) break;
            }
        }
    }
    
   private boolean checkTotal(Gamer gamer) {
	   boolean res = false;
	   int total = getTotal(gamer);
	   
	   if(total > 21) {
		   gamer.setBust(true);
		   Print.bust(gamer);
		   res = true;
		   
	   } else if(total == 21) {
		   Print.blackjack(gamer);
		   res = true;
	   }
	   
	   return res;
   }

    private boolean guestTurn(int value) {
    	boolean res = false;
    	
    	if(value == 0) {
    		guest.setSurrender(true);
    		Print.surrender(guest);
    		res = true;
    	} else if(value == 2) {
    		Print.stand(guest);
    		res = true;
    	} else if(value == 1) {
    		Print.hit(guest);
    		dealCard(guest);
    	} else {
    		Print.wrongInput();
    	}
    	
    	return res;
    }
    
    private boolean dealerTurn() {
    	boolean res = false;
    	int total = getTotal(dealer);
    	
    	if(total < 17) {
    		Print.hit(dealer);
    		dealCard(dealer);
    	} else {
    		Print.stand(dealer);
    		res = true;
    	}
    	return res;
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

        gamer.deck.add(deck.dealCard(random));
    }

    private int getTotal(Gamer gamer) {
        int res = 0;
        for (Card c : gamer.deck) {
            res += c.getValue();
        }
        return res;
    }

    private void printResult() {
        String res = String.format(
                "\n============Result============\n" +
                "Dealer (%d)\n\t" + dealer.deck + "\n" +
                "User (%d)\n\t" + guest.deck + "\n" +
                "=============================="
                , getTotal(dealer), getTotal(guest)
        );

        Print.message(res);
        Print.message(winner != null ? "★ Winner : " + winner : "Push");
    }

    private void printStatus(String label) {
        String res = String.format(
                "\n------------%s------------\n" +
                "Dealer\n\t" + dealer.deck + "\n" +
                "User\n\t" + guest.deck + "\n"
                ,label
        );

        Print.message(res);
    }
}