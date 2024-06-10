package de.talha.rentalapp.userinterface.provider;

import de.talha.rentalapp.model.vehicle.ElectricCar;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.model.vehicle.VehicleType;
import de.talha.rentalapp.model.vehicle.fuel.*;
import de.talha.rentalapp.userinterface.Words;

public class VehicleProvider {

    private final PrimitiveProvider pp;
    private final FallbackProvider fp;

    public VehicleProvider(PrimitiveProvider pp, FallbackProvider fp) {
        this.pp = pp;
        this.fp = fp;
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
        fuelVehicle.setFuelType(pp.provideEnum(FuelType.class));
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
        motorcycle.setBikeType(pp.provideEnum(BikeType.class));
        return motorcycle;
    }

    public Vehicle create() {
        VehicleType type = pp.provideEnum(VehicleType.class);
        return switch (type) {
            case CAR -> createCar();
            case ELECTRIC_CAR -> createElectricCar();
            case MOTORCYCLE -> createMotorcycle();
        };
    }

    public void update(Vehicle vehicle) {
        if (vehicle instanceof Car car) {
            car.update(fp);
        } else if (vehicle instanceof Motorcycle motorcycle) {
            motorcycle.update(fp);
        } else if (vehicle instanceof ElectricCar electricCar){
            electricCar.update(fp);
        } else {
            throw new RuntimeException("vehicle child class unknown");
        }
    }
}
