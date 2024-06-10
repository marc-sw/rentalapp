package de.talha.rentalapp.abstraction;

import de.talha.rentalapp.userinterface.provider.FallbackProvider;

public interface Updatable {
    void update(FallbackProvider fallbackProvider);
}
