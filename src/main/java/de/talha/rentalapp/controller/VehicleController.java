package de.talha.rentalapp.controller;

import de.talha.rentalapp.exception.EntityNotFound;
import de.talha.rentalapp.exception.LicensePlateNotFoundException;
import de.talha.rentalapp.exception.LicensePlateTakenException;
import de.talha.rentalapp.exception.VehicleRentedException;
import de.talha.rentalapp.model.VehicleSortType;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.model.vehicle.fuel.BikeType;
import de.talha.rentalapp.service.VehicleService;
import de.talha.rentalapp.userinterface.Userinterface;
import de.talha.rentalapp.userinterface.Words;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;
import de.talha.rentalapp.userinterface.provider.VehicleProvider;

public class VehicleController {

    private final PrimitiveProvider primitiveProvider;
    private final VehicleProvider vehicleProvider;
    private final VehicleService vehicleService;
    private final Userinterface ui;

    public VehicleController(PrimitiveProvider primitiveProvider, VehicleProvider vehicleProvider, VehicleService vehicleService, Userinterface ui) {
        this.primitiveProvider = primitiveProvider;
        this.vehicleProvider = vehicleProvider;
        this.vehicleService = vehicleService;
        this.ui = ui;
    }

    public void create() {
        Vehicle vehicle = vehicleProvider.create();
        try {
            vehicleService.create(vehicle);
            ui.info("Fahrzeug wurde erstellt");
        } catch (LicensePlateTakenException e) {
            ui.error(e.getMessage());
        }
    }

    public void update() {
        int id = primitiveProvider.provideInt(Words.ID);
        try {
            Vehicle vehicle = (Vehicle) vehicleService.getById(id).clone();
            vehicleProvider.update(vehicle);
            vehicleService.update(vehicle);
        } catch (EntityNotFound e) {
            ui.error(e.getMessage());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        int id = primitiveProvider.provideInt(Words.ID);
        try {
            vehicleService.delete(id);
            ui.info("Fahrzeug wurde gelöscht");
        } catch (EntityNotFound | VehicleRentedException e) {
            ui.error(e.getMessage());
        }
    }

    public void displayAll() {
        VehicleSortType sortType = primitiveProvider.provideEnum(VehicleSortType.class);
        vehicleService.getAllSorted(sortType).forEach(ui::display);
    }

    public void displayByLicensePlate() {
        String licensePlate = primitiveProvider.provideString(Words.LICENSE_PLATE);
        try {
            Vehicle vehicle = vehicleService.getByLicensePlate(licensePlate);
            ui.info(vehicle.display());
        } catch (LicensePlateNotFoundException e) {
            ui.error(e.getMessage());
        }
    }

    public void displayByManufacturer() {
        String manufacturer = primitiveProvider.provideString(Words.MANUFACTURER);
        vehicleService.getByManufacturer(manufacturer).forEach(v -> ui.info(v.displaySimple()));
    }

    public void displayCruiser() {
        vehicleService.getByBikeType(BikeType.CRUISER).forEach(v -> ui.info(v.displaySimple()));
    }
}
