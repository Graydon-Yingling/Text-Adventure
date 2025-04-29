package Locations;
import Actors.Actor;
import Armor.Armor;
import Effects.HitEffect;
import Healing.Healing;
import Shops.DisplayShops;
import Shops.InventoryItems;
import Weapons.Weapon;

import java.util.*;

public class StartingShop implements Location{

    private final Map<String, InventoryItems<Healing>> shopHealingInventory = new HashMap<>();
    private final Map<String, Weapon> shopWeaponInventory = new HashMap<>();
    private final Map<String, Armor> shopArmorInventory = new HashMap<>();

    private final List<String> shopHealingList = new ArrayList<>();
    private final List<String> shopWeaponList = new ArrayList<>();
    private final List<String> shopArmorList = new ArrayList<>();

    {
        shopHealingInventory.put("Healing Potion", new InventoryItems<>(new Healing("Healing Potion", 7, 1, 1), 2));
        shopHealingList.add("Healing Potion");

        shopWeaponInventory.put("Dagger", new Weapon("Dagger", 2, new HitEffect("Bleed", 2, 0, 0, 0.2)));
        shopWeaponList.add("Dagger");

        shopArmorInventory.put("Leather Armor", new Armor("Leather Armor", 3));
        shopArmorList.add("Leather Armor");
    }

    boolean hasEntered = false;

    @Override
    public String getLocationName() {return "The Broken Toe";}

    @Override
    @SuppressWarnings({"BusyWait"})
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

        LocationInteraction goTo = new LocationInteraction();
        while (true) {
            int choice = goTo.subInteract(LocationRegistry.get(getLocationName()));
            if (choice == 1) {
                shop(player);
            }else if (choice == 2) {
                System.out.println();
                System.out.println("You approach Axel...");
                Thread.sleep(1200);
                System.out.println("Axel: Gooday, sorry for the mostly empty shop, we don't get a lot of supply as of late, but let me know if you'd like to buy!");
                Thread.sleep(2000);
                System.out.println("Axel: Oh yeah, since my creator didn't have time to create currency, you can just take what you need!");
            }else if (choice == 3) {
                System.out.println();
                System.out.println("You leave the shop...");
                LocationRegistry.get("The Clearing Outpost").enterLocation(player);
                return;
            }
        }
    }

    private void shop(Actor player) {
        System.out.println();
        System.out.println("You walk around the shop, taking a mental note of what's available...");
        System.out.println();
        DisplayShops currentShop = new DisplayShops();
        currentShop.shopInteraction(shopHealingList, shopWeaponList, shopArmorList, shopWeaponInventory, shopArmorInventory, shopHealingInventory, player);
        return;
    }

    @Override
    public List<String> getAvailableActions() {
        return List.of("Shop", "Talk to Axel", "Leave");
    }
}