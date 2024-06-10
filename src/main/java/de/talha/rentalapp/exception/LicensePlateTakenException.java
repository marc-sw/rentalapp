package de.talha.rentalapp.exception;

public class LicensePlateTakenException extends Exception {

    public LicensePlateTakenException() {
        super("Nummernschild ist nicht verfügbar");
    }
}
