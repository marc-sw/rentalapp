package de.talha.rentalapp.exception;

public class CustomerRentException extends Exception {
    public CustomerRentException() {
        super("Kunde leiht momentan ein Fahrzeug");
    }
}
