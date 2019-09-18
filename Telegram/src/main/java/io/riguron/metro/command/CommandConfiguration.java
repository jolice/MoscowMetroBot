package io.riguron.metro.command;

import io.riguron.metro.command.handler.CommandHandler;
import io.riguron.metro.command.handler.PlainCommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfiguration {

    @Bean
    public CommandHandler commandHandler(ApplicationContext context) {
        CommandHandler commandHandler = new PlainCommandHandler();
        context.getBeansOfType(Command.class).forEach((s, command) -> commandHandler.registerCommand(command, command.aliases()));
        return commandHandler;
    }
}

