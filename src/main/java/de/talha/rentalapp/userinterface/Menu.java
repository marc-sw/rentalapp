package de.talha.rentalapp.userinterface;

import de.talha.rentalapp.userinterface.provider.PrimitiveProvider;

import java.util.List;

public class Menu {

    public static final List<Option> START = OptionFactory.createStartOptions();
    public static final List<Option> ADMIN = OptionFactory.createAdminOptions();
    public static final List<Option> CUSTOMER = OptionFactory.createCustomerOptions();
    public static final List<Option> VEHICLE = OptionFactory.createVehicleOptions();
    public static final List<Option> RENTAL = OptionFactory.createRentalOptions();
    public static final List<Option> REPORT = OptionFactory.createReportOptions();

    private final Userinterface ui;
    private final PrimitiveProvider primitiveProvider;
    private List<Option> options;

    public Menu(Userinterface ui, PrimitiveProvider primitiveProvider, List<Option> options) {
        this.ui = ui;
        this.primitiveProvider = primitiveProvider;
        this.options = options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void display() {
        ui.info("---------------------------");
        int i = 1;
        for (Option option: options) {
            ui.info("%d - %s".formatted(i++, option.getTitle()));
        }
        ui.info("---------------------------");
    }

    public Option provideDecision() {
        int input = primitiveProvider.provideInt("Nummer der Aktion eingeben") - 1;
        while (input < 0 || input >= options.size()) {
            ui.error("Zahl von 1 bis %d erlaubt".formatted(options.size()));
            input = primitiveProvider.provideInt("Nummer der Aktion eingeben") - 1;
        }
        return options.get(input);
    }
}
