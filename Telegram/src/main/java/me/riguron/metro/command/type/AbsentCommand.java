package me.riguron.metro.command.type;

import me.riguron.metro.command.Command;
import me.riguron.metro.command.arguments.Arguments;
import me.riguron.metro.command.arguments.Arguments;

import java.util.Collections;
import java.util.List;

public enum AbsentCommand implements Command {

    INSTANCE;

    @Override
    public List<String> aliases() {
        return Collections.emptyList();
    }

    @Override
    public String execute(Arguments args) {
        return "Unknown command!";
    }
}
