package de.talha.rentalapp.userinterface;

import de.talha.rentalapp.RentalApp;
import de.talha.rentalapp.abstraction.Callable;

public class Option implements Callable {

    private final String name;
    private final String description;
    private final Callable callable;

    public Option(String name, String description, Callable callable) {
        this.name = name;
        this.description = description;
        this.callable = callable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void call(RentalApp app) {
        callable.call(app);
    }
}
