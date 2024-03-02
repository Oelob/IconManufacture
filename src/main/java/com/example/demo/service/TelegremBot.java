package com.example.demo.service;

import com.example.demo.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class TelegremBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegremBot(BotConfig config){
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();

            switch (messageText){

                case ("/start"):

            }
        }

    }


    @Override
    public String getBotUsername() {
        return config.getBot_name();
    }

    public String getBotToken(){
        return config.getToken();
    }
}
