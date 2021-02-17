package com.example.travelbot.appconfig;


import com.example.travelbot.TravelTelegramBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {

    private String webHookPath;

    private String botUserName;

    private String botToken;

    @Bean
    public TravelTelegramBot travelTelegramBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        TravelTelegramBot travelTelegramBot = new TravelTelegramBot(options);
        travelTelegramBot.setBotUserName(botUserName);
        travelTelegramBot.setBotToken(botToken);
        travelTelegramBot.setWebHookPath(webHookPath);

        return travelTelegramBot;
    }
}
