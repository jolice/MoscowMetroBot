package io.riguron.metro.command.handler;

import io.riguron.metro.command.Command;

import java.util.List;

public interface CommandHandler {

    String handleCommand(String message);

    void registerCommand(Command command, List<String> aliases);


}
