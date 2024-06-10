package de.talha.rentalapp.model.vehicle.fuel;


import de.talha.rentalapp.model.vehicle.VehicleType;
import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public class Car extends FuelVehicle {

    @Override
    public void update(PrimitiveProvider p) {
        super.update(p);
    }

    @Override
    public String encode() {
        return super.encode() + ",%s".formatted(VehicleType.CAR.name());
    }

    @Override
    public String display() {
        return "%s - %s".formatted(Words.CAR, super.display());
    }

    @Override
    public String displaySimple() {
        return "%s - %s".formatted(Words.CAR, super.displaySimple());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
