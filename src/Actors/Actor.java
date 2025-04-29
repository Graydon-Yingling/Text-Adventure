package Actors;

import Armor.Armor;
import Effects.HitEffect;
import Healing.Healing;
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
    private final Map<Healing, Integer> healingInventory = new HashMap<>();

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
        if (healingInventory.getOrDefault(healing, 0) <= 0) {
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

        int remaining = healingInventory.get(healing) - 1;
        if (remaining <= 0) {
            healingInventory.remove(healing);
            healingNames.remove(healing.name());
        } else {
            healingInventory.put(healing, remaining);
        }

        System.out.println("Gained " + (this.hp - before) + " health from " + healing.name());
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
        int current = healingInventory.getOrDefault(heal, 0); // getOrDefault() method comes from ChatGPT
        healingInventory.put(heal, current + amnt);
        healingNames.add(heal.name());
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
            }
            inventoryChoice = weaponNames;
        }else if (choice == 2) {
            System.out.println(" - Armor:");
            for (String armorName : armorNames) {
                System.out.println("  " + count + ". " + armorName + " - " + armorInventory.get(armorName).armorPoints() + " armor");
            }
            inventoryChoice = armorNames;
        }else if (choice == 3) {
            System.out.println(" - Healing Items:");
            for (Map.Entry<Healing, Integer> healing : this.getHealingInventory().entrySet()) {
                Healing entry = healing.getKey();
                int healCount = healing.getValue();
                System.out.println("  " + count + ". " + entry.name() + " x" + healCount);
            }
            inventoryChoice = healingNames;
        }
        System.out.println("  " + (count + 1) + ". Back");
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
                    displayInventory(choice);
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

        System.out.println();
        System.out.println("What will you choose?");
        int itemChoice;
        while (true) {
            if (input.hasNextInt()) {
                itemChoice = input.nextInt() - 1;
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
                        for (Healing heal : healingInventory.keySet()) { // Solution from ChatGPT (My own understanding is that it creates a Set<Key>
                            if (heal.name().equals(healItem)) {          // from the Map's keys and then accesses the key class directly. It helps by not
                                selectedHealing = heal;                  // making me need to restructure my healingInventory)
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
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public Map<String, Weapon> getWeaponInventory() {
        return weaponInventory;
    }

    public Map<String, Armor> getArmorInventory() {
        return armorInventory;
    }

    public Map<Healing, Integer> getHealingInventory() {
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
