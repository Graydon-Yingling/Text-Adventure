package Locations;
import Actors.Actor;
import Healing.Healing;
import Shops.DisplayShops;
import Shops.InventoryItems;

import java.util.*;

public class StartingTavern implements Location{
    static Scanner input = new Scanner(System.in);

    private final Map<String, InventoryItems<Healing>> shopHealingInventory = new HashMap<>();
    private final List<String> shopHealingList = new ArrayList<>();

    {
        shopHealingInventory.put("Apple", new InventoryItems<>(new Healing("Apple", 3, 1, 0), 8));
        shopHealingInventory.put("Cooked Meat", new InventoryItems<>(new Healing("Cooked Meat", 5, 2, 0), 4));

        shopHealingList.add("Apple");
        shopHealingList.add("Cooked Meat");
    }

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

        LocationInteraction goTo = new LocationInteraction();
        while (true) {
            int choice = goTo.subInteract(LocationRegistry.get(getLocationName()));
            if (choice == 1) {
                System.out.println("Tammy: Just remember, you can always rest to regain health and armor, and what you eat now won't be available to take later.");
                Thread.sleep(1200);
                System.out.println("Tammy: But here's the menu for ya!");
                eat(player);
            }else if (choice == 2) {
                System.out.println();
                System.out.println("Tammy: Here's what I've got in stock...");
                shop(player);
            }else if (choice == 3) {
                System.out.println();
                System.out.println("Tammy: Hey there, not a lot of dining options, supply got cut off recently, but make yourself at home!");
                Thread.sleep(2000);
                System.out.println("Tammy: By the way, everything is free because my creator didn't add currency yet, so it's all you can eat!");
            }else if (choice == 4) {
                System.out.println();
                System.out.println("Tammy: Alrighty, here's a key, and your room will up be upstairs. See ya in the morning!");
                System.out.println();
                Thread.sleep(1000);
                System.out.println("You head to your room and sleep the night away...");
                player.setHp(player.getMaxHP());
                player.setArmorPoints(player.getEquippedArmor().armorPoints());
                Thread.sleep(4000);
                System.out.println();
                System.out.println("You're back downstairs now feeling rejuvenated...and your armor appears to have magically regenerated");
                System.out.println();
                Thread.sleep(1000);
                System.out.println("Tammy: Morning! Let me know what you need!");
            }else if (choice == 5) {
                System.out.println();
                System.out.println("You leave the tavern...");
                LocationRegistry.get("The Clearing Outpost").enterLocation(player);
                return;
            }
        }
    }

    public void eat(Actor player) {
        List<String> itemList;
        itemList = shopHealingList;
        System.out.println("Food:");
        if (shopHealingInventory.isEmpty()) {
            System.out.println();
            System.out.println("Tammy: Sorry! Out of stock....");
            return;
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                String healName = itemList.get(i);
                InventoryItems<Healing> healItem = shopHealingInventory.get(healName);
                System.out.println(" " + (i + 1) + ". " + healName + " x" + healItem.getCount() + " - " + healItem.getItem().healthGained() + " healing");
            }
        }
        System.out.println(" " + (itemList.size() + 1) + ". Go back");
        System.out.println();
        while (true) {
            if (input.hasNextInt()) {
                int itemChoice = input.nextInt();
                input.nextLine();
                System.out.println();
                if (itemChoice == (itemList.size() + 1)) {
                    return;
                }else if (itemChoice >= 1 && itemChoice <= itemList.size()) {
                    String itemName = itemList.get(itemChoice - 1);
                    System.out.println();
                    if (player.getHp() == player.getMaxHP()) {
                        System.out.println();
                        System.out.println("You don't need any more food right now, you're already at full health...");
                    }else {
                        player.addHealingToInventory(shopHealingInventory.get(itemName).getItem(), 1);
                        player.applyHealing(shopHealingInventory.get(itemName).getItem());
                        shopHealingInventory.get(itemName).setCount(shopHealingInventory.get(itemName).getCount() - 1);
                        if (shopHealingInventory.get(itemName).getCount() == 0) {
                            itemList.remove(itemChoice - 1);
                            shopHealingInventory.remove(itemName);
                        }
                    }
                    return;
                }
            }else {
                input.nextLine();
                System.out.println("Please enter a number...");
                System.out.println();
                for (int i = 0; i < itemList.size(); i++) {
                    String healName = itemList.get(i);
                    InventoryItems<Healing> healItem = shopHealingInventory.get(healName);
                    System.out.println(" " + (i + 1) + ". " + healName + " x" + healItem.getCount() + " - " + healItem.getItem().healthGained() + " healing");
                }
            }
        }
    }

    public void shop(Actor player) {
        DisplayShops currentShop = new DisplayShops();
        System.out.println();
        currentShop.shopInteraction(shopHealingList, null, null, null, null, shopHealingInventory, player);
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("Dine in", "Take Out", "Talk to Tammy", "Rest", "Leave");
    }
}