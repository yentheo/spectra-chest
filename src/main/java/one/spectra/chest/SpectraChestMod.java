package one.spectra.chest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpectraChestMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("spectra-chest");

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT
				.register(
						(dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("sort")
								.executes(context -> {
									// For versions below 1.19, replace "Text.literal" with "new LiteralText".
									context.getSource().sendMessage(Text
											.literal("Sort called by " + context.getSource().getPlayer().getName()));
									new Sorter().Sort(context.getSource().getPlayer());
									return 1;
								})));

		ServerPlayNetworking.registerGlobalReceiver(new Identifier("spectra-chest", "move-up"),
				(server, player, handler, buf, responseSender) -> {
					if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
						var screenhandler = (GenericContainerScreenHandler) player.currentScreenHandler;
						var playerInventory = player.getInventory();
						var chestInventory = screenhandler.getInventory();
						new Mover().Move(playerInventory, chestInventory, 9, 0, 36, chestInventory.size());
					}
				});
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("spectra-chest", "move-down"),
				(server, player, handler, buf, responseSender) -> {
					if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
						var screenhandler = (GenericContainerScreenHandler) player.currentScreenHandler;
						var playerInventory = player.getInventory();
						var chestInventory = screenhandler.getInventory();
						new Mover().Move(chestInventory, playerInventory, 0, 9, chestInventory.size(), 36);
					}
				});
		ServerPlayNetworking.registerGlobalReceiver(new Identifier("spectra-chest", "sort"),
				(server, player, handler, buf, responseSender) -> {
					var flag = buf.readByte();
					if (flag == 0) {
						LOGGER.info("sort request received from client for player inventory sort, "
								+ player.getName().getString());
						new Sorter().Sort(player);
					} else {
						LOGGER.info("sort request received from client for chest inventory sort, "
								+ player.getName().getString());

						if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
							var screenhandler = (GenericContainerScreenHandler) player.currentScreenHandler;

							if (!player.world.isClient) {
								var entity = screenhandler.getInventory();
								if (entity != null)
									new Sorter().Sort(entity);
							}
						}
					}
				});
	}
}
