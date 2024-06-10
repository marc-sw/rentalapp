package de.talha.rentalapp.comparator;

import de.talha.rentalapp.model.vehicle.Vehicle;

import java.util.Comparator;

public class PowerComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return Integer.compare(o1.getPower(), o2.getPower());
    }
}
