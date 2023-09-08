package one.spectra.better_chests.message_handlers;

import com.mojang.logging.LogUtils;

import net.minecraft.world.entity.player.Player;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationResponse;

public class GetConfigurationResponseHandler implements MessageHandler<GetConfigurationResponse> {

    @Override
    public void handle(Player player, GetConfigurationResponse message) {
        LogUtils.getLogger().info("Here I Am");
    }
    
}
