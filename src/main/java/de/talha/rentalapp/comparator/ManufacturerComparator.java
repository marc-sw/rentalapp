package de.talha.rentalapp.comparator;

import de.talha.rentalapp.model.vehicle.Vehicle;

import java.util.Comparator;

public class ManufacturerComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.getManufacturer().compareTo(o2.getManufacturer());
    }
}
