package de.talha.rentalapp.model.vehicle.fuel;

import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public abstract class FuelVehicle extends Vehicle {

    private int countCylinders;
    private FuelType fuelType;

    public void setCountCylinders(int countCylinders) {
        this.countCylinders = countCylinders;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public void update(PrimitiveProvider p) {
        super.update(p);
        setCountCylinders(p.provideInt(Words.COUNT_CYLINDERS, countCylinders));
        setFuelType(p.provideEnum(fuelType, Words.FUEL_TYPE));
    }

    @Override
    public String encode() {
        return super.encode() + ",%d,%s".formatted(countCylinders, fuelType.name());
    }

    @Override
    public String displaySimple() {
        return super.displaySimple() + ", %s: %s".formatted(Words.FUEL_TYPE, fuelType.toString());
    }

    @Override
    public String display() {
        return super.display() + ", %s: %d, %s: %s"
                .formatted(Words.COUNT_CYLINDERS, countCylinders, Words.FUEL_TYPE, fuelType.toString());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
