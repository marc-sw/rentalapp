package de.talha.rentalapp.store.decoder;

import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.model.vehicle.ElectricCar;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.model.vehicle.VehicleType;
import de.talha.rentalapp.model.vehicle.fuel.*;
import de.talha.rentalapp.abstraction.Decoder;
import de.talha.rentalapp.store.Store;

public class VehicleDecoder implements Decoder<Vehicle> {

    private final Store<Customer> customerStore;

    public VehicleDecoder(Store<Customer> customerStore) {
        this.customerStore = customerStore;
    }

    private void decodeVehicleData(Vehicle vehicle, String[] attributes) {
        vehicle.setId(Integer.parseInt(attributes[0]));
        vehicle.setDescription(attributes[1]);
        vehicle.setRented(Boolean.parseBoolean(attributes[2]));
        vehicle.setValue(Double.parseDouble(attributes[3]));
        int customerId = Integer.parseInt(attributes[4]);
        Customer customer;
        try {
            customer = customerStore.getById(customerId);

        } catch (EntityNotFound e) {
            customer = null;
        }
        vehicle.setCustomer(customer);
        vehicle.setVin(attributes[5]);
        vehicle.setLicensePlate(attributes[6]);
        vehicle.setMileage(Double.parseDouble(attributes[7]));
        vehicle.setPower(Integer.parseInt(attributes[8]));
        vehicle.setManufacturer(attributes[9]);
        vehicle.setModel(attributes[10]);
    }

    private void decodeFuelVehicleData(FuelVehicle vehicle, String[] attributes) {
        decodeVehicleData(vehicle, attributes);
        vehicle.setCountCylinders(Integer.parseInt(attributes[11]));
        vehicle.setFuelType(FuelType.valueOf(attributes[12]));
    }

    private Car decodeCar(String[] attributes) {
        Car car = new Car();
        decodeFuelVehicleData(car, attributes);
        return car;
    }

    private ElectricCar decodeElectricCar(String[] attributes) {
        ElectricCar electricCar = new ElectricCar();
        decodeVehicleData(electricCar, attributes);
        electricCar.setBatteryCapacity(Double.parseDouble(attributes[11]));
        electricCar.setChargingPower(Double.parseDouble(attributes[12]));
        return electricCar;
    }

    private Motorcycle decodeMotorcycle(String[] attributes) {
        Motorcycle motorcycle = new Motorcycle();
        decodeFuelVehicleData(motorcycle, attributes);
        motorcycle.setBikeType(BikeType.valueOf(attributes[13]));
        motorcycle.setBikeBoost(Double.parseDouble(attributes[14]));
        return motorcycle;
    }

    @Override
    public Vehicle decode(String data) {
        String[] attributes = data.split(",");
        VehicleType type = VehicleType.valueOf(attributes[attributes.length - 1]);
        return switch (type) {
            case CAR -> decodeCar(attributes);
            case ELECTRIC_CAR -> decodeElectricCar(attributes);
            case MOTORCYCLE -> decodeMotorcycle(attributes);
        };
    }
}
