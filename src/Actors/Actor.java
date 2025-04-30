package Actors;

import Armor.Armor;
import Effects.HitEffect;
import Healing.Healing;
import Shops.InventoryItems;
import Weapons.Weapon;

import java.util.*;

public class Actor {
    static Scanner input = new Scanner(System.in);

    private final String name;
    private double hp;
    private double speed;
    private final double maxSpeed;
    private double armorPoints;
    private final double maxHP;

    private Weapon equippedWeapon;
    private Armor equippedArmor;

    private final Map<String, Weapon> weaponInventory = new HashMap<>();
    private final Map<String, Armor> armorInventory = new HashMap<>();
    private final Map<String, InventoryItems<Healing>> healingInventory = new HashMap<>();

    private final List<String> weaponNames = new ArrayList<>();
    private final List<String> armorNames = new ArrayList<>();
    private final List<String> healingNames = new ArrayList<>();

    private List<String> inventoryChoice;

    public Actor(String name, double hp, double speed, double armor) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
        this.maxSpeed = speed;
        this.armorPoints = armor;
        this.maxHP = hp;
    }

    public void applyEffect(HitEffect effect) {
        this.hp -= effect.hpDamage();
        this.speed -= effect.speedChange();
        this.hp -= effect.armorDamage();
    }

    public void applyHealing(Healing healing) {
        String healName = healing.name();

        if (!healingInventory.containsKey(healName)) {
            System.out.println();
            System.out.println("You don't have this healing item");
            return;
        }

        if (this.hp >= maxHP) {
            System.out.println();
            System.out.println("You are already at max health");
            return;
        }

        double before = this.hp;
        this.hp += healing.healthGained();
        if (this.hp > maxHP) {
            this.hp = maxHP;
        }

        InventoryItems<Healing> item = healingInventory.get(healName);
        int remaining = item.getCount() - 1;
        if (remaining <= 0) {
            healingInventory.remove(healName);
            healingNames.remove(healName);
        } else {
            item.setCount(remaining);
        }

        System.out.println("Gained " + (this.hp - before) + " health from " + healName);
        System.out.println();
    }

    public void addWeaponToInventory(String name, Weapon weapon) {
        weaponInventory.put(name, weapon);
        weaponNames.add(name);
    }

    public void addArmorToInventory(String name, Armor armor) {
        armorInventory.put(name, armor);
        armorNames.add(name);
    }

    public void addHealingToInventory (Healing heal, int amnt) {
        String name = heal.name();

        //If-Else block came from ChatGPT because it was a last minute change
        if (healingInventory.containsKey(name)) {
            InventoryItems<Healing> existing = healingInventory.get(name);
            existing.setCount(existing.getCount() + amnt);
        } else {
            healingInventory.put(name, new InventoryItems<>(heal, amnt));
        }

        if (!healingNames.contains(heal.name())) {
            healingNames.add(heal.name());
        }
    }

    public void equipWeapon(String name, Weapon weapon) {
        if (weaponInventory.containsKey(name) && (equippedWeapon == null || !equippedWeapon.equals(weapon))) {
            this.equippedWeapon = weapon;
            System.out.println("You equipped " + name + "!");
        }else {
            System.out.println();
            System.out.println("You either already have this weapon equipped or do not own this weapon...");
        }
    }

    public void equipArmor(String name, Armor armor) {
        if (armorInventory.containsKey(name) && (equippedArmor == null || !equippedArmor.equals(armor))) {
            this.equippedArmor = armor;
            this.armorPoints = armor.armorPoints();
            System.out.println("You equipped " + name + "!");
        }else {
            System.out.println();
            System.out.println("You either already have this armor equipped or do not own this armor...");
        }
    }

    public void displayInventory(int choice) {
        int count = 1;
        if (choice == 1) {
            System.out.println(" - Weapons:");
            for (String weaponName : weaponNames) {
                System.out.println("  " + count + ". " + weaponName + " - " + weaponInventory.get(weaponName).damage() + " damage");
                count++;
            }
            inventoryChoice = weaponNames;
        }else if (choice == 2) {
            System.out.println(" - Armor:");
            for (String armorName : armorNames) {
                System.out.println("  " + count + ". " + armorName + " - " + armorInventory.get(armorName).armorPoints() + " armor");
                count++;
            }
            inventoryChoice = armorNames;
        }else if (choice == 3) {
            System.out.println(" - Healing Items:");
            for (String healName : healingNames) {
                System.out.println("  " + count + ". " + healName + " x" + healingInventory.get(healName).getCount() + " - " + healingInventory.get(healName).getItem().healthGained() + " healing");
                count++;
            }
            inventoryChoice = healingNames;
        }
        System.out.println("  " + count + ". Back");
        System.out.println();
    }

    public void inventoryInteraction() {
        System.out.println();
        int choice;
        while (true) {
            System.out.println();
            System.out.println("HP: " + getHp() + " / " + getMaxHP());
            System.out.println("Armor: " + getArmorPoints() + " / " + getEquippedArmor().armorPoints());
            System.out.println();
            System.out.println("What would you like to do?:");
            System.out.println(" 1. Weapons");
            System.out.println(" 2. Armor");
            System.out.println(" 3. Healing");
            System.out.println(" 4. Back");
            System.out.println();
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine();
                if (choice > 0 && choice < 4) {
                    int itemChoice;
                    while (true) {
                        displayInventory(choice);
                        System.out.println();
                        System.out.println("What will you choose?");
                        if (input.hasNextInt()) {
                            itemChoice = input.nextInt() - 1;
                            input.nextLine();
                            if (itemChoice >= 0 && itemChoice < inventoryChoice.size()) {
                                if (choice == 1) {
                                    System.out.println();
                                    String weapon = inventoryChoice.get(itemChoice);
                                    equipWeapon(weapon, weaponInventory.get(weapon));
                                    break;
                                }else if (choice == 2) {
                                    System.out.println();
                                    String armor = inventoryChoice.get(itemChoice);
                                    equipArmor(armor, armorInventory.get(armor));
                                    break;
                                }else {
                                    System.out.println();
                                    String healItem = inventoryChoice.get(itemChoice);
                                    Healing selectedHealing = null;
                                    for (String heal : healingNames) {
                                        if (heal.equals(healItem)) {
                                            selectedHealing = healingInventory.get(heal).getItem();
                                            break;
                                        }
                                    }
                                    if (selectedHealing != null) {
                                        applyHealing(selectedHealing);
                                    }else {
                                        System.out.println();
                                        System.out.println("Healing item was not found...");
                                    }
                                    break;
                                }
                            }else if (itemChoice == inventoryChoice.size()) {
                                inventoryInteraction();
                                break;
                            }else {
                                System.out.println();
                                System.out.println("Oops! Please enter a valid number");
                            }
                        }else {
                            input.nextLine();
                            System.out.println();
                            System.out.println("Please enter a number...");
                            System.out.println();
                        }
                    }
                    break;
                }else if (choice == 4) {
                    return;
                }else {
                    System.out.println();
                    System.out.println("Oops! Please enter a valid number...");
                    System.out.println();
                }
            }else {
                input.nextLine();
                System.out.println();
                System.out.println("Please enter a number...");
                System.out.println();
            }
        }
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public Map<String, InventoryItems<Healing>> getHealingInventory() {
        return healingInventory;
    }

    public String getName() {return name;}

    public double getHp() {return hp;}

    public double getMaxHP() {return maxHP;}

    public double getSpeed() {return speed;}

    public double getArmorPoints() {return armorPoints;}

    public void setHp(double hp) {this.hp = hp;}

    public void setSpeed(double speed) {this.speed = speed;}

    public double getMaxSpeed() {return this.maxSpeed;}

    public void setArmorPoints(double armorPoints) {this.armorPoints = armorPoints;}
}
