package com.example.travelbot;

import com.example.travelbot.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * My travel telegram bot.
 *
 * @author n.logvinova
 */
public class TravelTelegramBot extends TelegramWebhookBot {

    private static final String NO_INFO = "Нет информации";
    private static final String START = "/start";
    private static final String PRINT_CITY = "О каком городе хотите узнать?";

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

    /**
     * Telegram chat handling. A telegram bot user sends request with some city name
     * and gets response with info about this city or message NO_INFO, if such city is not present in DB.
     *
     * @param update
     * @return
     */
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            String inputMessage = update.getMessage().getText();
            String responseMessage;

            if (START.equals(inputMessage)) {
                responseMessage = PRINT_CITY;
            } else {
                String cityInfo = cityInfoService.findInfoByCityIgnoreCase(inputMessage);
                if (ObjectUtils.isEmpty(cityInfo)) {
                    responseMessage = NO_INFO;
                } else {
                    responseMessage = cityInfo;
                }
            }

            try {
                execute(new SendMessage(Long.toString(chat_id), responseMessage));
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
