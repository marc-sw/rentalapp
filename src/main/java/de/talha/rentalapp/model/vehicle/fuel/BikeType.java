package de.talha.rentalapp.model.vehicle.fuel;

public enum BikeType {
    CRUISER, ENDURO, TOURER, SPORT;

    public String toString() {
        return switch (this) {
            case SPORT -> "Sport";
            case TOURER -> "Tourer";
            case ENDURO -> "Enduro";
            case CRUISER -> "Cruiser";
        };
    }
}
