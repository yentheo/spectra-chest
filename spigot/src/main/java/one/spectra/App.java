package one.spectra;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class App extends JavaPlugin implements PluginMessageListener {
    public static Logger LOGGER;

    @Override
    public void onEnable() {
        LOGGER = getLogger();
        getLogger().info("Plugin enabled");
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
        if (flag == 0) {
            new Sorter().Sort(player);
        } else {
            new Sorter().Sort(player.getOpenInventory().getTopInventory());
        }
    }

}