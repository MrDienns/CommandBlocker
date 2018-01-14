package com.dyescape.commanddeleter.command.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.HelpCommand;
import com.dyescape.commanddeleter.CommandDeleter;
import com.dyescape.commanddeleter.util.TextUtil;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.command.CommandSender;

@Singleton
@CommandAlias("%base")
public class PluginCommand extends BaseCommand {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject protected TextUtil textUtil;
    @Inject protected CommandDeleter plugin;

    // -------------------------------------------- //
    // EXECUTION
    // -------------------------------------------- //

    @CommandAlias("%base")
    @HelpCommand
    public void onCommand(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    // -------------------------------------------- //
    // PROTECTED
    // -------------------------------------------- //

    protected void sendEmptyLine(CommandSender sender) {
        sender.sendMessage("");
    }

    protected void sendLine(CommandSender sender, String message) {
        sender.sendMessage(this.textUtil.parse(message));
    }

    protected String parseAuthors() {
        StringBuilder builder = new StringBuilder();
        for (String author : this.plugin.getDescription().getAuthors())
            builder.append(String.format("<yellow>%s<silver>, ", author));
        return builder.substring(0, builder.length() - 2);
    }

}
