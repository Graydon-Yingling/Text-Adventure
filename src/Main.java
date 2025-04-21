import Enemies.Beast;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.gameStart();
        Thread.sleep((1000));
        System.out.println();
        System.out.println();
        System.out.println("A wolf jumps out and attacks!");
        System.out.println();
        Thread.sleep(1000);
        game.startFight(new Beast("Wolf", 12, 2, 90));
    }
}
