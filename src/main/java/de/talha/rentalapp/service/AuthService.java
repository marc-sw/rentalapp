package de.talha.rentalapp.service;

import de.talha.rentalapp.exception.InvalidCredentialsException;

public class AuthService {

    private final String adminUsername;
    private final String adminPassword;
    private boolean adminAccess;

    public AuthService(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public void authorize(String username, String password) throws InvalidCredentialsException {
        if (!username.equals(adminUsername) || !password.equals(adminPassword)) {
            throw new InvalidCredentialsException();
        }
        adminAccess = true;
    }

    public void checkout() {
        adminAccess = false;
    }

    public boolean hasAdminAccess() {
        return adminAccess;
    }
}
