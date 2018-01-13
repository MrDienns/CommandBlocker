package com.dyescape.commanddeleter.command.commands.parameters;

import org.bukkit.command.Command;

public class UnregisterableCommand {

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    private final Command command;

    // -------------------------------------------- //
    // CONSTRUCTOR
    // -------------------------------------------- //

    public UnregisterableCommand(Command command) {
        this.command = command;
    }

    // -------------------------------------------- //
    // FIELD ACCESS
    // -------------------------------------------- //

    public Command getCommand() {
        return this.command;
    }

}
