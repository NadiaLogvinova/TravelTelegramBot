package com.example.travelbot;

import com.example.travelbot.service.CityInfoService;
import com.example.travelbot.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TravelTelegramBot extends TelegramWebhookBot {

    private static final String NO_INFO = "Нет информации";

    private String webHookPath;
    private String botUserName;
    private String botToken;

    @Autowired
    private CityInfoService cityInfoService;


    public TravelTelegramBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            String city = update.getMessage().getText();

            String info = cityInfoService.findInfoByCityIgnoreCase(city);
            if (StringUtil.isStringEmpty(info)) info = NO_INFO;

            try {
                execute(new SendMessage(Long.toString(chat_id), info));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

}
