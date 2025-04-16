package Weapons;
import Effects.*;

public record Weapon(String name, double damage, HitEffect effect) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Weapon other = (Weapon) obj;
        return name.equals(other.name) &&
                this.damage == other.damage &&
                this.effect.equals(other.effect);
    }

}