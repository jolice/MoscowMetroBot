package com.github.jolice.metro;

import com.github.jolice.metro.message.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MetroBot extends TelegramLongPollingBot {

    private Logger logger = LoggerFactory.getLogger(MetroBot.class);

    private MessageHandler messageHandler;

    @Autowired
    private Environment environment;

    @Autowired
    public MetroBot(@Qualifier("Core") MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logReceived(update);
        String response = messageHandler.handleMessage(update.getMessage());
        logger.info("Response: '{}'", response);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(response);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Failed to send message ", e);
        }
    }

    private void logReceived(Update update) {
        Message message = update.getMessage();
        User from = message.getFrom();
        logger.info("Received message: [from: [ID={}, Full name={} {}, Username: {}], body: '{}', message]", from.getId(), from.getFirstName(), from.getLastName(), from.getUserName(), message.getText());
    }

    @Override
    public String getBotUsername() {
        return environment.getProperty("bot.username");
    }

    @Override
    public String getBotToken() {
        return environment.getProperty("bot.token");
    }
}
