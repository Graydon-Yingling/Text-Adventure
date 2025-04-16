package Healing;

public record Healing(String name, double healthGained, int reserveHp, int tempArmor) {

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

}