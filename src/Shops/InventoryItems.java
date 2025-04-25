package Shops;

public class InventoryItems<Item> {
    private final Item item;
    private int count;

    public InventoryItems(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
