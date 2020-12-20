package blackjack;

public class Print {
    static void options(String str) {
        System.out.println(str);
    }

    static void wrongInput() {
        System.out.println("Wrong input");
    }

    static void hit(Gamer g) {
        message(g, "Hit");
    }

    static void stand(Gamer g) {
        message(g, "Stand");
    }

    static void surrender(Guest g) {
        message(g, "Surrender");
    }

    static void bust(Gamer g) {
        message(g, "Bust");
    }

    static void blackjack(Gamer g) {
        message(g, "Blackjack!!");
    }

    static void message(String str) {
        System.out.println(str);
    }

    static void message(Gamer g, String str) {
        System.out.println(String.format("@%s - %s", g.getRole(), str));
    }
}
