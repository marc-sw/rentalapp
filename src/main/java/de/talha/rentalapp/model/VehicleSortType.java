package de.talha.rentalapp.model;

public enum VehicleSortType {
    FAHRZEUGWERT, HERSTELLER, KILOMETERSTAND, LEISTUNG;

    public String toString() {
        return switch (this) {
            case FAHRZEUGWERT -> "Fahrzeugwert";
            case HERSTELLER -> "Hersteller";
            case KILOMETERSTAND -> "Kilometerstand";
            case LEISTUNG -> "Leistung";
        };
    }
}
