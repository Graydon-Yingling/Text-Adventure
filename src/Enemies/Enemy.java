package Enemies;

import Effects.HitEffect;

public interface Enemy {
    String getName();
    double getHP();
    double getArmorPoints();
    double getDamage();
    double getSpeed();
    HitEffect getEffect();
    void applyEffect(HitEffect effect);
    void setHP(double hp);
    void setSpeed(double speed);
    void setArmorPoints(double armorPoints);
    double getMaxSpeed();
}
