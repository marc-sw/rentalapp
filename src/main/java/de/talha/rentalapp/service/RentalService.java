package de.talha.rentalapp.service;

import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.exception.VehicleNotRentedException;
import de.talha.rentalapp.exception.VehicleRentedException;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.model.vehicle.Vehicle;

public class RentalService {

    private final VehicleService vehicleService;

    public RentalService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void rentVehicle(Customer customer, int vehicleId) throws EntityNotFound, VehicleRentedException {
        Vehicle vehicle = vehicleService.getById(vehicleId);
        if (vehicle.isRented()) {
            throw new VehicleRentedException();
        }
        vehicle.setRented(true);
        vehicle.setCustomer(customer);
        vehicleService.update(vehicle);
    }

    public void returnVehicle(int vehicleId) throws EntityNotFound, VehicleNotRentedException {
        Vehicle vehicle = vehicleService.getById(vehicleId);
        if (!vehicle.isRented()) {
            throw new VehicleNotRentedException();
        }
        vehicle.setRented(false);
        vehicle.setCustomer(null);
        vehicleService.update(vehicle);
    }
}
