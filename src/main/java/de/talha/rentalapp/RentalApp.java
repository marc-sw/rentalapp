package de.talha.rentalapp;

import de.talha.rentalapp.controller.*;
import de.talha.rentalapp.service.*;
import de.talha.rentalapp.store.decoder.CustomerDecoder;
import de.talha.rentalapp.store.decoder.VehicleDecoder;
import de.talha.rentalapp.model.Customer;
import de.talha.rentalapp.model.vehicle.Vehicle;
import de.talha.rentalapp.store.FileAccess;
import de.talha.rentalapp.store.Store;
import de.talha.rentalapp.userinterface.Menu;
import de.talha.rentalapp.userinterface.MenuFactory;
import de.talha.rentalapp.userinterface.Option;
import de.talha.rentalapp.userinterface.Userinterface;
import de.talha.rentalapp.userinterface.provider.CustomerProvider;
import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;
import de.talha.rentalapp.userinterface.provider.VehicleProvider;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class RentalApp {


    private final Menu menu;

    private final Userinterface ui;
    private final PrimitiveProvider primitiveProvider;

    private final AdminController adminController;
    private final AuthController authController;
    private final CustomerController customerController;
    private final RentalController rentalController;
    private final ReportController reportController;
    private final VehicleController vehicleController;
    private boolean running;

    public RentalApp() {
        String path = System.getProperty("user.home");
        File vehiclesFile = Paths.get(path, "vehicles", "vehicles.txt").toFile();
        File customersFile = Paths.get(path, "customers", "customers.txt").toFile();

        Store<Customer> customerStore = new Store<>(new FileAccess(customersFile), new CustomerDecoder());
        Store<Vehicle> vehicleStore = new Store<>(new FileAccess(vehiclesFile), new VehicleDecoder(customerStore));

        ui = new Userinterface();
        primitiveProvider = new PrimitiveProvider(ui);
        AuthService authService = new AuthService("admin", "secret");

        PrimitiveProvider pp = new PrimitiveProvider(ui);
        VehicleService vehicleService = new VehicleService(vehicleStore);
        CustomerService customerService = new CustomerService(customerStore, vehicleService);
        RentalService rentalService = new RentalService(vehicleService);
        ReportService reportService = new ReportService(vehicleService);
        AdminService adminService = new AdminService(authService, customerStore, vehicleStore);

        authController = new AuthController(pp, authService, ui);
        customerController = new CustomerController(ui, customerService, new CustomerProvider(pp), pp);
        rentalController = new RentalController(pp, rentalService, customerService, ui);
        reportController = new ReportController(reportService, customerService,vehicleService, ui);
        vehicleController = new VehicleController(pp, new VehicleProvider(pp), vehicleService, ui);
        adminController = new AdminController(ui, adminService);
        menu = new MenuFactory().createMenu();
        running = false;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public AuthController getAuthController() {
        return authController;
    }

    public CustomerController getCustomerController() {
        return customerController;
    }

    public RentalController getRentalController() {
        return rentalController;
    }

    public ReportController getReportController() {
        return reportController;
    }

    public VehicleController getVehicleController() {
        return vehicleController;
    }

    public void setMenuOptions(List<Option> options) {
        menu.setOptions(options);
    }

    public void quit() {
        running = false;
        ui.info("Rentalapp wird beendet");
    }

    public void help() {
        ui.display(menu);
    }

    public void run() {
        running = true;
        while (running) {
            ui.displaySimple(menu);
            Option option = menu.getOptions().get(primitiveProvider.provideInt("Option wählen", 1, menu.getOptions().size()) - 1);
            ui.info(option.getName());
            option.call(this);
        }
    }
}
