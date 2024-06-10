package de.talha.rentalapp.exception;

public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super("Aktion benötigt Administrator Rechte");
    }
}
