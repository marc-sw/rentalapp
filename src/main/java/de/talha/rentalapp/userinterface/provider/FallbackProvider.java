package de.talha.rentalapp.userinterface.provider;

public class FallbackProvider {

    private final PrimitiveProvider pp;

    public FallbackProvider(PrimitiveProvider pp) {
        this.pp = pp;
    }

    public String condString(String field, String value) {
        if (!pp.provideString("%s: %s | Beliebige Eingabe zum ändern".formatted(field, value)).isEmpty()) {
            return pp.provideString(field);
        }
        return value;
    }

    public int condInt(String field, int value) {
        if (!pp.provideString("%s: %d | Beliebige Eingabe zum ändern".formatted(field, value)).isEmpty()) {
            return pp.provideInt(field);
        }
        return value;
    }

    public double condDouble(String field, double value) {
        if (!pp.provideString("%s: %.2f | Beliebige Eingabe zum ändern".formatted(field, value)).isEmpty()) {
            return pp.provideDouble(field);
        }
        return value;
    }

    public boolean condBoolean(String field, boolean value) {
        String text = value ? "Ja": "Nein";
        if (!pp.provideString("%s: %s | Beliebige Eingabe zum ändern".formatted(field, text)).isEmpty()) {
            return pp.provideBoolean(field);
        }
        return value;
    }

    public <T extends Enum<T>> T condEnum(String field, T value) {
        if (!pp.provideString("%s: %s | Beliebige Eingabe zum ändern".formatted(field, value)).isEmpty()) {
            return pp.provideEnum(value.getDeclaringClass());
        }
        return value;
    }
}
