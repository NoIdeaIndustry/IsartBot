package com.noideaindustry.isartbot.utils;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.awt.*;

public class ConstantsUtils {
    public static final String ICON = "https://cdn.discordapp.com/attachments/1003678231788007616/1056979272234963034/noldea-bot-logo.png";
    public static final Gson GSON = new Gson();
    public static Color PRIMARY_COLOR = Color.decode("#0C1B3A");

    private static final Dotenv config = Dotenv.load();

    public static String getToken() {
        return config.get("BOT_TOKEN");
    }
    public static String getRedisPort() {
        return config.get("REDIS_PORT");
    }
}
