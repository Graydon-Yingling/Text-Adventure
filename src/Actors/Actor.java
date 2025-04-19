package Actors;

import Armor.Armor;
import Effects.HitEffect;
import Healing.Healing;
import Weapons.Weapon;

import java.util.*;

public class Actor {
    private final String name;
    private double hp;
    private double speed;
    private double armorPoints;
    private final double maxHP;

    private Weapon equippedWeapon;
    private Armor equippedArmor;

    private final Set<Weapon> weaponInventory = new HashSet<>();
    private final Set<Armor> armorInventory = new HashSet<>();
    private final Map<Healing, Integer> healingInventory = new HashMap<>();

    public Actor(String name, double hp, double speed, double armor) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
        this.armorPoints = armor;
        this.maxHP = hp;
    }

    public void applyEffect(HitEffect effect) {
        this.hp -= effect.hpDamage();
        this.speed += effect.speedChange();
        this.armorPoints -= effect.armorDamage();
    }

    public void applyHealing(Healing healing) {
        if (healingInventory.getOrDefault(healing, 0) <= 0) {
            System.out.println("You don't have this healing item");
            return;
        }

        if (this.hp >= maxHP) {
            System.out.println("You are already at max health");
            return;
        }

        double before = this.hp;
        this.hp += healing.healthGained();
        if (this.hp > maxHP) {
            this.hp = maxHP;
        }

        healingInventory.put(healing, healingInventory.get(healing) - 1);
        System.out.println("Gained " + (this.hp - before) + " health from " + healing.name());
    }

    public void addWeaponToInventory(Weapon weapon) {
        weaponInventory.add(weapon);
    }

    public void addArmorToInventory(Armor armor) {
        armorInventory.add(armor);
    }

    public void addHealingToInventory (Healing heal, int amnt) {
        int current = healingInventory.getOrDefault(heal, 0);//getOrDefault() method comes from ChatGPT
        healingInventory.put(heal, current + amnt);
    }

    public void equipWeapon(Weapon weapon) {
        if (weaponInventory.contains(weapon)) {
            this.equippedWeapon = weapon;
        }
    }

    public void equipArmor(Armor armor) {
        if (armorInventory.contains(armor)) {
            this.equippedArmor = armor;
            this.armorPoints += armor.armorPoints();
        }
    }

    public void displayInventory(Actor actor) {
        System.out.println(" - Weapons:");
        for (Weapon weapon : actor.getWeaponInventory()) {
            System.out.println("  ~ " + weapon.name());
        }
        System.out.println(" - Armor:");
        for (Armor armor : actor.getArmorInventory()) {
            System.out.println("  ~ " + armor.name());
        }
        System.out.println(" - Healing Items:");
        for (Map.Entry<Healing, Integer> healing : actor.getHealingInventory().entrySet()) {
            Healing entry = healing.getKey();
            int count = healing.getValue();
            System.out.println("  ~ " + entry.name() + " x" + count);
        }
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public Set<Weapon> getWeaponInventory() {
        return weaponInventory;
    }

    public Set<Armor> getArmorInventory() {
        return armorInventory;
    }

    public Map<Healing, Integer> getHealingInventory() {
        return healingInventory;
    }

    public String getName() {return name;}

    public double getHp() {return hp;}

    public double getSpeed() {return speed;}

    public double getArmor() {return armorPoints;}

    public void setHp(double hp) {this.hp = hp;}

    public void setSpeed(int speed) {this.speed = speed;}

    public void setArmor(double armorPoints) {this.armorPoints = armorPoints;}
}
