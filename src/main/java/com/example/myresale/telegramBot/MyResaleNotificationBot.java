package com.example.myresale.telegramBot;

import com.example.myresale.entities.TelegramBotUser;
import com.example.myresale.repositories.TelegramBotUserRepository;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

public class MyResaleNotificationBot extends TelegramLongPollingBot implements NotificationBot{
    private final Logger logger = Logger.getLogger(MyResaleNotificationBot.class.getName());
    @Autowired
    private TelegramBotUserRepository botUserRepository;
    private final String botToken;
    private final String botName;

    public MyResaleNotificationBot(String botToken, String botName) {
        this.botToken = botToken;
        this.botName = botName;
    }

    public void sendTextByChatId(Long chatId, String message){
        SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(message).build();
        try{
            execute(sendMessage);
        } catch (TelegramApiException ex){
            logger.info(ex.getMessage());
        }

    }

    public void sendTextToAllUsers(String message){
        var botUsers = botUserRepository.findAll();
        botUsers.forEach(user -> sendTextByChatId(user.getChatId(), message));
    }

    @Override
    public String getBotUsername(){
        return botName;
    }

    @Override
    public String getBotToken(){
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().getText().equals("/start")){
            var chatId = update.getMessage().getChat().getId();
            var fromUser = update.getMessage().getFrom();

            sendTextByChatId(chatId, "Welcome to the chat: " + fromUser.getFirstName() +"!");
            sendTextByChatId(chatId, "Here you will receive notifications about new items added to the site!");

            logger.info("New telegram bot user with chatID: " + chatId);

            var botUser = TelegramBotUser
                    .builder()
                    .id(fromUser.getId())
                    .username(fromUser.getUserName())
                    .chatId(chatId)
                    .build();

            botUserRepository.save(botUser);
        }
    }

}
