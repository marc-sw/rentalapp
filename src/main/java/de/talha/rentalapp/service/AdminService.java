package de.talha.rentalapp.service;

import de.talha.rentalapp.exception.UnauthorizedException;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.store.Store;

public class AdminService {

    private final AuthService authService;
    private final Store<Customer> customerStore;
    private final Store<Vehicle> vehicleStore;

    public AdminService(AuthService authService, Store<Customer> customerStore, Store<Vehicle> vehicleStore) {
        this.authService = authService;
        this.customerStore = customerStore;
        this.vehicleStore = vehicleStore;
    }

    public void reset() throws UnauthorizedException {
        if (!authService.hasAdminAccess()) {
            throw new UnauthorizedException();
        }
        customerStore.deleteAll();
        vehicleStore.deleteAll();
    }
}
