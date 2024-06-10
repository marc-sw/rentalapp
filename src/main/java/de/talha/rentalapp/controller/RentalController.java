package de.talha.rentalapp.controller;

import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.exception.VehicleNotRentedException;
import de.talha.rentalapp.exception.VehicleRentedException;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.service.CustomerService;
import de.talha.rentalapp.service.RentalService;
import de.talha.rentalapp.userinterface.Userinterface;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public class RentalController {

    private final PrimitiveProvider primitiveProvider;
    private final RentalService rentalService;
    private final CustomerService customerService;
    private final Userinterface ui;

    public RentalController(PrimitiveProvider primitiveProvider, RentalService rentalService, CustomerService customerService, Userinterface ui) {
        this.primitiveProvider = primitiveProvider;
        this.rentalService = rentalService;
        this.customerService = customerService;
        this.ui = ui;
    }

    public void rentVehicle() {
        int customerId = primitiveProvider.provideInt("Kunden Id");
        Customer customer;
        try {
            customer = customerService.getById(customerId);
        } catch (EntityNotFound e) {
            ui.error(e.getMessage());
            return;
        }
        int vehicleId = primitiveProvider.provideInt("Fahrzeug Id");
        try {
            rentalService.rentVehicle(customer, vehicleId);
            ui.info("Ausleihe erfolgreich");
        } catch (EntityNotFound | VehicleRentedException e) {
            ui.error(e.getMessage());
        }
    }

    public void returnVehicle() {
        int vehicleId = primitiveProvider.provideInt("Fahrzeug Id");
        try {
            rentalService.returnVehicle(vehicleId);
            ui.info("Rückgabe erfolgreich");
        } catch (EntityNotFound | VehicleNotRentedException e) {
            ui.error(e.getMessage());
        }
    }
}
