import Armor.*;
import Actors.*;
import Healing.*;
import Weapons.*;
import Effects.*;
import Enemies.*;

import java.util.*;

public class Game {
    static Scanner input = new Scanner(System.in);

    public void gameStart() {
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
                weapon = new Weapon("Long Sword", 5, new HitEffect("Armor Piercing", 0, 2, 0, 1.0));
                armor = new Armor("Iron Armor", 5);
                healing = new Healing("Cooked Meat", 5, 2, 0);

                actor.addWeaponToInventory(weapon);
                actor.addArmorToInventory(armor);
                actor.addHealingToInventory(healing, 2);

                actor.equipWeapon(weapon);
                actor.equipArmor(armor);
            }else if (choice == 2) {
                actor = new Actor("Wizard", 15, 85, 0);
                weapon = new Weapon("Magic Staff", 6, new HitEffect("Stun", 0, 0, -100, 0.1));
                armor = new Armor("Cloak", 0);
                healing = new Healing("Healing Potion", 7, 1, 1);

                actor.addWeaponToInventory(weapon);
                actor.addArmorToInventory(armor);
                actor.addHealingToInventory(healing, 2);

                actor.equipWeapon(weapon);
                actor.equipArmor(armor);
            }else {
                System.out.println();
                System.out.println("Oops! Please type either a 1 or a 2");
                System.out.println();
                System.out.println("Please Choose Your Starting Character by typing the corresponding number:");
                System.out.println("1. Warrior");
                System.out.println("2. Wizard");
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("You have chosen " + actor.getName() + "!");
        System.out.println();
        System.out.println("Equipped Items: ");
        System.out.println(" Weapon: " + actor.getEquippedWeapon().name());
        System.out.println(" Armor " + actor.getEquippedArmor().name());
        System.out.println();
        System.out.println("Inventory:");
        actor.displayInventory();
    }

    public void fightSequence(Actor player, Enemy currentEnemy) {
        while (player.getHp() > 0 && currentEnemy.getHP() > 0) {
            if (player.getSpeed() >= currentEnemy.getSpeed()) {
                System.out.println("Make your move!");
                System.out.println(" 1. Attack");
                System.out.println(" 2. Heal");
                int choice = 0;
                while (choice != 1 && choice != 2) {
                    choice = input.nextInt();
                    if (input.hasNextInt()) {
                        if (choice == 1) {
                            System.out.println("You attack and deal " + player.getEquippedWeapon().damage() + " damage!");
                            currentEnemy.setHP(currentEnemy.getHP() - player.getEquippedWeapon().damage());
                            if (player.getEquippedWeapon().effect().isApplied() && player.getEquippedWeapon().effect() != null) {
                                System.out.println();
                                currentEnemy.applyEffect(player.getEquippedWeapon().effect());
                                System.out.println("You applied " + player.getEquippedWeapon().effect().name());
                            }
                        }else if (choice == 2) {
                            System.out.println("Please choose you healing item:");
                            System.out.println();
                            List<Healing> healingItems = new ArrayList<>();
                            int num = 0;
                            for (Map.Entry<Healing, Integer> healing : player.getHealingInventory().entrySet()) {
                                Healing entry = healing.getKey();
                                int count = healing.getValue();
                                System.out.println(" " + num + ". " + entry.name() + " x" + count);
                                healingItems.add(entry);
                                num++;
                            }

                            int healChoice = -1;
                            while (healChoice < 0 || healChoice > healingItems.size()) {
                                healChoice = input.nextInt();
                                if (input.hasNextInt() && healChoice >= 0 && healChoice < healingItems.size()) {
                                    player.applyHealing(healingItems.get(healChoice));
                                }else {
                                    System.out.println("Please enter the number of the item you wish to use");
                                }
                            }

                        }else {
                            System.out.println("Oops! Please type either a 1 or a 2");
                            System.out.println();
                            System.out.println("Make your move!");
                            System.out.println(" 1. Attack");
                            System.out.println(" 2. Heal");
                        }
                    }else {
                        System.out.println("Please enter a number...");
                        System.out.println();
                    }
                }
            }else {
                System.out.println("The " + currentEnemy.getName() + " attacks and does " + currentEnemy.getDamage() + " damage!");
                player.setHp(player.getHp() - currentEnemy.getDamage());
                if (currentEnemy.getEffect().isApplied() && currentEnemy.getEffect() != null) {
                    System.out.println();
                    player.applyEffect(currentEnemy.getEffect());
                    System.out.println(currentEnemy.getName() + " applied " + currentEnemy.getEffect().name());
                }
            }
        }
    }
}