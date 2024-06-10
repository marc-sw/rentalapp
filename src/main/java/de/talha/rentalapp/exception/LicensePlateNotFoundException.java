package de.talha.rentalapp.exception;

public class LicensePlateNotFoundException extends Exception{
    public LicensePlateNotFoundException() {
        super("Unbekanntes Nummernschild");
    }
}
