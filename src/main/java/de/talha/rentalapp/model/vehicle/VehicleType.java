package de.talha.rentalapp.model.vehicle;

import de.talha.rentalapp.userinterface.Words;

public enum VehicleType {
    CAR, ELECTRIC_CAR, MOTORCYCLE;

    public String toString() {
        return switch (this){
            case MOTORCYCLE -> Words.MOTORCYCLE;
            case CAR -> Words.CAR;
            case ELECTRIC_CAR -> Words.ELECTRIC_CAR;
        };
    }
}
