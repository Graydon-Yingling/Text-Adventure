package Events;

import Actors.Actor;
import Enemies.Enemy;
import Fights.FightSequence;

import java.util.Scanner;

public class SearchArea {
    static Scanner input = new Scanner(System.in);

    private final Actor player;
    Enemy currentEnemy;
    LootItem<?> item;
    private int playerX;
    private int playerY;
    private final int length;
    private final int height;
    private int enemyX;
    private int enemyY;
    private int lootItemX;
    private int lootItemY;

    public SearchArea(Actor player, Enemy currentEnemy, LootItem<?> item, int length, int height, int playerX, int playerY) {
        this.player = player;
        this.currentEnemy = currentEnemy;
        this.item = item;
        this.playerX = playerX;
        this.playerY = playerY;
        this.length = length;
        this.height = height;
        do {
            this.enemyX = (int) (Math.random() * length);
            this.enemyY = (int) (Math.random() * height);
        } while (enemyX == playerX && enemyY == playerY);

        do {
            this.lootItemX = (int) (Math.random() * length);
            this.lootItemY = (int) (Math.random() * height);
        } while (
                (lootItemX == enemyX && lootItemY == enemyY) ||
                (lootItemX == playerX && lootItemY == playerY)
        );
    }

    @SuppressWarnings({"BusyWait"})
    public void search() throws InterruptedException {
        displayArea();
        int choice;
        while (true) {
            System.out.println();
            System.out.println("Where would you like to go?:");
            System.out.println(" 1. Up");
            System.out.println(" 2. Down");
            System.out.println(" 3. Left");
            System.out.println(" 4. Right");
            System.out.println(" 5. Stop Searching");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice == 1 && playerY > 0) {
                    playerY--;
                }else if (choice == 2 && playerY < height - 1) {
                    playerY++;
                }else if (choice == 3 && playerX > 0) {
                    playerX--;
                }else if (choice == 4 && playerX < length - 1) {
                    playerX++;
                }else if (choice == 5) {
                    System.out.println();
                    System.out.println("You stop searching and return to your travels");
                    Thread.sleep(1500);
                    return;
                }else {
                    System.out.println("Oops! You are not able to make that move...");
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Please enter a number...");
            }
            displayArea();
            if (playerX == enemyX && playerY == enemyY) {
                System.out.println();
                System.out.println("An enemy jumps out at you!");
                System.out.println();
                FightSequence nextFight = new FightSequence();
                nextFight.fight(player, currentEnemy);
                displayArea();
            }else if (playerX == lootItemX && playerY == lootItemY) {
                System.out.println();
                item.AddItemToInventory(player, item.getCount());
                System.out.println("You added " + item.getName() + " to your inventory!");
                displayArea();
            }
        }
    }

    private void displayArea() {
        String[][] area = new String[height][length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (i == playerY && j == playerX) {
                    area[i][j] = "@";
                }else {
                    area[i][j] = "+";
                }
            }
        }
        System.out.println();

        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int k = 0; k < length; k++) {
                System.out.print("   -   ");
            }
            System.out.println();
            for (int j = 0; j < length; j++) {
                System.out.print(" | " + area[i][j] + " | ");
            }
        }
        System.out.println();
        for (int k = 0; k < length; k++) {
            System.out.print("   -   ");
        }
        System.out.println();
    }
}
