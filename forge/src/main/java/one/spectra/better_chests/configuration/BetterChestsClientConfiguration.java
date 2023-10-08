package one.spectra.better_chests.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class BetterChestsClientConfiguration {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> SPREAD;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SORT_ALPHABETICALLY;

    static {
        BUILDER.push("Configuration for Better Chests");

        SPREAD = BUILDER.comment("Sets the default behaviour of spread").define("spread", true);
        SORT_ALPHABETICALLY = BUILDER.comment("Sets the default behaviour of alphabetical sort").define("sort_alphabetically", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
