package Effects;

import java.util.Objects;

public class HitEffect {
    private final String name;
    private final double hpDamage;
    private final double armorDamage;
    private final double speedChange;

    public HitEffect(String name, double hpDamage, double armorDamage, double speedChange) {
        this.name = name;
        this.hpDamage = hpDamage;
        this.armorDamage = armorDamage;
        this.speedChange = speedChange;
    }

    public double getArmorDamage() {return armorDamage;}

    public double getHpDamage() {return hpDamage;}

    public double getSpeedChange() {return speedChange;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HitEffect other = (HitEffect) obj;
        return name.equals(other.name) &&
                Double.compare(this.hpDamage, other.hpDamage) == 0 &&
                Double.compare(this.armorDamage, other.armorDamage) == 0 &&
                Double.compare(this.speedChange, other.speedChange) == 0;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hpDamage, armorDamage, speedChange);
    }
}
