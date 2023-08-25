package one.spectra.better_chests.message_handlers;

import net.minecraft.world.entity.player.Player;

public interface MessageHandler<T> {
    void handle(Player player, T message);
}
