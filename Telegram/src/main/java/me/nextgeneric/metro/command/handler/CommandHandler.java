package me.nextgeneric.metro.command.handler;

import me.nextgeneric.metro.command.Command;

import java.util.List;

public interface CommandHandler {

    String handleCommand(String message);

    void registerCommand(Command command, List<String> aliases);


}
