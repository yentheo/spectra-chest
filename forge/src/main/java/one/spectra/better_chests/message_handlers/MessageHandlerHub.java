package one.spectra.better_chests.message_handlers;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.simple.SimpleChannel;
import one.spectra.better_chests.BetterChestsMod;
import one.spectra.better_chests.abstractions.communication.JsonEncoder;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

public class MessageHandlerHub {

    private BetterChestsMod _plugin;
    private Set<MessageHandler> _messageHandlers;

    private List<Runnable> _registrations = new ArrayList<Runnable>();
    private static int index = 0;

    @Inject
    public MessageHandlerHub(BetterChestsMod plugin, Set<MessageHandler> messageHandlers) {
        _plugin = plugin;
        _messageHandlers = messageHandlers;
    }

    public void register() {
    }
}
