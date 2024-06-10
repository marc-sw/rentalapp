package de.talha.rentalapp.comparator;

import de.talha.rentalapp.model.vehicle.Vehicle;

import java.util.Comparator;

public class MileageComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return Double.compare(o1.getMileage(), o2.getMileage());
    }
}
