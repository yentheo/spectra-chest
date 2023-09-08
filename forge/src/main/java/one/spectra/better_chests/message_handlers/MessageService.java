package one.spectra.better_chests.message_handlers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.inject.Inject;

import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.message_handlers.messages.Message;

public class MessageService {

    private MessageHandlerHub _hub;

    @Inject
    public MessageService(MessageHandlerHub hub) {
        _hub = hub;
    }

    public <TRequest extends Message, TResponse extends Message> Future<TResponse> Request(TRequest request, Class<TResponse> clazz) {
        BetterChestsPacketHandler.INSTANCE.sendToServer(request);
        var completableFuture = new CompletableFuture<TResponse>();

        Executors.newCachedThreadPool().submit(() -> {
            _hub.registerOnce(r -> {
                completableFuture.complete(r);
            }, clazz);
        });

        return completableFuture;

    }
}
