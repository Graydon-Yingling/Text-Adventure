package Locations;
import Actors.Actor;

import java.util.List;
import java.util.Scanner;

public class StartingTavern implements Location{
    static Scanner input = new Scanner(System.in);

    boolean hasEntered = false;

    @Override
    public String getLocationName() {return "Tammy's Tavern";}

    @Override
    public void enterLocation(Actor player) throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("You enter a cozy and welcoming tavern...");
            System.out.println("Tammy: Welcome back to my tavern, let me know what you would like to do!");
        }else {
            System.out.println();
            System.out.println("You enter a cozy and welcoming tavern...");
            System.out.println("The tavern keeper introduces herself: Good day and welcome to my tavern! My name is Tammy and I've got food and beds, take your pick!");
            this.hasEntered = true;
        }

        int choice = -1;
        while (choice < 1) {
            System.out.println();
            System.out.println("What would you like to do?: ");
            System.out.println(" 1. Dine in");
            System.out.println(" 2. Take out");
            System.out.println(" 3. Talk to Tammy");
            System.out.println(" 4. Rest");
            System.out.println(" 5. Leave");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice == 1) {
                    System.out.println();
                }else if (choice == 2) {
                    System.out.println();
                }else if (choice == 3) {
                    System.out.println();
                    System.out.println("Tammy: Hey there, not a lot of dining options, supply got cut off recently, but make yourself at home!");
                    choice = -1;
                }else if (choice == 4) {
                    System.out.println();
                    System.out.println("Tammy: Alrighty, here's a key, and your room will up be upstairs. See ya in the morning!");
                    System.out.println();
                    Thread.sleep(1000);
                    System.out.println("You head to your room and sleep the night away...");
                    player.setHp(player.getMaxHP());
                    player.setArmorPoints(player.getArmorPoints());
                    Thread.sleep(4000);
                    System.out.println();
                    System.out.println("You're back downstairs now feeling rejuvenated...and your armor appears to have magically regenerated");
                    System.out.println();
                    Thread.sleep(1000);
                    System.out.println("Tammy: Morning! Let me know what you need!");
                    choice = -1;
                }else if (choice == 5) {
                    System.out.println();
                    System.out.println("You leave the tavern...");
                    LocationRegistry.get("The Clearing Outpost").enterLocation(player);
                }else {
                    System.out.println();
                    System.out.println("Oops! Please enter a valid number...");
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Please enter a number...");
                choice = -1;
            }
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("The Clearing Outpost");
    }
}