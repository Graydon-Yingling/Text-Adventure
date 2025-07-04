package Enemies;

import Effects.HitEffect;

public class Beast implements Enemy{
    private final String name;
    private final double damage;
    private final double maxSpeed;
    private double hp;
    private double speed;
    private final HitEffect bleed = new HitEffect("Bleed", 2, 0, 0, 0.15);

    public Beast(String name, double hp, double damage, double speed) {
        this.name = name;
        this.hp = hp;
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
    public double getArmorPoints() {return 0;}

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
    public void setArmorPoints(double armorPoints) {
    }

    @Override
    public double getMaxSpeed() {return maxSpeed;}
}
