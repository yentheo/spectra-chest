package one.spectra.chest.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public interface Command {    
    String getCommand();
    LiteralArgumentBuilder<FabricClientCommandSource> buildCommand();
    boolean execute(CommandContext<FabricClientCommandSource> context);
}
