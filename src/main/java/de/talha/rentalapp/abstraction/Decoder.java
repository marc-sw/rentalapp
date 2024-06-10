package de.talha.rentalapp.abstraction;

public interface Decoder<T> {
    T decode(String data);
}
