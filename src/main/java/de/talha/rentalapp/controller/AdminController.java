package de.talha.rentalapp.controller;

import de.talha.rentalapp.exception.UnauthorizedException;
import de.talha.rentalapp.service.AdminService;
import de.talha.rentalapp.userinterface.Userinterface;

public class AdminController {

    private final Userinterface ui;
    private final AdminService adminService;

    public AdminController(Userinterface ui, AdminService adminService) {
        this.ui = ui;
        this.adminService = adminService;
    }

    public void reset() {
        try {
            adminService.reset();
            ui.info("System wurde zurückgesetz");
        } catch (UnauthorizedException e) {
            ui.error(e.getMessage());
        }
    }
}
