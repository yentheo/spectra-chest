package one.spectra.better_chests.message_handlers;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Injector;

import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.abstractions.communication.JsonEncoder;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

@ExcludeFromGeneratedCoverageReport
public class MessageHandlerHub {
    private Injector _injector;
    private Logger _logger;

    private static int MessageId = 0;

    @Inject
    public MessageHandlerHub(Injector injector, Logger logger) {
        _injector = injector;
        _logger = logger;
    }

    public void register() {
        register(SortRequest.class, SortRequestHandler.class);
    }

    public <T, R extends MessageHandler<T>> void register(Class<T> messageClass, Class<R> messageHandlerClass) {

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
                });
                ctx.get().setPacketHandled(true);
            })
            .add();
        MessageId++;
    }
}
