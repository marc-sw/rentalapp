package de.talha.rentalapp.controller;

import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.service.CustomerService;
import de.talha.rentalapp.userinterface.Userinterface;
import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.CustomerProvider;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public class CustomerController {

    private final Userinterface ui;
    private final CustomerService customerService;
    private final CustomerProvider customerProvider;
    private final PrimitiveProvider primitiveProvider;

    public CustomerController(Userinterface ui, CustomerService customerService, CustomerProvider customerProvider, PrimitiveProvider primitiveProvider) {
        this.ui = ui;
        this.customerService = customerService;
        this.customerProvider = customerProvider;
        this.primitiveProvider = primitiveProvider;
    }

    public void create() {
        Customer customer = customerProvider.create();
        customerService.create(customer);
        ui.info("Kunde wurde erstellt");
    }

    public void update() {
        int id = primitiveProvider.provideInt(Words.ID);
        try {
            Customer customer = (Customer) customerService.getById(id).clone();
            customer.update(primitiveProvider);
            customerService.update(customer);
            ui.info("Kunde wurde bearbeitet");
        } catch (EntityNotFound e) {
            ui.error(e.getMessage());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayAll() {
        customerService.getAll().forEach(ui::display);
    }
}
