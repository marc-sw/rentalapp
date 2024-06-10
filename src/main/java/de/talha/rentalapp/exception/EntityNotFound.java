package de.talha.rentalapp.exception;

public class EntityNotFound extends Exception {

    public EntityNotFound() {
        super("Unbekannte Id");
    }
}
