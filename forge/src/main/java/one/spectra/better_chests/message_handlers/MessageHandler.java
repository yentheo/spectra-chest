package one.spectra.better_chests.message_handlers;

import one.spectra.better_chests.abstractions.Player;

public interface MessageHandler<T> {
    void handle(Player player, T message);
}
