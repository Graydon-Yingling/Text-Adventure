package Armor;

import java.util.Objects;

public class Armor {
    private final String name;
    private final int armorPoints;

    public Armor(String name, int armorPoints) {
        this.name = name;
        this.armorPoints = armorPoints;
    }

    public String getName() {return name;}

    public int getArmorPoints() {return armorPoints;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Armor other = (Armor) obj;
        return name.equals(other.name) && armorPoints == other.armorPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, armorPoints);
    }
}