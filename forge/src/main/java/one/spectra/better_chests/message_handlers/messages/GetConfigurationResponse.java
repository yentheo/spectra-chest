package one.spectra.better_chests.message_handlers.messages;

public class GetConfigurationResponse implements Message {
    public Configuration configuration;

    public GetConfigurationResponse(Configuration config) {
        this.configuration = config;
    }
}
