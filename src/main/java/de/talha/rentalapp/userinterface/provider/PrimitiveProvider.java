package de.talha.rentalapp.userinterface.provider;

import de.talha.rentalapp.userinterface.Userinterface;

public class PrimitiveProvider {

    private final Userinterface ui;

    public PrimitiveProvider(Userinterface ui) {
        this.ui = ui;
    }

    private String provideString(String message, boolean fallback, String value) {
        if (fallback) {
            message = "%s: %s | Beliebige Eingabe zum ändern".formatted(message, value);
        }
        while (true) {
            String input = ui.input(message);
            if (!input.isEmpty()) {
                return input;
            } else if (fallback) {
                return value;
            } else {
                ui.error("Eingabe darf nicht leer sein");
            }
        }
    }

    public String provideString(String message) {
        return provideString(message, false, "");
    }

    public String provideString(String message, String fallback) {
        return provideString(message, true, fallback);
    }

    private int provideInt(String message, boolean fallback, int value) {
        if (fallback) {
            message = "%s: %d | Beliebige Eingabe zum ändern".formatted(message, value);
        }
        while (true) {
            String input = provideString(message);
            if (fallback && input.isEmpty()) {
                return value;
            }
            try {
                return Integer.parseInt(input);
            } catch(NumberFormatException e) {
                ui.error("Ungültige Eingabe, nur ganze Zahlen erlaubt");
            }
        }
    }

    public int provideInt(String message) {
        return provideInt(message, false, 0);
    }

    public int provideInt(String message, int fallback) {
        return provideInt(message, true, fallback);
    }

    private double provideDouble(String message, boolean fallback, double value) {
        if (fallback) {
            message = "%s: %.2f | Beliebige Eingabe zum ändern".formatted(message, value);
        }
        while (true) {
            String input = ui.input(message);
            if (fallback && input.isEmpty()) {
                return value;
            }
            try {
                return Double.parseDouble(input);
            } catch(NumberFormatException e) {
                ui.error("Ungültige eingabe, nur Zahlen erlaubt");
            }
        }
    }

    public double provideDouble(String message) {
        return provideDouble(message, false, 0);
    }

    public double provideDouble(String message, double fallback) {
        return provideDouble(message, true, fallback);
    }

    private <T extends Enum<T>> T provideEnum(Class<T> cls, String message, boolean fallback, T value) {
        int i = 1;
        StringBuilder builder = new StringBuilder();
        for (T e: cls.getEnumConstants()) {
            if (i > 1) {
                builder.append(" ");
            }
            builder.append("(%d) %s".formatted(i, e));
            i++;
        }
        String options = builder.toString();
        if (fallback) {
            message = "%s: %s | Beliebige Eingabe zum ändern".formatted(message, value.toString());
        }
        int count = cls.getEnumConstants().length;
        while (true) {
            ui.info(options);
            String input = ui.input(message);
            if (fallback && input.isEmpty()) {
                return value;
            }
            try {
                int index = Integer.parseInt(input);
                if (index > 0 && index <= count) {
                    return cls.getEnumConstants()[index - 1];
                } else {
                    ui.error("Ungültige Eingabe, nur ganze Zahlen von 1 bis %d erlaubt".formatted(count));
                }
            } catch (NumberFormatException e) {
                ui.error("Ungültige Eingabe, nur ganze Zahlen erlaubt");
            }
        }
    }

    public <T extends Enum<T>> T provideEnum(Class<T> cls, String message) {
        return provideEnum(cls, message, false, null);
    }

    public <T extends Enum<T>> T provideEnum(T fallback, String message) {
        return provideEnum(fallback.getDeclaringClass(), message,true, fallback);
    }
}
