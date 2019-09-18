package io.riguron.metro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:bot.properties")
public class BotConfiguration {

    static {
        ApiContextInitializer.init();
    }

    private MetroBot metroBot;

    @Autowired
    public BotConfiguration(MetroBot metroBot) {
        this.metroBot = metroBot;
    }

    @PostConstruct
    public void initializeContext() {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(metroBot);
        } catch (TelegramApiException e) {
            throw new IllegalStateException(e);
        }
    }
}