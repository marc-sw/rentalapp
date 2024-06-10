package de.talha.rentalapp.service;

import de.talha.rentalapp.comparator.ManufacturerComparator;
import de.talha.rentalapp.comparator.MileageComparator;
import de.talha.rentalapp.comparator.PowerComparator;
import de.talha.rentalapp.comparator.ValueComparator;
import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.exception.LicensePlateNotFoundException;
import de.talha.rentalapp.exception.LicensePlateTakenException;
import de.talha.rentalapp.exception.VehicleRentedException;
import de.talha.rentalapp.model.VehicleSortType;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.model.vehicle.fuel.BikeType;
import de.talha.rentalapp.model.vehicle.fuel.Motorcycle;
import de.talha.rentalapp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class VehicleService {

    private final Store<Vehicle> vehicleStore;

    public VehicleService(Store<Vehicle> vehicleStore) {
        this.vehicleStore = vehicleStore;
    }

    public Vehicle getById(int id) throws EntityNotFound {
        return vehicleStore.getById(id);
    }

    public Vehicle getByLicensePlate(String licensePlate) throws LicensePlateNotFoundException {
        Optional<Vehicle> vehicle = vehicleStore.getAll().stream().filter(v -> v.getLicensePlate().equals(licensePlate)).findFirst();
        if (vehicle.isEmpty()) {
            throw new LicensePlateNotFoundException();
        }
        return vehicle.get();
    }

    public List<Vehicle> getByManufacturer(String manufacturer) {
        return vehicleStore.getAll().stream().filter(v -> v.getManufacturer().equals(manufacturer)).toList();
    }

    public List<Vehicle> getByBikeType(BikeType bikeType) {
        return vehicleStore.getAll().stream().filter(v -> v instanceof Motorcycle m && m.getBikeType().equals(bikeType)).toList();
    }

    public void create(Vehicle vehicle) throws LicensePlateTakenException {
        try {
            getByLicensePlate(vehicle.getLicensePlate());
            throw new LicensePlateTakenException();
        } catch (LicensePlateNotFoundException e) {
            vehicleStore.create(vehicle);
        }
    }

    public List<Vehicle> getAllSorted(VehicleSortType sortType) {
        Comparator<Vehicle> comparator = switch (sortType) {
            case FAHRZEUGWERT -> new ValueComparator();
            case HERSTELLER -> new ManufacturerComparator();
            case KILOMETERSTAND -> new MileageComparator();
            case LEISTUNG -> new PowerComparator();
        };
        List<Vehicle> vehicles = getAll();
        vehicles.sort(comparator);
        return vehicles;
    }

    public boolean isRenting(int customerId) {
        return vehicleStore.getAll().stream().anyMatch(v -> v.getCustomer().getId() == customerId);
    }

    public List<Vehicle> getAll() {
        return vehicleStore.getAll();
    }

    public void delete(int id) throws EntityNotFound, VehicleRentedException {
        Vehicle vehicle = vehicleStore.getById(id);
        if (vehicle.isRented()) {
            throw new VehicleRentedException();
        }
        vehicleStore.delete(id);
    }

    public void update(Vehicle vehicle) throws EntityNotFound {
        vehicleStore.update(vehicle);
    }
}
