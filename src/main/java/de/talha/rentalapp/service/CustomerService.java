package de.talha.rentalapp.service;

import de.talha.rentalapp.exception.CustomerRentException;
import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.store.Store;

import java.util.List;

public class CustomerService {

    private final Store<Customer> customerStore;
    private final VehicleService vehicleService;

    public CustomerService(Store<Customer> customerStore, VehicleService vehicleService) {
        this.customerStore = customerStore;
        this.vehicleService = vehicleService;
    }

    public void create(Customer customer) {
        customerStore.create(customer);
    }

    public void update(Customer customer) throws EntityNotFound {
        customerStore.update(customer);
    }

    public void delete(int id) throws EntityNotFound, CustomerRentException {
        if (vehicleService.isRenting(id)) {
            throw new CustomerRentException();
        }
        customerStore.delete(id);
    }

    public List<Customer> getAll() {
        return customerStore.getAll();
    }

    public Customer getById(int id) throws EntityNotFound {
        return customerStore.getById(id);
    }
}
