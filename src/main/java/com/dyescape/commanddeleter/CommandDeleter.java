package com.dyescape.commanddeleter;

import com.dyescape.commanddeleter.command.TestCommand;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public final class CommandDeleter extends JavaPlugin {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject private TestCommand command;
    @Inject private CommandDeleterLogger logger;

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
        this.logger.log("Enabled plugin.");
    }

    // -------------------------------------------- //
    // DISABLE
    // -------------------------------------------- //

    @Override
    public void onDisable() {

    }

}

