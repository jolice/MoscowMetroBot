package io.riguron.metro.command;

import io.riguron.metro.command.arguments.Arguments;
import io.riguron.metro.command.arguments.Arguments;
import io.riguron.metro.command.arguments.Arguments;

import java.util.List;

public interface Command {

    String execute(Arguments args);

    List<String> aliases();
}
