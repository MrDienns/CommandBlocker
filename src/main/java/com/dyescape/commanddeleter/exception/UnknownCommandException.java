package com.dyescape.commanddeleter.exception;

public class UnknownCommandException extends Exception {

    public UnknownCommandException() {
        super("Command does not exist.");
    }

}
