package de.talha.rentalapp.controller;

import de.talha.rentalapp.model.Statistics;
import de.talha.rentalapp.service.CustomerService;
import de.talha.rentalapp.service.ReportService;
import de.talha.rentalapp.service.VehicleService;
import de.talha.rentalapp.userinterface.Userinterface;

public class ReportController {

    private final ReportService reportService;
    private final CustomerService customerService;
    private final VehicleService vehicleService;
    private final Userinterface ui;

    public ReportController(ReportService reportService, CustomerService customerService, VehicleService vehicleService, Userinterface ui) {
        this.reportService = reportService;
        this.customerService = customerService;
        this.vehicleService = vehicleService;
        this.ui = ui;
    }

    public void displayStatistics() {
        Statistics statistics = reportService.getStatistics();
        ui.title("Report aktueller Informationen");
        ui.info("Anzahl vemieteter Fahrzeuge: " + statistics.rentedVehicles());
        ui.info("Anzahl normaler Autos: " + statistics.carCount());
        ui.info("Anzahl E-Autos: " + statistics.ecarCount());
        ui.info("Anzahl Motorräder: " + statistics.motorcycleCount());
        ui.info("Anzahl Fahrzeuge: " + statistics.vehicleCount());
        ui.info("Ausleihquote: " + statistics.rentquote() + "%");
        ui.info("Gesamtbestandwert: " + statistics.totalValue() + "€");
    }

    public void displayVehicles() {
        vehicleService.getAll().forEach(ui::display);
    }

    public void displayCustomers() {
        customerService.getAll().forEach(ui::display);
    }
}
