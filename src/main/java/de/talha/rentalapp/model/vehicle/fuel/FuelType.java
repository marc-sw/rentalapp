package de.talha.rentalapp.model.vehicle.fuel;

public enum FuelType {
    BENZIN, DIESEL, GAS;

    public String toString() {
        return switch (this) {
            case GAS -> "Gas";
            case DIESEL -> "Diesel";
            case BENZIN -> "Benzin";
        };
    }
}
