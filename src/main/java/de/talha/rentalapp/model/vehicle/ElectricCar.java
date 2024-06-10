package de.talha.rentalapp.model.vehicle;

import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.FallbackProvider;

public class ElectricCar extends Vehicle {

    private double batteryCapacity;
    private double chargingPower;

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setChargingPower(double chargingPower) {
        this.chargingPower = chargingPower;
    }

    @Override
    public void update(FallbackProvider p) {
        super.update(p);
        setBatteryCapacity(p.condDouble(Words.BATTERY_CAPACITY, batteryCapacity));
        setChargingPower(p.condDouble(Words.CHARGING_POWER, chargingPower));
    }

    @Override
    public String encode() {
        return super.encode() + ",%.2f,%.2f,%s".formatted(batteryCapacity, chargingPower, VehicleType.ELECTRIC_CAR.name());
    }

    @Override
    public String displaySimple() {
        return "%s - %s".formatted(Words.ELECTRIC_CAR, super.displaySimple());
    }

    @Override
    public String display() {
        return "%s - %s, %s: %.2fkWh, %s: %.2fkW"
                .formatted(Words.ELECTRIC_CAR, super.display(),Words.BATTERY_CAPACITY, batteryCapacity, Words.CHARGING_POWER, chargingPower);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
