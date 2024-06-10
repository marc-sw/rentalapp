package de.talha.rentalapp.service;

import de.talha.rentalapp.exception.AlreadyAuthorizedException;
import de.talha.rentalapp.exception.InvalidCredentialsException;
import de.talha.rentalapp.exception.UnauthorizedException;

public class AuthService {

    private final String adminUsername;
    private final String adminPassword;
    private boolean adminAccess;

    public AuthService(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public void authorize(String username, String password) throws InvalidCredentialsException, AlreadyAuthorizedException {
        if (hasAdminAccess()) {
            throw new AlreadyAuthorizedException();
        }
        if (!username.equals(adminUsername) || !password.equals(adminPassword)) {
            throw new InvalidCredentialsException();
        }
        adminAccess = true;
    }

    public void checkout() throws UnauthorizedException {
        if (!hasAdminAccess()) {
            throw new UnauthorizedException();
        }
        adminAccess = false;
    }

    public boolean hasAdminAccess() {
        return adminAccess;
    }
}
