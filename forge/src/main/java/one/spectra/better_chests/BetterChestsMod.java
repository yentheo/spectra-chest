package one.spectra.better_chests;

import org.slf4j.Logger;

import com.google.inject.Guice;
import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import one.spectra.better_chests.message_handlers.MessageHandlerHub;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterChestsMod.MODID)
@ExcludeFromGeneratedCoverageReport
public class BetterChestsMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "better_chests_mod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterChestsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Better Chests are enabled");
        var injector = Guice.createInjector(new BetterChestsModule());
        var messageHandlerHub = injector.getInstance(MessageHandlerHub.class);
        messageHandlerHub.register();

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MinecraftForge.EVENT_BUS.register(ScreenEvents.class);
        }
    }
}
