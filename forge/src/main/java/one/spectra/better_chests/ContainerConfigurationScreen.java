package one.spectra.better_chests;

import java.util.Optional;
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
import one.spectra.better_chests.configuration.BetterChestsClientConfiguration;
import one.spectra.better_chests.message_handlers.MessageService;
import one.spectra.better_chests.message_handlers.messages.Configuration;
import one.spectra.better_chests.message_handlers.messages.ConfigureCurrentChestRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationRequest;
import one.spectra.better_chests.message_handlers.messages.GetConfigurationResponse;

@ExcludeFromGeneratedCoverageReport
public class ContainerConfigurationScreen extends Screen {

    private Checkbox _spreadCheckbox;
    private Checkbox _sortAlphabeticallyCheckbox;
    private Screen _parentScreen;

    protected ContainerConfigurationScreen(Component p_96550_, Screen parentScreen) {
        super(p_96550_);
        _parentScreen = parentScreen;
        var messageService = BetterChestsMod.INJECTOR.getInstance(MessageService.class);
        var futureResponse = messageService.Request(new GetConfigurationRequest(), GetConfigurationResponse.class);
        Executors.newCachedThreadPool().submit(() -> {
            try {
                var response = futureResponse.get();
                LogUtils.getLogger().info("Current configured spread value:");
                LogUtils.getLogger().info(String.valueOf(response.configuration.spread));
                var spread = response.configuration.spread != null ? response.configuration.spread : BetterChestsClientConfiguration.SPREAD.get();
                if (spread && !_spreadCheckbox.selected()) {
                    _spreadCheckbox.onPress();
                }
                var sortAlphabetically = response.configuration.sortAlphabetically != null ? response.configuration.sortAlphabetically : BetterChestsClientConfiguration.SORT_ALPHABETICALLY.get();
                if (sortAlphabetically && !_sortAlphabeticallyCheckbox.selected()) {
                    _sortAlphabeticallyCheckbox.onPress();
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
        _sortAlphabeticallyCheckbox = new Checkbox(32, 64, 16, 20, Component.literal("Sort alphabetically"), true);
        this.addRenderableWidget(_spreadCheckbox);
        this.addRenderableWidget(_sortAlphabeticallyCheckbox);
        if (_spreadCheckbox.selected()) {
            _spreadCheckbox.onPress();
        }
        if (_sortAlphabeticallyCheckbox.selected()) {
            _sortAlphabeticallyCheckbox.onPress();
        }
        
        var saveChangesButton = Button.builder(Component.literal("Save changes"), e -> {
            var config = new Configuration();
            config.spread = _spreadCheckbox.selected();
            config.sortAlphabetically = _sortAlphabeticallyCheckbox.selected();
            var request = new ConfigureCurrentChestRequest(config);
            BetterChestsPacketHandler.INSTANCE.sendToServer(request);
            Minecraft.getInstance().setScreen(_parentScreen);

        }).pos(32, 96).build();
        this.addRenderableWidget(saveChangesButton);
    }

    @Override
    public boolean keyPressed(int p_96552_, int p_96553_, int p_96554_) {
        if (p_96552_ == 256 && this.shouldCloseOnEsc()) {
            Minecraft.getInstance().setScreen(_parentScreen);
            return true;
        }
        return super.keyPressed(p_96552_, p_96553_, p_96554_);
    }

    @Override
    public void render(GuiGraphics huh, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(huh);
        super.render(huh, mouseX, mouseY, partialTick);
        // https://docs.minecraftforge.net/en/1.19.x/gui/screens/
    }
}
