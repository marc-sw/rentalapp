package de.talha.rentalapp.userinterface.provider;

import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.userinterface.Words;

public class CustomerProvider {

    private final PrimitiveProvider pp;

    public CustomerProvider(PrimitiveProvider pp) {
        this.pp = pp;
    }

    public Customer create() {
        Customer customer = new Customer();
        customer.setName(pp.provideString(Words.NAME));
        customer.setEmail(pp.provideString(Words.EMAIL));
        customer.setStreet(pp.provideString(Words.STREET));
        customer.setCity(pp.provideString(Words.CITY));
        customer.setZipcode(pp.provideString(Words.ZIPCODE));
        customer.setCountry(pp.provideString(Words.COUNTRY, "Deutschland"));
        return customer;
    }
}
