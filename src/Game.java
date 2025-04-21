import Armor.*;
import Actors.*;
import Healing.*;
import Locations.Location;
import Weapons.*;
import Effects.*;
import Enemies.*;

import java.util.*;

public class Game {
    static Scanner input = new Scanner(System.in);

    private Actor player;
    private Enemy currentEnemy;
    private Location currentLocation;

    public void gameStart() {
        System.out.println("Welcome to the Game!");
        System.out.println();
        System.out.println("Please Choose Your Starting Character by typing the corresponding number:");
        System.out.println("1. Warrior");
        System.out.println("2. Wizard");
        System.out.println();

        Weapon weapon;
        Armor armor;
        Healing healing;

        int choice = 0;
        while (choice != 1 && choice != 2) {
            choice = input.nextInt();

            if (choice == 1) {
                player = new Actor("Warrior", 20, 80, 0);
                weapon = new Weapon("Long Sword", 5, new HitEffect("Armor Piercing", 2, 0, 0, 1.0));
                armor = new Armor("Iron Armor", 5);
                healing = new Healing("Cooked Meat", 5, 2, 0);

                player.addWeaponToInventory(weapon);
                player.addArmorToInventory(armor);
                player.addHealingToInventory(healing, 2);

                player.equipWeapon(weapon);
                player.equipArmor(armor);
            }else if (choice == 2) {
                player = new Actor("Wizard", 15, 85, 0);
                weapon = new Weapon("Magic Staff", 6, new HitEffect("Stun", 0, 0, 100, 0.1));
                armor = new Armor("Cloak", 0);
                healing = new Healing("Healing Potion", 7, 1, 1);

                player.addWeaponToInventory(weapon);
                player.addArmorToInventory(armor);
                player.addHealingToInventory(healing, 2);

                player.equipWeapon(weapon);
                player.equipArmor(armor);
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
        System.out.println("You have chosen " + player.getName() + "!");
        System.out.println();
        System.out.println("Equipped Items: ");
        System.out.println(" Weapon: " + player.getEquippedWeapon().name());
        System.out.println(" Armor " + player.getEquippedArmor().name());
        System.out.println();
        System.out.println("Inventory:");
        player.displayInventory();
    }

    public void startFight(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
        fightSequence();
    }

    public void fightSequence() {
        boolean playersTurn = player.getSpeed() >= currentEnemy.getSpeed();

        while (player.getHp() > 0 && currentEnemy.getHP() > 0) {
            if (playersTurn && player.getSpeed() != 0) {
                System.out.println("Make your move!");
                System.out.println(" 1. Attack");
                System.out.println(" 2. Heal");
                System.out.println();
                int choice = 0;
                while (choice != 1 && choice != 2) {
                    if (input.hasNextInt()) {
                        choice = input.nextInt();
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
                            }else {
                                currentEnemy.setHP(currentEnemy.getHP() - player.getEquippedWeapon().damage());
                            }
                            if (player.getEquippedWeapon().effect().isApplied() && player.getEquippedWeapon().effect() != null) {
                                currentEnemy.applyEffect(player.getEquippedWeapon().effect());
                                System.out.println("You inflicted " + player.getEquippedWeapon().effect().name() + "!");
                                System.out.println();
                            }
                            System.out.println();
                            System.out.println("The " + currentEnemy.getName() + " now has " + currentEnemy.getArmorPoints() + " armor and " + currentEnemy.getHP() + " HP!");
                        }else if (choice == 2) {
                            if (player.getHealingInventory().isEmpty()) {
                                System.out.println();
                                System.out.println("You have no healing items");
                                choice = 0;
                                System.out.println("Make your move!");
                                System.out.println(" 1. Attack");
                                System.out.println(" 2. Heal");
                                System.out.println();
                                continue;
                            }
                            if (player.getHp() >= player.getMaxHP()) {
                                System.out.println();
                                System.out.println("You're already at full health!");
                                choice = 0;
                                System.out.println("Make your move!");
                                System.out.println(" 1. Attack");
                                System.out.println(" 2. Heal");
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
                                if (healChoice >= 0 && healChoice < healingItems.size()) {
                                    player.applyHealing(healingItems.get(healChoice));
                                }else {
                                    System.out.println("Please enter the number of the item you wish to use");
                                    healChoice = -1;
                                }
                            }

                        }else {
                            System.out.println("Oops! Please type either a 1 or a 2");
                            System.out.println();
                            System.out.println("Make your move!");
                            System.out.println(" 1. Attack");
                            System.out.println(" 2. Heal");
                            System.out.println();
                        }
                    }else {
                        System.out.println("Please enter a number...");
                        System.out.println();
                    }
                }
            }else if (currentEnemy.getSpeed() != 0){
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
                }else {
                    currentEnemy.setHP(currentEnemy.getHP() - player.getEquippedWeapon().damage());
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

    public void enterLocation(Location currentLocation) throws InterruptedException {
        this.currentLocation = currentLocation;
        enter();
    }

    public void enter() throws InterruptedException {
        currentLocation.enterLocation();
    }
}