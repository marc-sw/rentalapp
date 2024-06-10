package de.talha.rentalapp.userinterface;

import de.talha.rentalapp.RentalApp;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {

    public Menu createMenu() {
        Option help = new Option("Hilfe", "Beschreibung aller Optionen anzeigen", RentalApp::help);

        List<Option> admin = new ArrayList<>();
        List<Option> customer = new ArrayList<>();
        List<Option> rental  = new ArrayList<>();
        List<Option> report = new ArrayList<>();
        List<Option> vehicle = new ArrayList<>();

        List<Option> root = new ArrayList<>();
        root.add(new Option("Anwendung beenden", "Anwendung wird beendet", RentalApp::quit));
        root.add(new Option("Admin Menü", "Administrator Menü öffnen", app -> app.setMenuOptions(admin)));
        root.add(new Option("Kunden Menü", "Kunden Menü öffnen", app -> app.setMenuOptions(customer)));
        root.add(new Option("Ausleihe Menü", "Ausleihe Menü öffnen", app -> app.setMenuOptions(rental)));
        root.add(new Option("Fahrzeug Menü", "Fahrzeug Menü öffnen", app -> app.setMenuOptions(vehicle)));
        root.add(new Option("Report Menü", "Report Menü öffnen", app -> app.setMenuOptions(report)));
        root.add(help);

        Option back = new Option("Zurück", "Zurück zum Hauptmenü", app -> app.setMenuOptions(root));

        admin.add(back);
        admin.add(new Option("System zurücksetzen", "Alle Kunden- & Fahrzeuginformationen permanent löschen", app -> app.getAdminController().reset()));
        admin.add(new Option("Anmelden", "Anmelden um Administrator Funktionalitäten zu nutzen", app -> app.getAuthController().authorize()));
        admin.add(new Option("Abmelden", "Abmelden um Administrator Funktionalitäten zu sperren", app -> app.getAuthController().checkout()));
        admin.add(help);

        customer.add(back);
        customer.add(new Option("Hinzufügen", "Neuen Kunden hinzufügen", app -> app.getCustomerController().create()));
        customer.add(new Option("Bearbeiten", "Bestehenden Kunden bearbeiten", app -> app.getCustomerController().update()));
        customer.add(new Option("Entfernen", "Bestehenden Kunden entfernen, Fehler falls Kunde einen Wagen leiht", app -> app.getCustomerController().delete()));
        customer.add(new Option("Alle anzeigen", "Bstehende Kunden mit allen Informationen auflisten", app -> app.getCustomerController().displayAll()));
        customer.add(help);

        rental.add(back);
        rental.add(new Option("Ausleihe starten", "Fahrzeug wird als verliehen markiert", app -> app.getRentalController().rentVehicle()));
        rental.add(new Option("Ausleihe beenden", "Fahrzeug wird als verfügbar markiert", app -> app.getRentalController().returnVehicle()));
        rental.add(help);

        report.add(back);
        report.add(new Option("Statistiken anzeigen", "Wichtigesten Statistiken über das gesamte System anzeigen", app -> app.getReportController().displayStatistics()));
        report.add(new Option("Fahrzeuge detailliert anzeigen", "Alle Fahrzeuge mit allen Informationen anzeigen", app -> app.getReportController().displayVehicles()));
        report.add(new Option("Kunden detailliert anzeigen", "Alle Kunden mit allen Informationen anzeigen", app -> app.getReportController().displayCustomers()));
        report.add(help);

        vehicle.add(back);
        vehicle.add(new Option("Hinzufügen", "Neues Fahrzeug hinzufügen, Fehler falls Nummernschild nicht verfügbar ist", app -> app.getVehicleController().create()));
        vehicle.add(new Option("Bearbeiten", "Bestehendes Fahrzeug bearbeiten, Fehler falls geändertes Nummernschild nicht verfügbar ist", app -> app.getVehicleController().update()));
        vehicle.add(new Option("Entfernen", "Bestehendes Fahrzeug entfernen", app -> app.getVehicleController().delete()));
        vehicle.add(new Option("Alle anzeigen", "Fahrzeuge mit allen Informationen auflisten", app -> app.getVehicleController().displayAll()));
        vehicle.add(new Option("Suche Nummernschild", "System nach Fahrzeug mit gegebenem Nummernschild durchsuchen", app -> app.getVehicleController().displayByLicensePlate()));
        vehicle.add(new Option("Filtern nach Hersteller", "Fahrzeuge nach gegebenem Hersteller filtern und anzeigen", app -> app.getVehicleController().displayByManufacturer()));
        vehicle.add(new Option("Cruiser Motorräder anzeigen", "Motorräder nach der Art Cruiser filtern und anzeigen", app -> app.getVehicleController().displayCruiser()));
        vehicle.add(help);

        return new Menu(root);
    }
}
