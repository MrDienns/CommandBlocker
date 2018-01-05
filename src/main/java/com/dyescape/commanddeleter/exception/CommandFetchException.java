package com.dyescape.commanddeleter.exception;

public class CommandFetchException extends Exception {

    public CommandFetchException(String field) {
        super(String.format("Could not find Bukkit %s map (unsuported server version?)", field));
    }

}
