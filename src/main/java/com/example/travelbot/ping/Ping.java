package com.example.travelbot.ping;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Special app invoker for Heroku free plan.
 * He prevents sleep mode for the app after 2 min inactive.
 */
@Service
@Slf4j
@Getter
public class Ping {

    @Value("${pingTask.url}")
    private String pingTaskUrl;

    @Scheduled(fixedRateString = "${pingTask.period}")
    public void ping() {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(pingTaskUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            log.info("ping {}, OK: response code {}", url.getHost(), connection.getResponseCode());
        } catch (IOException e) {
            log.error("Ping FAILED");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
