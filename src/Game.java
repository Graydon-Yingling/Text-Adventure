import Armor.*;
import Actors.*;
import Fights.FightSequence;
import Healing.*;
import Locations.Location;
import Weapons.*;
import Effects.*;
import Enemies.*;

import java.util.*;

public class Game {
    static Scanner input = new Scanner(System.in);

    private Actor player;

    public void gameStart() {
        Weapon weapon;
        String weaponName;
        Armor armor;
        String armorName;
        Healing healing;

        System.out.println();
        System.out.println("Welcome to the Game!");
        System.out.println();

        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.println("Please Choose Your Starting Character by typing the corresponding number:");
            System.out.println("1. Warrior");
            System.out.println("2. Wizard");
            System.out.println();
            if (input.hasNextInt()){
                choice = input.nextInt();
                input.nextLine();
                if (choice == 1) {
                    player = new Actor("Warrior", 20, 80, 0);
                    weapon = new Weapon("Long Sword", 5, new HitEffect("Armor Piercing", 2, 0, 0, 1.0));
                    weaponName = "Long Sword";
                    armor = new Armor("Iron Armor", 5);
                    armorName = "Iron Armor";
                    healing = new Healing("Cooked Meat", 5, 2, 0);

                    System.out.println();
                    System.out.println("You have chosen " + player.getName() + "!");

                    player.addWeaponToInventory(weaponName, weapon);
                    player.addArmorToInventory(armorName, armor);
                    player.addHealingToInventory(healing, 2);

                    player.equipWeapon(weaponName, weapon);
                    player.equipArmor(armorName, armor);
                }else if (choice == 2) {
                    player = new Actor("Wizard", 15, 85, 0);
                    weapon = new Weapon("Magic Staff", 6, new HitEffect("Stun", 0, 0, 100, 0.1));
                    weaponName = "Magic Staff";
                    armor = new Armor("Cloak", 0);
                    armorName = "Cloak";
                    healing = new Healing("Healing Potion", 7, 1, 1);

                    System.out.println();
                    System.out.println("You have chosen " + player.getName() + "!");

                    player.addWeaponToInventory(weaponName, weapon);
                    player.addArmorToInventory(armorName, armor);
                    player.addHealingToInventory(healing, 2);

                    player.equipWeapon(weaponName, weapon);
                    player.equipArmor(armorName, armor);
                }else {
                    System.out.println();
                    System.out.println("Oops! Please type either a 1 or a 2");
                    System.out.println();
                }
            }else {
                input.nextLine();
                System.out.println("Please enter a number...");
                System.out.println();
            }
        }
    }

    public void startFight(Enemy currentEnemy) {
        FightSequence fight = new FightSequence();
        fight.fight(player, currentEnemy);
    }

    public void enterLocation(Location currentLocation) throws InterruptedException {
        currentLocation.enterLocation(this.player);
    }

    public Actor getPlayer() {return this.player;}
}