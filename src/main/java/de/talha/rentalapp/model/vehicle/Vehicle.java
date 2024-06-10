package de.talha.rentalapp.model.vehicle;

import de.talha.rentalapp.model.Rentable;
import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

public abstract class Vehicle extends Rentable {
    private String vin;
    private String licensePlate;
    private double mileage;
    private int power;
    private String manufacturer;
    private String model;

    public Vehicle() {}

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String encode() {
        return super.encode() + ",%s,%s,%.1f,%d,%s,%s".formatted(vin, licensePlate, mileage, power, manufacturer, model);
    }

    @Override
    public String display() {
        return  super.display() + ", %s: %s, %s: %s, %s: %.2fkm, %s: %dPS, %s: %s, %s: %s".formatted(
                        Words.VIN, vin,
                        Words.LICENSE_PLATE, licensePlate,
                        Words.MILEAGE, mileage,
                        Words.POWER, power,
                        Words.MANUFACTURER, manufacturer,
                        Words.MODEL, model);
    }

    @Override
    public String displaySimple() {
        return super.displaySimple() + ", %s: %.2fkm, %s: %dPS, %s: %s"
                .formatted(Words.MILEAGE, mileage, Words.POWER, power, Words.MANUFACTURER, manufacturer);
    }

    @Override
    public void update(PrimitiveProvider p) {
        super.update(p);
        setVin(p.provideString(Words.VIN, vin));
        setLicensePlate(p.provideString(Words.LICENSE_PLATE, licensePlate));
        setMileage(p.provideDouble(Words.MILEAGE, mileage));
        setPower(p.provideInt(Words.POWER, power));
        setManufacturer(p.provideString(Words.MANUFACTURER, manufacturer));
        setModel(p.provideString(Words.MODEL, manufacturer));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
