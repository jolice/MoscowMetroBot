package com.github.jolice.metro.command.handler;

import com.github.jolice.metro.command.Command;

import java.util.List;

public interface CommandHandler {

    String handleCommand(String message);

    void registerCommand(Command command, List<String> aliases);


}
