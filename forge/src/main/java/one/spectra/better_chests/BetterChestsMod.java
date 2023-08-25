package one.spectra.better_chests;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.slf4j.Logger;

import com.google.inject.Guice;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent.Context;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.abstractions.communication.JsonEncoder;
import one.spectra.better_chests.message_handlers.MessageHandlerHub;
import one.spectra.better_chests.message_handlers.SortRequestHandler;
import one.spectra.better_chests.message_handlers.messages.SortRequest;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterChestsMod.MODID)
public class BetterChestsMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "better_chests_mod";
    private static final Logger LOGGER = LogUtils.getLogger();
    private MessageHandlerHub _messageHandlerHub;

    public BetterChestsMod() {
        LOGGER.info("test BETTER");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Plugin enabled");
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");

        var injector = Guice.createInjector(new BetterChestsModule());
        var jsonEncoder = new JsonEncoder<SortRequest>(SortRequest.class);
        BetterChestsPacketHandler.INSTANCE.messageBuilder(SortRequest.class, 0).encoder(jsonEncoder).decoder(jsonEncoder).consumerMainThread((r, ctx) -> {
            LOGGER.info("GOT SOMETHIGN");
            ctx.get().enqueueWork(() -> {
                var player = ctx.get().getSender();
                injector.getInstance(SortRequestHandler.class).handle(player, r);
            });
            ctx.get().setPacketHandled(true);
        }).add();
        _messageHandlerHub = injector.getInstance(MessageHandlerHub.class);
        _messageHandlerHub.register();

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            MinecraftForge.EVENT_BUS.register(ScreenEvents.class);
        }
    }

    public static void handle(SortRequest request, Supplier<Context> ctx) {
        LogUtils.getLogger().info("Got somethig");
    }
}
