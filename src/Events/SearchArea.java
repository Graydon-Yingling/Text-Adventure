package Events;

import Actors.Actor;

import java.util.Scanner;

public class SearchArea {
    static Scanner input = new Scanner(System.in);

    private Actor player;
    private int playerX;
    private int playerY;
    private int length;
    private int height;
    private int enemies;
    private int lootItems;

    public SearchArea(Actor player, int length, int height, int enemies, int lootItems, int playerX, int playerY) {
        this.player = player;
        this.playerX = playerX;
        this.playerY = playerY;
        this.length = length;
        this.height = height;
        this.enemies = enemies;
        this.lootItems = lootItems;
    }

    public void search() {
        int choice;
        while (true) {
            displayArea();
            System.out.println();
            System.out.println("Where would you like to go?:");
            System.out.println(" 1. Up");
            System.out.println(" 2. Down");
            System.out.println(" 3. Left");
            System.out.println(" 4. Right");
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
                }else {
                    System.out.println("Oops! You are not able to make that move...");
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Please enter a number...");
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
            for (int k = 0; k < height; k++) {
                System.out.print("   -   ");
            }
            System.out.println();
            for (int j = 0; j < length; j++) {
                System.out.print(" | " + area[i][j] + " | ");
            }
        }
        System.out.println();
        for (int k = 0; k < height; k++) {
            System.out.print("   -   ");
        }
        System.out.println();
    }

    private void setPlayerX(int playerX) {this.playerX = playerX;}
}
