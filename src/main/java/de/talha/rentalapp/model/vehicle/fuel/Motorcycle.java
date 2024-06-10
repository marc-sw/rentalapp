package de.talha.rentalapp.model.vehicle.fuel;

import de.talha.rentalapp.model.vehicle.VehicleType;
import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public class Motorcycle extends FuelVehicle {
    private BikeType bikeType;
    private double bikeBoost;

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }

    public void setBikeBoost(double bikeBoost) {
        this.bikeBoost = bikeBoost;
    }

    @Override
    public void update(PrimitiveProvider p) {
        super.update(p);
        setBikeType(p.provideEnum(bikeType, Words.BIKE_TYPE));
        setBikeBoost(p.provideDouble(Words.BIKE_BOOST, bikeBoost));
    }

    @Override
    public String encode() {
        return super.encode() + ",%s,%.1f,%s".formatted(bikeType.name(), bikeBoost, VehicleType.MOTORCYCLE.name());
    }

    @Override
    public String display() {
        return "%s - %s, %s: %s, %s: %.2f"
                .formatted(Words.MOTORCYCLE, super.display(), Words.BIKE_TYPE, bikeType,Words.BIKE_BOOST, bikeBoost);
    }

    @Override
    public String displaySimple() {
        return "%s - %s, %s: %s".formatted(Words.MOTORCYCLE, super.displaySimple(), Words.BIKE_TYPE, bikeType.toString());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

