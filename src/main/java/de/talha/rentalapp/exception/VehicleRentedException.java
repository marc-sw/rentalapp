package de.talha.rentalapp.exception;

public class VehicleRentedException extends Exception {
    public VehicleRentedException() {
        super("Fahrzeug ist verliehen");
    }
}
