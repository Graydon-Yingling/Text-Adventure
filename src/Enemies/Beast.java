package Enemies;

import Effects.HitEffect;

public class Beast {
    private final String name;
    private final double maxHP;
    private final double damage;
    private double hp;
    private double speed;

    private HitEffect effect;

    public Beast(String name, double hp, double damage, double speed) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.maxHP = hp;
    }

    public void applyEffect(HitEffect effect) {
        this.hp -= effect.hpDamage();
        this.speed += effect.speedChange();
    }

    public String getName() {return name;}

    public double getHp() {return hp;}

    public double getDamage() {return damage;}

    public double getSpeed() {return speed;}

    public double getMaxHP() {return maxHP;}

    public void setHp(double hp) {this.hp = hp;}

    public void setSpeed(double speed) {this.speed = speed;}
}
