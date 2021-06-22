package at.htlkaindorf.dorjab18.util;

import java.util.Objects;

/**
 *
 * @author dorjab18
 */
public class PhysicsUnit {
    private double num;
    private String unit;

    public PhysicsUnit(PhysicsUnit physicsUnit) {
        this.num = physicsUnit.num;
        this.unit = physicsUnit.unit;
    }
    
    public PhysicsUnit(double num, String unit) {
        this.num = num;
        this.unit = unit;
    }
    
    public PhysicsUnit(double num) {
        this(num, "");
    }
    
    public PhysicsUnit(String unit) {
        this(1, unit);
    }
    
    public PhysicsUnit() {
        this(1);
    }

    public double getNumber() {
        return num;
    }

    public void setNumber(double num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public void set(PhysicsUnit physicsUnit) {
        this.num = physicsUnit.num;
        this.unit = physicsUnit.unit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.num) ^ (Double.doubleToLongBits(this.num) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.unit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhysicsUnit other = (PhysicsUnit) obj;
        if (Double.doubleToLongBits(this.num) != Double.doubleToLongBits(other.num)) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        return true;
    }
    
    public boolean isUnitLess() {
        return unit.isEmpty();
    }
}