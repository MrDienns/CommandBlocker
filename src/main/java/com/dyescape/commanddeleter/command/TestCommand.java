package com.dyescape.commanddeleter.command;

import com.dyescape.commanddeleter.CommandDeleterLogger;
import com.dyescape.commanddeleter.exception.CommandFetchException;
import com.dyescape.commanddeleter.exception.UnknownCommandException;
import com.dyescape.commanddeleter.util.TextUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.inject.Inject;

public class TestCommand implements CommandExecutor {

    @Inject private CommandRegister commandRegister;
    @Inject private CommandDeleterLogger logger;
    @Inject private TextUtil textUtil;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(this.textUtil.parse("<rose>Not enough args (/commanddeleter unregister <command>)"));
            return false;
        }

        if (args[0].equalsIgnoreCase("unregister")) {
            String deleteCommand = args[1];
            try {
                CommandUnregisterResult result = this.commandRegister.unregisterCommand(deleteCommand);
                if (result.getAliasDeletionCount() > 0) {
                    sender.sendMessage(this.textUtil.parse(String.format("<lime>Command '%s' with %s aliases deleted!",
                            args[1], result.getAliasDeletionCount())));
                } else {
                    sender.sendMessage(this.textUtil.parse(String.format("<lime>Command '%s' deleted!", args[1])));
                }
                return true;
            } catch (CommandFetchException e) {
                this.logger.error(e.getMessage());
                e.printStackTrace();
                return false;
            } catch (UnknownCommandException e) {
                sender.sendMessage(this.textUtil.parse(String.format("<rose>Command '%s' does not exist!", args[1])));
                return false;
            }
        }
        return false;
    }

}
