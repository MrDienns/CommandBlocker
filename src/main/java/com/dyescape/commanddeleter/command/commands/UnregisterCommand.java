package com.dyescape.commanddeleter.command.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.dyescape.commanddeleter.command.CommandRegister;
import com.dyescape.commanddeleter.command.CommandUnregisterResult;
import com.dyescape.commanddeleter.command.commands.parameters.UnregisterableCommand;
import com.dyescape.commanddeleter.exception.CommandFetchException;
import com.dyescape.commanddeleter.exception.UnknownCommandException;
import com.dyescape.commanddeleter.util.TextUtil;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.command.CommandSender;

@Singleton
@CommandAlias("%base")
public class UnregisterCommand extends PluginCommand {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject private CommandRegister commandRegister;
    @Inject private TextUtil textUtil;

    // -------------------------------------------- //
    // EXECUTION
    // -------------------------------------------- //

    @CommandCompletion("unregister")
    @Subcommand("unregister|u")
    @CommandAlias("unregister|u")
    @CommandPermission("commanddeleter.unregister")
    public void onUnregisterCommand(CommandSender sender, UnregisterableCommand command) {
        try {
            CommandUnregisterResult result = this.commandRegister.unregisterCommand(command.getCommand());
            if (result.getAliasDeletionCount() > 0) {
                sender.sendMessage(this.textUtil.parse(String.format(
                        "<lime>Command '%s' with %s aliases/subcommands deleted!",
                        command.getCommand().getName(), result.getAliasDeletionCount())));
            } else {
                sender.sendMessage(this.textUtil.parse(String.format("<lime>Command '%s' deleted!",
                        command.getCommand().getName())));
            }
        } catch (UnknownCommandException | CommandFetchException e) {
            sender.sendMessage(textUtil.parse(String.format("<red>Error: %s", e.getMessage())));
        }
    }

}
