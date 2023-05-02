package one.spectra.chest.commands;

import com.google.inject.Inject;
import com.google.inject.Injector;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class CommandHub {

    private Injector _injector;

    @Inject
    public CommandHub(Injector injector) {
        _injector = injector;
    }

    public void register() {
        register(FeaturesCommand.class);
    }

    private <TCommand extends Command> void register(Class<TCommand> commandType) {
        var command = _injector.getInstance(commandType);
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal(command.getCommand())
                    .then(command.buildCommand().executes(context -> command.execute(context) ? 1 : 0)));
        });
    }

}
