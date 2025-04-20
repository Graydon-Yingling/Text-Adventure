package Effects;

public record HitEffect(String name, double hpDamage, double armorDamage, double speedChange, double effectChance) {

    public boolean isApplied() {
        return Math.random() <= this.effectChance;
    }

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

}
