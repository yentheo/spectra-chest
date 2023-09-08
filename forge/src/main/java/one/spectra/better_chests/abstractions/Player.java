package one.spectra.better_chests.abstractions;

import one.spectra.better_chests.inventory.SpectraInventory;

public interface Player {
    SpectraInventory getOpenContainer();
    SpectraInventory getInventory();
    <TMessage> void sendTo(TMessage message);
}
