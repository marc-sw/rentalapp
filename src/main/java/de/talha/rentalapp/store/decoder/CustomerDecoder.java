package de.talha.rentalapp.store.decoder;

import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.abstraction.Decoder;
import de.talha.rentalapp.userinterface.Words;

public class CustomerDecoder implements Decoder<Customer> {

    public Customer decode(String data) {
        String[] attributes = data.split(",");
        if (attributes.length != 7) {
            System.out.println(attributes.length);
            throw new IllegalArgumentException("Fehlerhafte %s Informationen".formatted(Words.CUSTOMER));
        }
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(attributes[0]));
        customer.setName(attributes[1]);
        customer.setEmail(attributes[2]);
        customer.setStreet(attributes[3]);
        customer.setCity(attributes[4]);
        customer.setZipcode(attributes[5]);
        customer.setCountry(attributes[6]);
        return customer;
    }
}
