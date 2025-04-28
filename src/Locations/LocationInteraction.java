package Locations;

import Actors.Actor;

import java.util.List;
import java.util.Scanner;

public class LocationInteraction {
    static Scanner input = new Scanner(System.in);

    public void mainInteract(Location currentLocation, Actor player) throws InterruptedException {
        List<String> options = currentLocation.getAvailableActions();

        int choice = -1;
        while (choice < 0 || choice > options.size()) {
            System.out.println();
            int inv = 0;
            System.out.println("What would you like to do?: ");
            for (int i = 0; i < options.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + options.get(i));
                inv = i + 2;
            }
            System.out.println(" " + inv + ". Inventory");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                if (choice > 0 && choice <= options.size()) {
                    currentLocation = LocationRegistry.get(options.get(choice - 1));
                    currentLocation.enterLocation(player);
                }else if (choice == options.size() + 1) {
                    player.inventoryInteraction();
                }else {
                    System.out.println();
                    System.out.println("Oops! Please enter a valid location...");
                    choice = -1;
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Oops! Please enter a number...");
            }
        }
    }

    public int subInteract(Location currentLocation) {
        List<String> options = currentLocation.getAvailableActions();

        int choice;
        while (true) {
            System.out.println();
            System.out.println("What would you like to do?: ");
            for (int i = 0; i < options.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + options.get(i));
            }
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                if (choice > 0 && choice <= options.size()) {
                    return choice;
                }else {
                    System.out.println();
                    System.out.println("Oops! Please enter a valid number...");
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Oops! Please enter a number...");
            }
        }
    }
}
