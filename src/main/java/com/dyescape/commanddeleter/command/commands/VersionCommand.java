package com.dyescape.commanddeleter.command.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Subcommand;
import com.google.inject.Singleton;
import org.bukkit.command.CommandSender;

@Singleton
@CommandAlias("%base")
public class VersionCommand extends PluginCommand {

    // -------------------------------------------- //
    // EXECUTION
    // -------------------------------------------- //

    @Subcommand("version|v")
    public void onVersionCommand(CommandSender sender) {
        this.sendEmptyLine(sender);
        this.sendLine(sender, String.format("<rose>CommandDeleter <silver>by <yellow>%s", this.parseAuthors()));
        this.sendLine(sender, String.format("<silver>Version <yellow>%s", this.plugin.getDescription().getVersion()));
        this.sendEmptyLine(sender);
    }

}
