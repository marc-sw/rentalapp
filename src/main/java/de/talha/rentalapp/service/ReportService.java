package de.talha.rentalapp.service;

import de.talha.rentalapp.model.Rentable;
import de.talha.rentalapp.model.Statistics;
import de.talha.rentalapp.model.vehicle.ElectricCar;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.model.vehicle.fuel.Car;
import de.talha.rentalapp.model.vehicle.fuel.Motorcycle;

import java.util.List;

public class ReportService {

    private final VehicleService vehicleService;

    public ReportService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Statistics getStatistics() {
        List<Vehicle> vehicles = vehicleService.getAll();
        int rentedCount = (int) vehicles.stream().filter(Rentable::isRented).count();
        int vehicleCount = vehicles.size();
        return new Statistics(
                rentedCount,
                (int) vehicles.stream().filter(e -> e instanceof Car).count(),
                (int) vehicles.stream().filter(e -> e instanceof ElectricCar).count(),
                (int) vehicles.stream().filter(e -> e instanceof Motorcycle).count(),
                vehicleCount,
                rentedCount / (double) vehicleCount,
                vehicles.stream().mapToDouble(Rentable::getValue).sum()
        );
    }
}
