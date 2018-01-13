package com.dyescape.commanddeleter;

import co.aikar.commands.BukkitCommandManager;
import com.dyescape.commanddeleter.command.commands.PluginCommand;
import com.dyescape.commanddeleter.command.commands.UnregisterCommand;
import com.dyescape.commanddeleter.command.commands.VersionCommand;
import com.dyescape.commanddeleter.command.commands.parameters.UnregisterableCommand;
import com.dyescape.commanddeleter.command.commands.parameters.UnregisterableCommandContextResolver;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandDeleter extends JavaPlugin {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject private CommandDeleterLogger logger;

    @Inject private PluginCommand mainCommand;
    @Inject private VersionCommand versionCommand;
    @Inject private UnregisterCommand unregisterCommand;

    @Inject private UnregisterableCommandContextResolver unregisterableCommandContextResolver;

    // -------------------------------------------- //
    // ENABLE
    // -------------------------------------------- //

    @Override
    public void onEnable() {
        // Fetch dependencies
        CommandDeleterModule module = new CommandDeleterModule(this);
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        // Register commands
        BukkitCommandManager commandManager = new BukkitCommandManager(this);

        // ... Replacements ...
        commandManager.getCommandReplacements().addReplacement("base", "commanddeleter|deleter|cd");

        // ... Context resolvers ...
        commandManager.getCommandContexts().registerContext(UnregisterableCommand.class,
                this.unregisterableCommandContextResolver);

        // ... Registration
        commandManager.registerCommand(this.mainCommand);
        commandManager.registerCommand(this.versionCommand);
        commandManager.registerCommand(this.unregisterCommand);

        // Log a friendly message
        this.logger.log("Enabled plugin.");
    }

    // -------------------------------------------- //
    // DISABLE
    // -------------------------------------------- //

    @Override
    public void onDisable() {

    }

}

