package de.talha.rentalapp.controller;

import de.talha.rentalapp.exception.InvalidCredentialsException;
import de.talha.rentalapp.service.AuthService;
import de.talha.rentalapp.userinterface.Userinterface;
import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public class AuthController {

    private final PrimitiveProvider primitiveProvider;
    private final AuthService authService;
    private final Userinterface ui;

    public AuthController(PrimitiveProvider primitiveProvider, AuthService authService, Userinterface ui) {
        this.primitiveProvider = primitiveProvider;
        this.authService = authService;
        this.ui = ui;
    }

    public void authorize() {
        String username = primitiveProvider.provideString(Words.USERNAME);
        String password = primitiveProvider.provideString(Words.PASSWORD);
        try {
            authService.authorize(username, password);
            ui.info("Admin Rechte hinzugefügt");
        } catch (InvalidCredentialsException e) {
            ui.error(e.getMessage());
        }
    }

    public void checkout() {
        authService.checkout();
        ui.info("Admin Rechte entzogen");
    }
}
