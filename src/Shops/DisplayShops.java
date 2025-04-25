package Shops;

import Actors.Actor;
import Armor.Armor;
import Healing.Healing;
import Weapons.Weapon;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DisplayShops {
    static Scanner input = new Scanner(System.in);

    public void shopInteraction(
            List<String> shopHealingList,
            List<String> shopWeaponList,
            List<String> shopArmorList,

            Map<String, Weapon> shopWeaponInventory,
            Map<String, Armor> shopArmorInventory,
            Map<String, InventoryItems<Healing>> shopHealingInventory,

            Actor player) {

        int choice;
        while (true) {
            System.out.println("What would you like to buy?:");
            System.out.println(" 1. Healing");
            System.out.println(" 2. Weapons");
            System.out.println(" 3. Armor");
            System.out.println(" 4. Go back");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                if (choice < 4) {
                    break;
                }else if (choice == 4) {
                    System.out.println();
                    System.out.println("You decide not to buy things right now...");
                    return;
                }else {
                    System.out.println();
                    System.out.println("Oops! Please enter a valid number...");
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Please choose a number...");
            }
        }

        List<String> itemList;

        System.out.println();
        if (choice == 1) {
            itemList = shopHealingList;
            System.out.println("Healing:");
            for (int i = 0; i < itemList.size(); i++) {
                String healName = itemList.get(i);
                InventoryItems<Healing> healItem = shopHealingInventory.get(healName);
                System.out.println(" " + (i + 1) + ". " + healName + " x" + healItem.getCount() + " - " + healItem.getItem().healthGained() + " healing");
            }
        }else if (choice == 2) {
            itemList = shopWeaponList;
            System.out.println("Weapons:");
            for (int i = 0; i < itemList.size(); i++) {
                String weaponName = itemList.get(i);
                Weapon weapon = shopWeaponInventory.get(weaponName);
                System.out.println(" " + (i + 1) + ". " + weaponName + " - " + weapon.damage() + " damage");
            }
        }else if (choice == 3) {
            itemList = shopArmorList;
            System.out.println("Armor:");
            for (int i = 0; i < itemList.size(); i++) {
                String armorName = itemList.get(i);
                Armor armor = shopArmorInventory.get(armorName);
                System.out.println(" " + (i + 1) + ". " + armorName + " - " + armor.armorPoints() + " armor");
            }
        }else {
            System.out.println();
            System.out.println("Oops! Please enter a valid number...");
            return;
        }
        System.out.println(" " + (itemList.size() + 1) + ". Go back");
        System.out.println();

        while (true) {
            if (input.hasNextInt()) {
                System.out.println();
                int itemChoice = input.nextInt();
                if (itemChoice == (itemList.size() + 1)) {
                    return;
                }else if (itemChoice >= 1 && itemChoice <= itemList.size()) {
                    String itemName = itemList.get(itemChoice - 1);
                    System.out.println();
                    System.out.println("You added 1 " + itemName + " to your inventory!");

                    if (choice == 1) {
                        player.addHealingToInventory(shopHealingInventory.get(itemName).getItem(), 1);
                        shopHealingInventory.get(itemName).setCount(shopHealingInventory.get(itemName).getCount() - 1);
                        if (shopHealingInventory.get(itemName).getCount() == 0) {
                            itemList.remove(itemChoice - 1);
                            shopHealingInventory.remove(itemName);
                        }
                        return;
                    }else if (choice == 2) {
                        player.addWeaponToInventory(shopWeaponInventory.get(itemName));
                        shopWeaponInventory.remove(itemName);
                        itemList.remove(itemChoice - 1);
                        return;
                    }else {
                        player.addArmorToInventory(shopArmorInventory.get(itemName));
                        shopArmorInventory.remove(itemName);
                        itemList.remove(itemChoice - 1);
                        return;
                    }
                }else {
                    System.out.println();
                    System.out.println("Oops! Please enter a valid number...");
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Please enter a number...");
            }
        }
    }
}
