package de.talha.rentalapp.model;

import de.talha.rentalapp.abstraction.Storable;
import de.talha.rentalapp.abstraction.Displayable;
import de.talha.rentalapp.userinterface.Words;

public class Customer implements Storable, Displayable, Cloneable {
    private int id;
    private String name;
    private String email;
    private String street;
    private String city;
    private String zipcode;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String encode() {
        return "%d,%s,%s,%s,%s,%s,%s".formatted(id, name, email, street, city, zipcode, country);
    }

    @Override
    public String display() {
        return "%s(%s: %d, %s: %s, %s: %s, %s: %s, %s: %s, %s: %s, %s: %s)"
                .formatted(Words.CUSTOMER,
                        Words.ID, id,
                        Words.NAME, name,
                        Words.EMAIL, email,
                        Words.STREET, street,
                        Words.CITY, city,
                        Words.ZIPCODE, zipcode,
                        Words.COUNTRY, country);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
