package de.talha.rentalapp.comparator;

import de.talha.rentalapp.model.vehicle.Vehicle;

import java.util.Comparator;

public class ValueComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return -Double.compare(o1.getValue(), o2.getValue());
    }
}
