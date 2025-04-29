package Enemies;

import Effects.HitEffect;

public class Undead implements Enemy{
    private final String name;
    private final double damage;
    private final double maxSpeed;
    private double hp;
    private double armorPoints;
    private double speed;
    private final HitEffect bleed = new HitEffect("Poison", 4, 0, 0, 0.15);

    public Undead(String name, double hp, double armorPoints, double damage, double speed) {
        this.name = name;
        this.hp = hp;
        this.armorPoints = armorPoints;
        this.damage = damage;
        this.speed = speed;
        this.maxSpeed = speed;
    }

    @Override
    public void applyEffect(HitEffect effect) {
        this.hp -= effect.hpDamage();
        this.speed += effect.speedChange();
    }

    @Override
    public String getName() {return name;}

    @Override
    public double getArmorPoints() {return armorPoints;}

    @Override
    public double getHP() {return hp;}

    @Override
    public double getDamage() {return damage;}

    @Override
    public double getSpeed() {return speed;}

    @Override
    public HitEffect getEffect() {return bleed;}

    @Override
    public void setHP(double hp) {this.hp = hp;}

    @Override
    public void setSpeed(double speed) {this.speed = speed;}

    @Override
    public void setArmorPoints(double armorPoints) {this.armorPoints = armorPoints;}

    @Override
    public double getMaxSpeed() {return maxSpeed;}
}
