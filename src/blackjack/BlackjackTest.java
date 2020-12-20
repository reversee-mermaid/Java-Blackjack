package blackjack;

import java.util.Scanner;
import static blackjack.Print.*;

public class BlackjackTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            options("1) Game start\n0) Exit");
            int input = scan.nextInt();

            if (input == 0) {
                return;
            } else if (input != 1) {
                wrongInput();
                continue;
            }

            new Game(scan);
        }
    }
}