package de.talha.rentalapp.exception;

public class VehicleNotRentedException extends Exception {
    public VehicleNotRentedException() {
        super("Fahrzeug ist nicht verliehen");
    }
}
