package Events;

import Actors.Actor;
import Armor.Armor;
import Healing.Healing;
import Weapons.Weapon;

public class LootItem<Item> {
    private final String name;
    private final Item item;
    private int count;

    public LootItem(String name, Item item, int count) {
        this.name = name;
        this.item = item;
        this.count = count;
    }

    public void AddItemToInventory(Actor player, int count) {
        if (item instanceof Healing) {
            player.addHealingToInventory(((Healing) item), count);
        }else if (item instanceof Weapon weap) {
            player.addWeaponToInventory(weap.name(), weap);
        }else if (item instanceof Armor arm) {
            player.addArmorToInventory(arm.name(), arm);
        }
    }

    public String getName() {return name;}

    public Item getItem() {return item;}

    public int getCount() {return count;}

    public void setCount(int count) {this.count = count;}
}
