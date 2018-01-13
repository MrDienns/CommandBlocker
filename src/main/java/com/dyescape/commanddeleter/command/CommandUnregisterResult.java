package com.dyescape.commanddeleter.command;

import org.bukkit.command.Command;

public class CommandUnregisterResult {

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    private final int aliasesDeleted;
    private final Command deletedCommand;
    private boolean success;

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CommandUnregisterResult(boolean success) {
        this(0, null, success);
    }

    public CommandUnregisterResult(int aliasesDeleted, Command deletedCommand, boolean success) {
        this.aliasesDeleted = aliasesDeleted;
        this.deletedCommand = deletedCommand;
        this.success = success;
    }

    // -------------------------------------------- //
    // FIELD ACCESS
    // -------------------------------------------- //

    public boolean succeeded() {
        return this.success;
    }

    public int getAliasDeletionCount() {
        return this.aliasesDeleted;
    }

    public Command getDeletedCommand() {
        return this.deletedCommand;
    }

}
