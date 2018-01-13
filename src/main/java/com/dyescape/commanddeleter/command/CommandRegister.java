package com.dyescape.commanddeleter.command;

import com.dyescape.commanddeleter.exception.CommandFetchException;
import com.dyescape.commanddeleter.exception.UnknownCommandException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

import javax.inject.Singleton;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;

@Singleton
public class CommandRegister {

    // -------------------------------------------- //
    // LOGIC
    // -------------------------------------------- //

    public CommandUnregisterResult unregisterCommand(String command) throws CommandFetchException, UnknownCommandException {
        return this.unregisterCommand(this.getCommandFromAlias(command));
    }

    public CommandUnregisterResult unregisterCommand(Command command) throws CommandFetchException, UnknownCommandException {
        // Check if the provided command exists.
        if (command == null) throw new UnknownCommandException();

        // Hack into Bukkit and get the SimpleCommandMap and it's knownCommands...
        SimpleCommandMap commandMap = this.getSimpleCommandMap();
        Map<String, Command> knownCommands = this.getKnownCommands();

        // ... check if it's even registered ...
        if (!knownCommands.containsKey(command.getName())) return new CommandUnregisterResult(false);

        // First delete it the normal way.
        knownCommands.remove(command.getName());

        // Then delete all aliases the hard way.
        Iterator<Command> knownCommandIterator = knownCommands.values().iterator();
        int aliases = 0;
        while (knownCommandIterator.hasNext()) {
            Command knownCommand = knownCommandIterator.next();
            if (command.getClass().isInstance(knownCommand)) {
                aliases++;
                knownCommandIterator.remove();
            }
        }

        // Remove all aliases, if set in a certain way. This rarely happens, just in case.
        for (String alias : command.getAliases()) knownCommands.remove(alias);
        boolean result = command.unregister(commandMap);
        return new CommandUnregisterResult(aliases, command, result);
    }

    public Command getCommandFromAlias(String alias) throws CommandFetchException, UnknownCommandException {
        Command command = this.getKnownCommands().get(alias);
        if (command ==  null) throw new UnknownCommandException();
        return command;
    }

    // -------------------------------------------- //
    // PRIVATE
    // -------------------------------------------- //

    private SimpleCommandMap getSimpleCommandMap() throws CommandFetchException {
        try {
            Field field = this.getServerField("commandMap", Bukkit.getServer().getClass());
            this.makeAccessible(field);
            this.removeFinalModifier(field);
            return (SimpleCommandMap) field.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new CommandFetchException("commandMap");
        }
    }

    private Map<String, Command> getKnownCommands() throws CommandFetchException {
        try {
            SimpleCommandMap commandMap = this.getSimpleCommandMap();
            Field field = this.getServerField("knownCommands", SimpleCommandMap.class);
            this.makeAccessible(field);
            this.removeFinalModifier(field);
            return (Map<String, Command>) field.get(commandMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new CommandFetchException("knownCommands");
        }
    }

    private void makeAccessible(Field field) {
        field.setAccessible(true);
    }

    private void removeFinalModifier(Field field) throws IllegalAccessException, NoSuchFieldException {
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }

    private Field getServerField(String name, Class<?> clazz) throws NoSuchFieldException {
        return clazz.getDeclaredField(name);
    }

}
