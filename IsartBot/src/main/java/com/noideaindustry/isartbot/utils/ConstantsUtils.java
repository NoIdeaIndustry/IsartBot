package com.noideaindustry.isartbot.utils;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ConstantsUtils {
    public static final String ICON = "https://cdn.discordapp.com/attachments/1067440038247411712/1067440420893753354/isart.png";
    public static final Gson GSON = new Gson();
    public static Color PRIMARY_COLOR = Color.decode("#0C1B3A");
    public static final SimpleDateFormat ISO_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat DATE = new SimpleDateFormat("EEE dd MMM");
    public static String formatDate(final Date date) {
        final String newDate = DATE.format(date);
        return newDate.replaceAll("\\.", "");
    }
    private static final Dotenv config = Dotenv.load();
    public static String getToken() {
        return config.get("BOT_TOKEN");
    }
    public static String getRedisPort() {
        return config.get("REDIS_PORT");
    }
}
