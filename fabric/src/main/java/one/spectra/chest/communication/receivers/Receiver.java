package one.spectra.chest.communication.receivers;

import net.minecraft.util.Identifier;

public interface Receiver<T> {
    Identifier getChannel();
    void handle(T message);
}
