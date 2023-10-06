package one.spectra.better_chests.warehouse;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "better_chests");

    public static final RegistryObject<EntityType<AgvEntity>> AGV = ENTITY_TYPES.register("agv", () -> EntityType.Builder
        .of(AgvEntity::new, MobCategory.MISC)
        .sized(1, 1)
        .build("agv"));
}
