package Weapons;
import Effects.*;

import java.util.Objects;

public class Weapon {
    private final String name;
    private final double damage;
    private final HitEffect effect;

    public Weapon(String name, double damage, HitEffect effect) {
        this.name = name;
        this.damage = damage;
        this.effect = effect;
    }

    public String getName() {return name;}

    public double getDamage() {return damage;}

    public HitEffect getEffect() {return effect;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Weapon other = (Weapon) obj;
        return name.equals(other.name) &&
                this.damage == other.damage &&
                this.effect.equals(other.effect) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, damage, effect);
    }
}