package Locations;
import Actors.Actor;

import java.util.List;
import java.util.Scanner;

public class StartingTown implements Location{
    static Scanner input = new Scanner(System.in);

    private boolean hasEntered = false;

    @Override
    public String getLocationName() {return "The Clearing Outpost";}

    @Override
    public void enterLocation(Actor player) throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("You enter The Clearing Outpost...");
        }else {
            System.out.println();
            System.out.println("You enter the western part of a small town just beyond the forest clearing...");
            Thread.sleep(1000);
            System.out.println();
            System.out.println("At first glance, you notice a shop called 'The Broken Toe' and a tavern named 'Tammy's Tavern'");
            this.hasEntered = true;
        }
        int choice = -1;
        System.out.println();
        while (choice < 1) {
            System.out.println("What would you like to do?");
            System.out.println(" 1. Tammy's Tavern");
            System.out.println(" 2. The Broken Toe");
            System.out.println(" 3. Forest");
            System.out.println(" 4. Inventory");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice == 1) {
                    LocationRegistry.get("Tammy's Tavern").enterLocation(player);
                }else if (choice == 2) {
                    LocationRegistry.get("The Broken Toe").enterLocation(player);
                }else if (choice == 3) {
                    System.out.println("NO FOREST YET");
                    choice = -1;
                }else if (choice == 4) {
                    player.inventoryInteraction();
                    choice = -1;
                }else {
                    System.out.println();
                    System.out.println("Oops! Please enter a valid number...");
                    choice = -1;
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Please enter a number...");
            }
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("Tammy's Tavern", "The Broken Toe");
    }
}