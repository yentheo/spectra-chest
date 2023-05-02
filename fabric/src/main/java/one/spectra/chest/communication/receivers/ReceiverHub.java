package one.spectra.chest.communication.receivers;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.reflect.TypeLiteral;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mojang.authlib.minecraft.client.ObjectMapper;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ReceiverHub {

    private Injector _injector;

    @Inject
    public ReceiverHub(Injector injector) {
        _injector = injector;
    }

    public void register() {
        register(FeatureReceiver.class, String[].class);
    }

    private <TReceiver extends Receiver<TMessage>, TMessage> void register(Class<TReceiver> receiverType, Class<TMessage> messageType) {
        var receiver = _injector.getInstance(receiverType);
        ClientPlayNetworking.registerGlobalReceiver(receiver.getChannel(), (client, handler, buf, responseSender) -> {
            var mapper = ObjectMapper.create();
            var myBuf = buf.getWrittenBytes();
            var json = new String(myBuf, StandardCharsets.UTF_8);
            var features = mapper.readValue(json, messageType);
            receiver.handle(features);
        });
    }

}
