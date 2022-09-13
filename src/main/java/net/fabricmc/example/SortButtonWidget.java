package net.fabricmc.example;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class SortButtonWidget extends TexturedButtonWidget {
    private Inventory inventory;
    private static final Identifier texture = new Identifier("modid", "sort-button.png");

    public SortButtonWidget(int x, int y, Inventory inventory) {
        super(x, y, 9, 9, 0, 0, 9, texture, 9, 18, null, Text.literal(""));
        this.inventory = inventory;
    }

    @Override
    public void onPress() {
        ExampleMod.LOGGER.info("Pressed button");
        var buf = PacketByteBufs.create();
        if (inventory == Inventory.PLAYER) {
            buf.writeByte(0);
        } else {
            buf.writeByte(1);
        }
        ClientPlayNetworking.send(new Identifier("modid", "sort"), buf);
    }
}
