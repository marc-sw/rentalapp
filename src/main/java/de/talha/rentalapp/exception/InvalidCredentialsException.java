package de.talha.rentalapp.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("Ungültige Benutzer Daten");
    }
}
