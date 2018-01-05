package com.dyescape.commanddeleter;

import com.dyescape.commanddeleter.cmd.CommandProcessor;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public final class CommandDeleter extends JavaPlugin {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject
    private CommandProcessor command;

    // -------------------------------------------- //
    // ENABLE
    // -------------------------------------------- //

    @Override
    public void onEnable() {
        // Fetch dependencies
        CommandDeleterModule module = new CommandDeleterModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        // Register command
        this.getCommand("commanddeleter").setExecutor(this.command);
    }

    // -------------------------------------------- //
    // DISABLE
    // -------------------------------------------- //

    @Override
    public void onDisable() {

    }

}

