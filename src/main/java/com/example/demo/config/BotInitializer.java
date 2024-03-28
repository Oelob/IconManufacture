package com.example.demo.config;

import com.example.demo.service.TelegremBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j

public class BotInitializer {
    @Autowired
    TelegremBot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotApi.registerBot(bot);
        } catch (TelegramApiException e) {
            log.error("Error:" + e.getMessage());
        }
    }
}
