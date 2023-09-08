package one.spectra.better_chests.message_handlers;

import one.spectra.better_chests.abstractions.Player;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationResponse;

public class GetConfingurationRequestHandler implements MessageHandler<GetConfigurationRequest> {

    @Override
    public void handle(Player player, GetConfigurationRequest message) {
        var openContainer = player.getOpenContainer();
        if (openContainer != null) {
            player.sendTo(new GetConfigurationResponse(openContainer.geConfiguration()));
        }
    }
}
