package one.spectra.better_chests;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import one.spectra.better_chests.abstractions.communication.BetterChestsPacketHandler;
import one.spectra.better_chests.message_handlers.MessageService;
import one.spectra.better_chests.message_handlers.messages.Configuration;
import one.spectra.better_chests.message_handlers.messages.ConfigureCurrentChestRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationResponse;

@ExcludeFromGeneratedCoverageReport
public class ContainerConfigurationScreen extends Screen {

    private Checkbox _spreadCheckbox;
    private Screen _parentScreen;

    protected ContainerConfigurationScreen(Component p_96550_, Screen parentScreen) {
        super(p_96550_);
        _parentScreen = parentScreen;
        var messageService = BetterChestsMod.INJECTOR.getInstance(MessageService.class);
        var futureResponse = messageService.Request(new GetConfigurationRequest(), GetConfigurationResponse.class);
        Executors.newCachedThreadPool().submit(() -> {
            try {
                var response = futureResponse.get();
                LogUtils.getLogger().info(String.valueOf(response.configuration.spread));
                if (response.configuration.spread && !_spreadCheckbox.selected()) {
                    _spreadCheckbox.onPress();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void init() {
        super.init();
        _spreadCheckbox = new Checkbox(32, 32, 16, 20, Component.literal("Spread"), true);
        this.addRenderableWidget(_spreadCheckbox);
        var saveChangesButton = Button.builder(Component.literal("Save changes"), e -> {
            var config = new Configuration();
            config.spread = _spreadCheckbox.selected();
            var request = new ConfigureCurrentChestRequest(config);
            BetterChestsPacketHandler.INSTANCE.sendToServer(request);
            Minecraft.getInstance().setScreen(_parentScreen);

        }).pos(32, 64).build();
        this.addRenderableWidget(saveChangesButton);
        if (_spreadCheckbox.selected()) {
            _spreadCheckbox.onPress();
        }
    }

    @Override
    public void render(GuiGraphics huh, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(huh);
        super.render(huh, mouseX, mouseY, partialTick);
        // https://docs.minecraftforge.net/en/1.19.x/gui/screens/
    }
}
