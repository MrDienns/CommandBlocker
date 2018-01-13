package com.dyescape.commanddeleter.command.commands.parameters;

import co.aikar.commands.BukkitCommandExecutionContext;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.contexts.ContextResolver;
import com.dyescape.commanddeleter.command.CommandRegister;
import com.dyescape.commanddeleter.exception.CommandFetchException;
import com.dyescape.commanddeleter.exception.UnknownCommandException;
import com.dyescape.commanddeleter.util.TextUtil;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UnregisterableCommandContextResolver implements ContextResolver<UnregisterableCommand,
        BukkitCommandExecutionContext> {

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject private CommandRegister commandRegister;
    @Inject private TextUtil textUtil;

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public UnregisterableCommand getContext(BukkitCommandExecutionContext bukkitCommandExecutionContext)
            throws InvalidCommandArgument {
        try {
            return new UnregisterableCommand(this.commandRegister.getCommandFromAlias(bukkitCommandExecutionContext.getFirstArg()));
        } catch (CommandFetchException | UnknownCommandException e) {
            throw new InvalidCommandArgument(this.textUtil.parse(e.getMessage()));
        }
    }
}
