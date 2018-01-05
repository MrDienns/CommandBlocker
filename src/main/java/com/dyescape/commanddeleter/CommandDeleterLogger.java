package com.dyescape.commanddeleter;

import com.dyescape.commanddeleter.util.TextUtil;
import org.bukkit.Bukkit;

import javax.inject.Inject;

public class CommandDeleterLogger {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject
    private TextUtil textUtil;

    // -------------------------------------------- //
    // CONSTANTS
    // -------------------------------------------- //

    private final String logPrefix = "<green>[<lime>CommandDeleter<green>]<lime> ";
    private final String warningPrefix = "<orange>[<yellow>CommandDeleter<orange>]<yellow> ";
    private final String errorPrefix = "<red>[<rose>CommandDeleter<red>]<rose> ";

    // -------------------------------------------- //
    // LOGIC
    // -------------------------------------------- //

    public void log(String message) {
        this.send(this.logPrefix + message);
    }

    public void warning(String message) {
        this.send(this.warningPrefix + message);
    }

    public void error(String message) {
        this.send(this.errorPrefix + message);
    }

    // -------------------------------------------- //
    // LOGIC
    // -------------------------------------------- //

    private void send(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(this.textUtil.parse(message));
    }

}
