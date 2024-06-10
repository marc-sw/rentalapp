package de.talha.rentalapp.userinterface;

import java.util.ArrayList;
import java.util.List;

public class OptionFactory {
    public static List<Option> createStartOptions() {
        List<Option> options = new ArrayList<>();
        options.add(Option.ADMIN_MENU);
        options.add(Option.CUSTOMER_MENU);
        options.add(Option.VEHICLE_MENU);
        options.add(Option.RENTAL_MENU);
        options.add(Option.REPORT_MENU);
        options.add(Option.QUIT);
        return options;
    }

    public static List<Option> createAdminOptions() {
        List<Option> options = new ArrayList<>();
        options.add(Option.ADMIN_RESET);
        options.add(Option.AUTH_AUTHORIZE);
        options.add(Option.AUTH_CHECKOUT);
        options.add(Option.BACK);
        return options;
    }

    public static List<Option> createCustomerOptions() {
        List<Option> options = new ArrayList<>();
        options.add(Option.CUSTOMER_CREATE);
        options.add(Option.CUSTOMER_LIST);
        options.add(Option.BACK);
        return options;
    }

    public static List<Option> createVehicleOptions() {
        List<Option> options = new ArrayList<>();
        options.add(Option.VEHICLE_CREATE);
        options.add(Option.VEHICLE_UPDATE);
        options.add(Option.VEHICLE_DELETE);
        options.add(Option.VEHICLE_LIST);
        options.add(Option.VEHICLE_LICENSE_PLATE);
        options.add(Option.VEHICLES_MANUFCATURER);
        options.add(Option.VEHICLE_CRUISER);
        options.add(Option.BACK);
        return options;
    }

    public static List<Option> createRentalOptions() {
        List<Option> options = new ArrayList<>();
        options.add(Option.RENTAL_RENT);
        options.add(Option.RENTAL_RETURN);
        options.add(Option.BACK);
        return options;
    }

    public static List<Option> createReportOptions() {
        List<Option> options = new ArrayList<>();
        options.add(Option.REPORT_STATISTICS);
        options.add(Option.REPORT_CUSTOMERS);
        options.add(Option.REPORT_VEHICLES);
        options.add(Option.BACK);
        return options;
    }
}
