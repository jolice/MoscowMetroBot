package me.nextgeneric.metro.command;

import me.nextgeneric.metro.command.arguments.Arguments;

import java.util.List;

public interface Command {

    String execute(Arguments args);

    List<String> aliases();
}
