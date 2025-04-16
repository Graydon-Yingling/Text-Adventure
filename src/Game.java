import Armor.*;
import Actors.*;
import Healing.*;
import Weapons.*;
import Effects.*;

import java.util.*;

public class Game {
    static Scanner input = new Scanner(System.in);

    private static Actor actor;
    private static Weapon weapon;
    private static Armor armor;

    public static void main(String[] args) {
        System.out.println("Welcome to the Game!");
        System.out.println();
        System.out.println("Please Choose Your Starting Character by typing the corresponding number:");
        System.out.println("1. Warrior");
        System.out.println("2. Wizard");
        System.out.println();

        int choice = 0;

        while (choice != 1 && choice != 2) {
            choice = input.nextInt();

            if (choice == 1) {
                actor = new Actor("Warrior", 20, 100, 5);
                weapon = new Weapon("Long Sword", 5, new HitEffect("Armor Piercing", 0, 2, 0));
                armor = new Armor("Iron Armor", 5);
            }else if (choice == 2) {
                actor = new Actor("Wizard", 15, 100, 0);
                weapon = new Weapon("Magic Staff", 6, new HitEffect("Stun", 0, 0, -100));
                armor = new Armor("Cloak", 0);
            }else {
                System.out.println("Oops! Please type either a 1 or a 2");
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("You have chosen " + actor.getName() + "!");
        System.out.println("You now have: ");
        System.out.println();
    }

    public static void displayInventory() {

    }
}
