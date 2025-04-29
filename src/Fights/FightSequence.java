package Fights;

import Actors.Actor;
import Enemies.Enemy;
import Healing.Healing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FightSequence {
    static Scanner input = new Scanner(System.in);

    public void fight(Actor player, Enemy currentEnemy) {
        boolean playersTurn = player.getSpeed() >= currentEnemy.getSpeed();

        while (player.getHp() > 0 && currentEnemy.getHP() > 0) {
            if (playersTurn && player.getSpeed() != 0) {
                int choice = 0;
                while (choice != 1 && choice != 2) {
                    System.out.println("Make your move!");
                    System.out.println(" 1. Attack");
                    System.out.println(" 2. Heal");
                    System.out.println();
                    if (input.hasNextInt()) {
                        choice = input.nextInt();
                        input.nextLine();
                        if (choice == 1) {
                            System.out.println();
                            System.out.println("You attack and deal " + player.getEquippedWeapon().damage() + " damage!");
                            System.out.println();
                            if (currentEnemy.getArmorPoints() > 0) {
                                double currentArmor = currentEnemy.getArmorPoints();
                                currentEnemy.setArmorPoints(currentEnemy.getArmorPoints() - player.getEquippedWeapon().damage());
                                if (currentEnemy.getArmorPoints() < 0) {
                                    currentEnemy.setArmorPoints(0);
                                    currentEnemy.setHP(currentEnemy.getHP() - (player.getEquippedWeapon().damage() - currentArmor));
                                }
                            } else {
                                currentEnemy.setHP(currentEnemy.getHP() - player.getEquippedWeapon().damage());
                            }
                            if (player.getEquippedWeapon().effect().isApplied() && player.getEquippedWeapon().effect() != null) {
                                currentEnemy.applyEffect(player.getEquippedWeapon().effect());
                                System.out.println("You inflicted " + player.getEquippedWeapon().effect().name() + "!");
                                System.out.println();
                            }
                            System.out.println();
                            System.out.println("The " + currentEnemy.getName() + " now has " + currentEnemy.getArmorPoints() + " armor and " + currentEnemy.getHP() + " HP!");
                        } else if (choice == 2) {
                            if (player.getHealingInventory().isEmpty()) {
                                System.out.println();
                                System.out.println("You have no healing items");
                                choice = 0;
                                System.out.println();
                                continue;
                            }
                            if (player.getHp() >= player.getMaxHP()) {
                                System.out.println();
                                System.out.println("You're already at full health!");
                                choice = 0;
                                System.out.println();
                                continue;
                            }
                            System.out.println("Please choose your healing item:");
                            System.out.println();
                            List<Healing> healingItems = new ArrayList<>();
                            int num = 0;
                            for (Map.Entry<Healing, Integer> healing : player.getHealingInventory().entrySet()) {
                                Healing entry = healing.getKey();
                                int count = healing.getValue();
                                System.out.println(" " + (num + 1) + ". " + entry.name() + " x" + count);
                                healingItems.add(entry);
                                num++;
                            }

                            System.out.println();
                            int healChoice = -1;
                            while (healChoice < 0 || healChoice > healingItems.size()) {
                                healChoice = input.nextInt() - 1;
                                input.nextLine();
                                if (healChoice >= 0 && healChoice < healingItems.size()) {
                                    player.applyHealing(healingItems.get(healChoice));
                                } else {
                                    System.out.println("Please enter the number of the item you wish to use");
                                    healChoice = -1;
                                }
                            }

                        } else {
                            System.out.println("Oops! Please type either a 1 or a 2");
                            System.out.println();
                        }
                    } else {
                        input.nextLine();
                        System.out.println("Please enter a number...");
                        choice = 0;
                    }
                }
            } else if (currentEnemy.getSpeed() != 0) {
                System.out.println();
                System.out.println("The " + currentEnemy.getName() + " attacks and does " + currentEnemy.getDamage() + " damage!");
                System.out.println();
                if (player.getArmorPoints() > 0) {
                    double currentArmor = player.getArmorPoints();
                    player.setArmorPoints(player.getArmorPoints() - currentEnemy.getDamage());
                    if (player.getArmorPoints() < 0) {
                        player.setArmorPoints(0);
                        player.setHp(player.getHp() - (currentEnemy.getDamage() - currentArmor));
                    }
                } else {
                    player.setHp(player.getHp() - currentEnemy.getDamage());
                }
                if (currentEnemy.getEffect().isApplied() && currentEnemy.getEffect() != null) {
                    System.out.println();
                    player.applyEffect(currentEnemy.getEffect());
                    System.out.println(currentEnemy.getName() + " inflicted " + currentEnemy.getEffect().name() + "!");
                    System.out.println();
                }
                System.out.println("You now have " + player.getArmorPoints() + " armor and " + player.getHp() + " HP!");
                System.out.println();
            }
            playersTurn = !playersTurn;
            player.setSpeed(player.getMaxSpeed());
            currentEnemy.setSpeed(currentEnemy.getMaxSpeed());
        }
    }
}
