package de.talha.rentalapp.userinterface;

import de.talha.rentalapp.RentalApp;
import de.talha.rentalapp.abstraction.Callable;

public enum Option implements Callable {
    CUSTOMER_CREATE("Kunde erstellen", app -> app.getCustomerController().create()),
    CUSTOMER_LIST("Kunden anzeigen", app -> app.getCustomerController().displayAll()),
    VEHICLE_CREATE("Fahrzeug erstellen", app -> app.getVehicleController().create()),
    VEHICLE_LIST("Fahrzeuge anzeigen", app -> app.getVehicleController().displayAll()),
    VEHICLE_UPDATE("Fahrzeug ändern", app -> app.getVehicleController().update()),
    VEHICLE_LICENSE_PLATE("Fahrzeug mit Nummernschild anzeigen", app -> app.getVehicleController().displayByLicensePlate()),
    VEHICLES_MANUFCATURER("Fahrzeuge nach Hersteller gefiltert anzeigen", app -> app.getVehicleController().displayByManufacturer()),
    VEHICLE_CRUISER("Motorräder der Art Cruiser anzeigen", app -> app.getVehicleController().displayCruiser()),
    VEHICLE_DELETE("Fahrzeug löschen", app -> app.getVehicleController().delete()),
    RENTAL_RENT("Ausleihe starten", app -> app.getRentalController().rentVehicle()),
    RENTAL_RETURN("Ausleihe beenden", app -> app.getRentalController().returnVehicle()),
    REPORT_STATISTICS("Statistik anzeigen", app -> app.getReportController().displayStatistics()),
    REPORT_VEHICLES("Fahrzeuge detailliert anzeigen", app -> app.getReportController().displayVehicles()),
    REPORT_CUSTOMERS("Kunden detailliert anzeigen", app -> app.getReportController().displayCustomers()),
    AUTH_AUTHORIZE("Anmelden", app -> app.getAuthController().authorize()),
    AUTH_CHECKOUT("Abmelden", app -> app.getAuthController().checkout()),
    ADMIN_RESET("System zurücksetzen", app -> app.getAdminController().reset()),
    QUIT("Anwendung beenden", RentalApp::quit),

    BACK("Zurück", app -> app.setMenueOptions(Menu.START)),
    ADMIN_MENU("Admin Menü", app -> app.setMenueOptions(Menu.ADMIN)),
    CUSTOMER_MENU("Kunden Menü", app -> app.setMenueOptions(Menu.CUSTOMER)),
    RENTAL_MENU("Ausleihe Menü", app -> app.setMenueOptions(Menu.RENTAL)),
    REPORT_MENU("Report Menü", app -> app.setMenueOptions(Menu.REPORT)),
    VEHICLE_MENU("Fahrzeug Menü", app -> app.setMenueOptions(Menu.VEHICLE))
    ;

    private final String title;
    private final Callable callable;

    Option(String title, Callable callable) {
        this.title = title;
        this.callable = callable;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void call(RentalApp app) {
        callable.call(app);
    }
}
