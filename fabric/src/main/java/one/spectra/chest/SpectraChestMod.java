package one.spectra.chest;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import one.spectra.chest.commands.CommandHub;
import one.spectra.chest.communication.receivers.ReceiverHub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;

public class SpectraChestMod implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("spectra-chest");

	@Override
	public void onInitializeClient() {		
		var injector = Guice.createInjector(new SpectraChestModModule());

		var receiverHub = injector.getInstance(ReceiverHub.class);
		receiverHub.register();
		var commandHub = injector.getInstance(CommandHub.class);
		commandHub.register();
	}
}
