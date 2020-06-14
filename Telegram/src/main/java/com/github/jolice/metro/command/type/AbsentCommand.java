package com.github.jolice.metro.command.type;

import com.github.jolice.metro.command.arguments.Arguments;
import com.github.jolice.metro.command.Command;

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
