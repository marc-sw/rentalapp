package de.talha.rentalapp.userinterface.provider;

import de.talha.rentalapp.userinterface.Userinterface;

public class PrimitiveProvider {

    private final Userinterface ui;

    public PrimitiveProvider(Userinterface ui) {
        this.ui = ui;
    }

    public <T extends Enum<T>> T provideEnum(Class<T> cls) {
        int i = 1;
        StringBuilder message = new StringBuilder();
        for (T e: cls.getEnumConstants()) {
            if (i > 1) {
                message.append(" ");
            }
            message.append("(%d) %s".formatted(i, e));
            i++;
        }
        int count = cls.getEnumConstants().length;
        int index = provideInt(message.toString());
        while (index < 1 || index > count) {
            ui.info("Ungültige Eingabe, nur ganze Zahlen von 1 bis %d erlaubt".formatted(count));
            index = provideInt(message.toString());
        }
        return cls.getEnumConstants()[index-1];
    }

    public String provideString(String message) {
        ui.info(message + ":");
        return ui.provideNextLine();
    }

    public int provideInt(String message) {
        boolean correct = false;
        int response = 0;
        while (!correct) {
            try {
                response = Integer.parseInt(provideString(message));
                correct = true;
            } catch(NumberFormatException e) {
                ui.info("Ungültige Eingabe, nur ganze Zahlen erlaubt");
            }
        }
        return response;
    }

    public double provideDouble(String message) {
        boolean correct = false;
        double response = 0;
        while (!correct) {
            try {
                response = Double.parseDouble(provideString(message));
                correct = true;
            } catch(NumberFormatException e) {
                ui.info("Ungültige eingabe, nur Zahlen erlaubt");
            }
        }
        return response;
    }

    /*
    public boolean provideBoolean(String message) {
        boolean correct = false;
        boolean response = false;
        while (!correct) {
            try {
                int n = provideInt(message + "? (1) Ja (2) Nein");
                if (n == 1 || n == 2) {
                    response = n == 1;
                    correct = true;
                } else {
                    ui.info("Ungültige Eingabe, nur 1 oder 2 erlaubt");
                }
            } catch(NumberFormatException e) {
                ui.info("Ungültige Eingabe, nur 1 oder 2 erlaubt");
            }
        }
        return response;
    }*/
}
