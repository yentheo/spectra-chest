package one.spectra.better_chests.warehouse;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AgvEntity extends AbstractMinecartContainer {

    public AgvEntity(EntityType<?> p_38213_, Level p_38214_) {
        super(p_38213_, p_38214_);
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_38222_, Inventory p_38223_) {
        return null;
    }

    @Override
    protected Item getDropItem() {
        return new AgvItem(getMinecartType(), null);
    }

    @Override
    public Type getMinecartType() {
        return Type.RIDEABLE;
    }
    
}
