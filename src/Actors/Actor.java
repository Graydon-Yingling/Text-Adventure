package Actors;

import Armor.Armor;
import Effects.HitEffect;
import Healing.Healing;
import Weapons.Weapon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Actor {
    private final String name;
    private double hp;
    private double speed;
    private double armorPoints;
    private double maxHP;

    private Weapon equippedWeapon;
    private Armor equippedArmor;

    private Set<Weapon> weaponInventory = new HashSet<>();
    private Set<Armor> armorInventory = new HashSet<>();
    private Map<Healing, Integer> healingInventory = new HashMap<>();

    public Actor(String name, double hp, double speed, double armor) {
        this.name = name;
        this.hp = hp;
        this.speed = speed;
        this.armorPoints = armor;
        this.maxHP = hp;
    }

    public void applyEffect(HitEffect effect) {
        this.hp -= effect.getHpDamage();
        this.speed += effect.getSpeedChange();
        this.armorPoints -= effect.getArmorDamage();
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
        this.hp += healing.getHealthGained();
        if (this.hp > maxHP) {
            this.hp = maxHP;
        }

        healingInventory.put(healing, healingInventory.get(healing) - 1);
        System.out.println("Gained " + (this.hp - before) + " health from " + healing.getName());
    }

    public void addWeaponToInventory(Weapon weapon) {
        weaponInventory.add(weapon);
    }

    public void addArmorToInventory(Armor armor) {
        armorInventory.add(armor);
    }

    public void addHealingToInventory (Healing heal) {
        healingInventory.put(heal, healingInventory.getOrDefault(heal, 0) + 1); //getOrDefault() method comes from ChatGPT
    }

    public void equipWeapon(Weapon weapon) {
        if (weaponInventory.contains(weapon)) {
            this.equippedWeapon = weapon;
        }
    }

    public void equipArmor(Armor armor) {
        if (armorInventory.contains(armor)) {
            this.equippedArmor = armor;
        }
    }

    public String getName() {return name;}

    public double getHp() {return hp;}

    public double getSpeed() {return speed;}

    public double getArmor() {return armorPoints;}

    public void setHp(double hp) {this.hp = hp;}

    public void setSpeed(int speed) {this.speed = speed;}

    public void setArmor(double armorPoints) {this.armorPoints = armorPoints;}
}
