package de.talha.rentalapp.model;

import de.talha.rentalapp.abstraction.Displayable;
import de.talha.rentalapp.abstraction.SimpleDisplayable;
import de.talha.rentalapp.abstraction.Storable;
import de.talha.rentalapp.abstraction.Updatable;
import de.talha.rentalapp.userinterface.*;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public abstract class Rentable implements Storable, Displayable, SimpleDisplayable, Updatable, Cloneable {
    private int id;
    private String description;
    private boolean isRented;
    private double value;
    private Customer customer;

    public Rentable() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String encode() {
        int customerId = customer == null ? -1 : customer.getId();
        return "%d,%s,%b,%.2f,%d".formatted(id, description, isRented, value, customerId);
    }

    @Override
    public String display() {
        String customerString = customer == null ? "-": customer.display();
        String rented = isRented ? "Ja": "Nein";
        return "%s: %d, %s: %s, %s: %s, %s: %.2f€, %s: %s"
                .formatted(
                        Words.ID, id,
                        Words.DESCRIPTION, description,
                        Words.RENTED, rented,
                        Words.VALUE, value,
                        Words. CUSTOMER, customerString);
    }

    @Override
    public String displaySimple() {
        return "%s: %.2f€".formatted(Words.VALUE, value);
    }

    @Override
    public void update(PrimitiveProvider p) {
        setDescription(p.provideString(Words.DESCRIPTION, description));
        setValue(p.provideDouble(Words.VALUE, value));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
