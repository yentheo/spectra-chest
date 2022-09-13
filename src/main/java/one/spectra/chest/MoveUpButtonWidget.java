package one.spectra.chest;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class MoveUpButtonWidget extends TexturedButtonWidget {
    private static final Identifier texture = new Identifier("spectra-chest", "move-up-button.png");

    public MoveUpButtonWidget(int x, int y) {
        super(x, y, 9, 9, 0, 0, 9, texture, 9, 18, null, Text.literal(""));
    }

    @Override
    public void onPress() {
        ExampleMod.LOGGER.info("Pressed button");
        var buf = PacketByteBufs.empty();
        ClientPlayNetworking.send(new Identifier("spectra-chest", "move-up"), buf);
    }
}
