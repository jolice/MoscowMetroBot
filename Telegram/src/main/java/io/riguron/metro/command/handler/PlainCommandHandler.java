package io.riguron.metro.command.handler;

import io.riguron.metro.command.Command;
import io.riguron.metro.command.arguments.OffsetArguments;
import io.riguron.metro.command.type.AbsentCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlainCommandHandler implements CommandHandler {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public String handleCommand(String message) {
        String[] args = message.replaceFirst("/", "").split(" ");
        return commands.getOrDefault(args[0].toLowerCase(), AbsentCommand.INSTANCE)
                .execute(new OffsetArguments(args));
    }

    @Override
    public void registerCommand(Command command, List<String> aliases) {
        aliases.forEach(s -> {
            if (commands.put(s.toLowerCase(), command) != null) {
                throw new IllegalStateException("Duplicate alias: " + s + " for command " + command.getClass());
            }

        });
    }


}
