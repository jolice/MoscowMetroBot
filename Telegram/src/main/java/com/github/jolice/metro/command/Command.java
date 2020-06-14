package com.github.jolice.metro.command;

import com.github.jolice.metro.command.arguments.Arguments;

import java.util.List;

public interface Command {

    String execute(Arguments args);

    List<String> aliases();
}
