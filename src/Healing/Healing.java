package Healing;

import Effects.HitEffect;

import java.util.Objects;

public class Healing {
    private final String name;
    private final double healthGained;
    private final int reserveHp;
    private final int tempArmor;

    public Healing(String name, double healthGained, int reserveHp, int tempArmor) {
        this.name = name;
        this.healthGained = healthGained;
        this.reserveHp = reserveHp;
        this.tempArmor = tempArmor;
    }

    public String getName() {return name;}

    public double getHealthGained() {return healthGained;}

    public int getReserveHp() {return reserveHp;}

    public int getTempArmor() {return tempArmor;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Healing other = (Healing) obj;
        return this.name.equals(other.name) &&
                Double.compare(this.healthGained, other.healthGained) == 0 &&
                this.reserveHp == other.reserveHp &&
                this.tempArmor == other.tempArmor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, healthGained, reserveHp, tempArmor);
    }
}