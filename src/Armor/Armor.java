package Armor;

public record Armor(String name, int armorPoints) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Armor other = (Armor) obj;
        return name.equals(other.name) && armorPoints == other.armorPoints;
    }

}