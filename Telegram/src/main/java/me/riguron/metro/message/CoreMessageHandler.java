package me.riguron.metro.message;

import me.riguron.metro.command.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("Core")
public class CoreMessageHandler implements MessageHandler {

    private CommandHandler commandHandler;

    private MessageHandler messageHandler;

    @Autowired
    public CoreMessageHandler(CommandHandler commandHandler, @Qualifier("Application") MessageHandler messageHandler) {
        this.commandHandler = commandHandler;
        this.messageHandler = messageHandler;
    }

    @Override
    public String handleMessage(Message message) {
        String body = message.getText();
        if (body != null && body.startsWith("/")) {
            return commandHandler.handleCommand(body);
        } else {
            return messageHandler.handleMessage(message);
        }
    }
}
