package one.spectra.chest.commands;

import com.google.inject.Inject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import one.spectra.chest.FeatureService;

public class FeaturesCommand implements Command {

    public String getCommand() {
        return "better-chest";
    }

    private FeatureService _featureService;

    @Inject
    public FeaturesCommand(FeatureService featureService) {
        _featureService = featureService;
    }

    public LiteralArgumentBuilder<FabricClientCommandSource> buildCommand() {
        return ClientCommandManager.literal("features");
    }

    public boolean execute(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.literal("Features supported by the server: " + String.join(", ", _featureService.getServerFeatures())));
        return true;
    }
}
