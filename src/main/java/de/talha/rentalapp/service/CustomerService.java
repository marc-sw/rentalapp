package de.talha.rentalapp.service;

import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.store.Store;

import java.util.List;

public class CustomerService {

    private final Store<Customer> customerStore;

    public CustomerService(Store<Customer> customerStore) {
        this.customerStore = customerStore;
    }

    public void create(Customer customer) {
        customerStore.create(customer);
    }

    public List<Customer> getAll() {
        return customerStore.getAll();
    }

    public Customer getById(int id) throws EntityNotFound {
        return customerStore.getById(id);
    }
}
