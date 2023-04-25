package one.spectra.better_chests.message_handlers;

import org.bukkit.entity.Player;

public interface MessageHandler {
    String getChannel();
    void handle(Player player, byte[] message);
}
