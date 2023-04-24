package one.spectra.better_chests;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.inject.Guice;
import com.google.inject.Injector;

import one.spectra.better_chests.inventory.SpectraInventory;

@ExcludeFromGeneratedCoverageReport
public class BetterChestsPlugin extends JavaPlugin implements PluginMessageListener {
    public static Logger LOGGER;
    public static Server SERVER;
    public static Injector INJECTOR;

    @Override
    public void onEnable() {
        INJECTOR = Guice.createInjector(new BetterChestsModule(getServer()));
        LOGGER = getLogger();
        SERVER = getServer();
        LOGGER.info("Plugin enabled");
        this.getCommand("better-chests")
                .setExecutor(new BetterChestsCommandExecutor());
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "spectra-chest:sort", this);

    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this, "spectra-chest:sort", this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        getLogger().info("Message received on channel " + channel);
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        byte flag = in.readByte();
        var inventory = flag == 0
                ? new SpectraInventory(player.getInventory())
                : new SpectraInventory(player.getOpenInventory().getTopInventory());
        INJECTOR.getInstance(Sorter.class).sort(inventory);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return true;
    }
}
