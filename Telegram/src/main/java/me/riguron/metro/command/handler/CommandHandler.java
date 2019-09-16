package me.riguron.metro.command.handler;

import me.riguron.metro.command.Command;

import java.util.List;

public interface CommandHandler {

    String handleCommand(String message);

    void registerCommand(Command command, List<String> aliases);


}
