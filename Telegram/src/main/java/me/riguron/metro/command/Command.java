package me.riguron.metro.command;

import me.riguron.metro.command.arguments.Arguments;
import me.riguron.metro.command.arguments.Arguments;

import java.util.List;

public interface Command {

    String execute(Arguments args);

    List<String> aliases();
}
