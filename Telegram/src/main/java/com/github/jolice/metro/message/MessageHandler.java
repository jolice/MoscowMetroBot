package com.github.jolice.metro.message;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {

    String handleMessage(Message message);
}
