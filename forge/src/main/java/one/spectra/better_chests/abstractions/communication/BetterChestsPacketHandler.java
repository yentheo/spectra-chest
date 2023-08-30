package one.spectra.better_chests.abstractions.communication;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import one.spectra.better_chests.ExcludeFromGeneratedCoverageReport;

@ExcludeFromGeneratedCoverageReport
public class BetterChestsPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("better_chests", "main"),
            () -> PROTOCOL_VERSION,
            version -> true,
            version -> true);
}
