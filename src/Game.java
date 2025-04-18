import Armor.*;
import Actors.*;
import Healing.*;
import Weapons.*;
import Effects.*;

import java.util.*;

public class Game {
    static Scanner input = new Scanner(System.in);
    public static void gameStart() {
        System.out.println("Welcome to the Game!");
        System.out.println();
        System.out.println("Please Choose Your Starting Character by typing the corresponding number:");
        System.out.println("1. Warrior");
        System.out.println("2. Wizard");
        System.out.println();

        Actor actor = null;
        Weapon weapon = null;
        Armor armor = null;
        Healing healing = null;

        int choice = 0;
        while (choice != 1 && choice != 2) {
            choice = input.nextInt();

            if (choice == 1) {
                actor = new Actor("Warrior", 20, 80, 0);
                weapon = new Weapon("Long Sword", 5, new HitEffect("Armor Piercing", 0, 2, 0));
                armor = new Armor("Iron Armor", 5);
                healing = new Healing("Cooked Meat", 5, 2, 0);

                actor.addWeaponToInventory(weapon);
                actor.addArmorToInventory(armor);
                actor.addHealingToInventory(healing, 2);

                actor.equipWeapon(weapon);
                actor.equipArmor(armor);
            }else if (choice == 2) {
                actor = new Actor("Wizard", 15, 85, 0);
                weapon = new Weapon("Magic Staff", 6, new HitEffect("Stun", 0, 0, -100));
                armor = new Armor("Cloak", 0);
                healing = new Healing("Healing Potion", 7, 1, 1);

                actor.addWeaponToInventory(weapon);
                actor.addArmorToInventory(armor);
                actor.addHealingToInventory(healing, 2);

                actor.equipWeapon(weapon);
                actor.equipArmor(armor);
            }else {
                System.out.println("Oops! Please type either a 1 or a 2");
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("You have chosen " + actor.getName() + "!");
        System.out.println();
        System.out.println("Equipped Items: ");
        System.out.println(" - " + actor.getEquippedWeapon().name());
        System.out.println(" - " + actor.getEquippedArmor().name());
        System.out.println();
        System.out.println("Inventory:");
        displayInventory(actor);
    }

    public static void displayInventory(Actor actor) {
        System.out.println(" - Weapons:");
        for (Weapon weapon : actor.getWeaponInventory()) {
            System.out.println("  ~ " + weapon.name());
        }
        System.out.println(" - Armor:");
        for (Armor armor : actor.getArmorInventory()) {
            System.out.println("  ~ " + armor.name());
        }
        System.out.println(" - Healing Items:");
        for (Map.Entry<Healing, Integer> healing : actor.getHealingInventory().entrySet()) {
            Healing entry = healing.getKey();
            int count = healing.getValue();
            System.out.println("  ~ " + entry.name() + " x" + count);
        }
    }
}
