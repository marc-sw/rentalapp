package de.talha.rentalapp.controller;

import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.service.CustomerService;
import de.talha.rentalapp.userinterface.Userinterface;
import de.talha.rentalapp.userinterface.provider.CustomerProvider;

public class CustomerController {

    private final Userinterface ui;
    private final CustomerService customerService;
    private final CustomerProvider customerProvider;

    public CustomerController(Userinterface ui, CustomerService customerService, CustomerProvider customerProvider) {
        this.ui = ui;
        this.customerService = customerService;
        this.customerProvider = customerProvider;
    }

    public void create() {
        Customer customer = customerProvider.create();
        customerService.create(customer);
        ui.info("Kunde wurde erstellt");
    }

    public void displayAll() {
        customerService.getAll().forEach(ui::display);
    }
}
