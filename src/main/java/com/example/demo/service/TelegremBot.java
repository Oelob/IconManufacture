package com.example.demo.service;

import com.example.demo.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@Slf4j
public class TelegremBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegremBot(BotConfig config){
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if ( update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();
            switch (messageText){

                case ("/start"):
                    startCommandReceived(chatID, update.getMessage().getChat().getFirstName());
                    break;
                default: sendMessage(chatID,  "извините, не работает");
            }
        }


    }

    private void startCommandReceived(long chatID, String name){
        String answer = "Hi " + name + "!";
        sendMessage(chatID, answer);
        log.info("Replied to user " + name);
    }

    private void sendMessage (long chatID, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(textToSend);

        try {
            execute(message);
        }
        catch (TelegramApiException e){
            log.error("Error:" + e.getMessage());
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
