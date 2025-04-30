package Locations;

import Actors.Actor;
import Armor.Armor;
import Enemies.*;
import Enemies.Undead;
import Events.LootItem;
import Events.SearchArea;
import Fights.FightSequence;
import Healing.Healing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartingForest implements Location {
    static Scanner input = new Scanner(System.in);

    public List<LootItem<?>> possibleItems = new ArrayList<>();
    public  List<Enemy> possibleEnemies = new ArrayList<>();

    boolean hasEntered;

    @Override
    public String getLocationName() {return "The Forest";}

    @Override
    @SuppressWarnings({"BusyWait"})
    public void enterLocation(Actor player) throws InterruptedException {
        {
            possibleItems.add(new LootItem<>("Greater Healing Potion", new Healing("Greater Healing Potion", player.getMaxHP() / 2, 0, 2), 1));
            possibleItems.add(new LootItem<>("Iron Armor", new Armor("Iron Armor", 5), 1));

            possibleEnemies.add(new Beast("Wolf", 12, 2, 90));
            possibleEnemies.add(new Undead("Hound", 10, 1, 2, 80));
            possibleEnemies.add(new Dragon("Hatchling", 8, 3, 75));
        }

        if (hasEntered) {
            System.out.println();
            System.out.println("You travel back into the hear of a now-quiet forest...");
        }else {
            System.out.println();
            System.out.println("Slowly but surely, you make you way into the dense forest...");
            Thread.sleep(1500);
            System.out.println("Twigs snap, leaves rustle, and birds scatter as you continue deeper into the trees and brush");
            Thread.sleep(2000);
            System.out.println();
            System.out.println("Suddenly, a snarling beast, not much different from one you have faced before appears from the shrubbery!");
            System.out.println();
            FightSequence nextFight = new FightSequence();
            nextFight.fight(player, new Undead("Hound", 25, 1, 4, 80));
            hasEntered = true;
        }
        int choice;
        while (true) {
            System.out.println();
            System.out.println("Would you like to search the area for loot...and potentially something less desirable?");
            Thread.sleep(1000);
            System.out.println(" 1. Yes, take the risk");
            System.out.println(" 2. No, continue on");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice == 1) {
                    int randomEnemy = (int) (Math.random() * possibleEnemies.size());
                    int randomLoot = (int) (Math.random() * possibleItems.size());
                    SearchArea area = new SearchArea(player, possibleEnemies.get(randomEnemy), possibleItems.get(randomLoot), 2, 2, 0, 0);
                    area.search();
                    break;
                }else if (choice == 2) {
                    break;
                }else {
                    System.out.println();
                    System.out.println("Oops! PLease enter a valid number...");
                }
            }else {
                System.out.println();
                System.out.println("Please enter a number...");
            }
        }
        LocationInteraction goTo = new LocationInteraction();
        goTo.mainInteract(LocationRegistry.get(getLocationName()), player);
    }

    @Override
    public List<String> getAvailableActions() {return List.of("The Clearing Outpost");}
}