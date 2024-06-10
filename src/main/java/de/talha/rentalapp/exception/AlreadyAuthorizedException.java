package de.talha.rentalapp.exception;

public class AlreadyAuthorizedException extends Exception {
    public AlreadyAuthorizedException() {
        super("already authorized");
    }
}
