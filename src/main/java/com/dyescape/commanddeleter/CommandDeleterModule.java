package com.dyescape.commanddeleter;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class CommandDeleterModule extends AbstractModule {

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    private final CommandDeleter plugin;

    // -------------------------------------------- //
    // CONSTRUCTOR
    // -------------------------------------------- //

    public CommandDeleterModule(CommandDeleter plugin) {
        this.plugin = plugin;
    }

    // -------------------------------------------- //
    // LOGIC
    // -------------------------------------------- //

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    protected void configure() {
        this.bind(CommandDeleter.class).toInstance(this.plugin);
    }

}
