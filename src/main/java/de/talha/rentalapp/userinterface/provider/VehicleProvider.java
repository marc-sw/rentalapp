package de.talha.rentalapp.userinterface.provider;

import de.talha.rentalapp.model.vehicle.ElectricCar;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.model.vehicle.VehicleType;
import de.talha.rentalapp.model.vehicle.fuel.*;
import de.talha.rentalapp.userinterface.Words;

public class VehicleProvider {

    private final PrimitiveProvider pp;

    public VehicleProvider(PrimitiveProvider pp) {
        this.pp = pp;
    }

    private void createVehicleData(Vehicle vehicle) {
        vehicle.setDescription(pp.provideString(Words.DESCRIPTION));
        vehicle.setRented(false);
        vehicle.setValue(pp.provideDouble(Words.VALUE));
        vehicle.setCustomer(null);
        vehicle.setVin(pp.provideString(Words.VIN));
        vehicle.setLicensePlate(pp.provideString(Words.LICENSE_PLATE));
        vehicle.setMileage(pp.provideDouble(Words.MILEAGE));
        vehicle.setPower(pp.provideInt(Words.POWER));
        vehicle.setManufacturer(pp.provideString(Words.MANUFACTURER));
        vehicle.setModel(pp.provideString(Words.MODEL));
    }

    private void createFuelVehicleData(FuelVehicle fuelVehicle) {
        createVehicleData(fuelVehicle);
        fuelVehicle.setCountCylinders(pp.provideInt(Words.COUNT_CYLINDERS));
        fuelVehicle.setFuelType(pp.provideEnum(FuelType.BENZIN, Words.FUEL_TYPE));
    }

    private Car createCar() {
        Car car = new Car();
        createFuelVehicleData(car);
        return car;
    }

    private ElectricCar createElectricCar() {
        ElectricCar electricCar = new ElectricCar();
        createVehicleData(electricCar);
        electricCar.setBatteryCapacity(pp.provideDouble(Words.BATTERY_CAPACITY));
        electricCar.setChargingPower(pp.provideDouble(Words.CHARGING_POWER));
        return electricCar;
    }

    private Motorcycle createMotorcycle() {
        Motorcycle motorcycle = new Motorcycle();
        createFuelVehicleData(motorcycle);
        motorcycle.setBikeBoost(pp.provideDouble(Words.BIKE_BOOST));
        motorcycle.setBikeType(pp.provideEnum(BikeType.class, Words.BIKE_TYPE));
        return motorcycle;
    }

    public Vehicle create() {
        VehicleType type = pp.provideEnum(VehicleType.CAR, Words.VEHICLE_TYPE);
        return switch (type) {
            case CAR -> createCar();
            case ELECTRIC_CAR -> createElectricCar();
            case MOTORCYCLE -> createMotorcycle();
        };
    }

    public void update(Vehicle vehicle) {
        switch (vehicle) {
            case Car car -> car.update(pp);
            case Motorcycle motorcycle -> motorcycle.update(pp);
            case ElectricCar electricCar -> electricCar.update(pp);
            default -> throw new RuntimeException("vehicle child class unknown");
        }
    }
}
