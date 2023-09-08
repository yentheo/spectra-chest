package one.spectra.better_chests.message_handlers;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Injector;

import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.abstractions.communication.JsonEncoder;
import one.spectra.better_chests.message_handlers.messages.ConfigureCurrentChestRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationResponse;
import one.spectra.better_chests.message_handlers.messages.Message;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

@ExcludeFromGeneratedCoverageReport
public class MessageHandlerHub {
    private Injector _injector;
    private Logger _logger;

    private static int MessageId = 0;

    private Dictionary<Class<Message>, Consumer<Message>> _oneTimeConsumers;

    @Inject
    public MessageHandlerHub(Injector injector, Logger logger) {
        _injector = injector;
        _logger = logger;
        _oneTimeConsumers = new Hashtable<Class<Message>, Consumer<Message>>();
    }

    public void register() {
        register(SortRequest.class, SortRequestHandler.class);
        register(ConfigureCurrentChestRequest.class, ConfigureCurrentChestRequestHandler.class);
        register(GetConfigurationRequest.class, GetConfingurationRequestHandler.class);
        register(GetConfigurationResponse.class, GetConfigurationResponseHandler.class);
    }

    public <T extends Message, R extends MessageHandler<T>> void register(Class<T> messageClass, Class<R> messageHandlerClass) {

        _logger.info("Registering message of type " + messageClass.getSimpleName());

        var jsonEncoder = new JsonEncoder<T>(messageClass);
        var handler = _injector.getInstance(messageHandlerClass);

        BetterChestsPacketHandler.INSTANCE
                .messageBuilder(messageClass, MessageId)
                .decoder(jsonEncoder).encoder(jsonEncoder)
                .consumerMainThread((m, ctx) -> {
                    ctx.get().enqueueWork(() -> {
                        var player = ctx.get().getSender();
                        handler.handle(player, m);
                        var oneTimeConsumer = _oneTimeConsumers.get(messageClass);
                        if (oneTimeConsumer != null) {
                            oneTimeConsumer.accept(m);
                            _oneTimeConsumers.remove(messageClass);
                        }
                    });
                    ctx.get().setPacketHandled(true);
                })
                .add();
        MessageId++;
    }

    @SuppressWarnings("unchecked")
    public <TResponse extends Message> void registerOnce(Consumer<TResponse> response, Class<TResponse> clazz) {
        _oneTimeConsumers.put((Class<Message>)clazz, (Consumer<Message>)response);
    }
}
