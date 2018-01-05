package com.dyescape.commanddeleter.cmd;

import com.dyescape.commanddeleter.CommandDeleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.inject.Inject;

public class CommandProcessor implements CommandExecutor {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject
    private CommandDeleter plugin;

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

}
