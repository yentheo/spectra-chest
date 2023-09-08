package one.spectra.better_chests.message_handlers.messages;

public class ConfigureCurrentChestRequest implements Message {
    public Configuration configuration;

    public ConfigureCurrentChestRequest(Configuration config) {
        this.configuration = config;
    }
}
