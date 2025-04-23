package Locations;
import Actors.Actor;
import Armor.Armor;
import Effects.HitEffect;
import Healing.Healing;
import Weapons.Weapon;

import java.util.*;

public class StartingShop implements Location{
    static Scanner input = new Scanner(System.in);

    private final Map<Healing, Integer> shopHealingInventory = new HashMap<>();
    private final Set<Weapon> shopWeaponInventory = new HashSet<>();
    private final Set<Armor> shopArmorInventory = new HashSet<>();

    boolean hasEntered = false;

    @Override
    public String getLocationName() {return "Broken Toe";}

    @Override
    public void enterLocation(Actor player) throws InterruptedException {
        if (hasEntered) {
            System.out.println();
            System.out.println("Welcome back to the 'ol Broken Toe, don't forget to watch your step!");
        }else {
            System.out.println();
            System.out.println("You enter a small, scarce shop...");
            System.out.println("The shopkeeper welcomes you: Welcome to my shop, the Broken Toe! Name's Axel, grab whatever you need, and watch your step on the way in!");
            this.hasEntered = true;
        }

        int choice = -1;
        while (choice < 1) {
            System.out.println();
            System.out.println("What will you do now?: ");
            System.out.println(" 1. Shop");
            System.out.println(" 2. Talk to Axel");
            System.out.println(" 3. Leave");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice == 1) {
                    shop();
                }else if (choice == 2) {
                    System.out.println();
                    System.out.println("You approach Axel...");
                    Thread.sleep(1200);
                    System.out.println("Axel: Gooday, sorry for the mostly empty shop, we don't get a lot of supply as of late, but let me know if you'd like to buy!");
                    choice = -1;
                }else if (choice == 3) {
                    System.out.println();
                    System.out.println("You leave the shop...");
                    LocationRegistry.get("The Clearing Outpost").enterLocation(player);
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

    private void shop() {
        System.out.println();
        System.out.println("You walk around the shop, taking a mental note of what's available...");
        System.out.println();
        displayShop();
    }

    {
        shopHealingInventory.put(new Healing("Apple", 3, 1, 0), 8);
        shopHealingInventory.put(new Healing("Cooked Meat", 5, 2, 0), 4);
        shopHealingInventory.put(new Healing("Healing Potion", 7, 1, 1), 1);

        shopWeaponInventory.add(new Weapon("Dagger", 2, new HitEffect("Bleed", 2, 0, 0, 0.2)));

        shopArmorInventory.add(new Armor("Leather Armor", 3));
    }

    private void displayShop() {
        int count = 1;
        System.out.println("Healing:");
        for (Map.Entry<Healing, Integer> healing : this.shopHealingInventory.entrySet()) {
            Healing entry = healing.getKey();
            int num = healing.getValue();
            System.out.println(" " + count + ". " + entry.name() + " x" + num + " - " + entry.healthGained() + " healing");
            count++;
        }
        System.out.println();
        System.out.println("Weapons:");
        count = 1;
        for (Weapon weapon : this.shopWeaponInventory) {
            System.out.println(" " + count + ". " + weapon.name() + " - " + weapon.damage() + " damage");
            count++;
        }
        System.out.println();
        System.out.println("Armor:");
        count = 1;
        for (Armor armor : this.shopArmorInventory) {
            System.out.println(" " + count + ". " + armor.name() + " - " + armor.armorPoints() + " armor");
            count++;
        }
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("The Clearing Outpost");
    }
}