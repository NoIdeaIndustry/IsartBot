package com.noideaindustry.isartbot;

import com.noideaindustry.isartbot.commands.SlashCommandListener;
import com.noideaindustry.isartbot.utils.ConstantsUtils;
import com.noideaindustry.isartbot.utils.EmojiUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class IsartBot {
    private static JDA jda;

    public IsartBot() {
        System.out.println("[System - IsartBot] Starting program...\n");

        try {
            final JDABuilder builder = JDABuilder.create(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.SCHEDULED_EVENTS);
            builder.setToken(ConstantsUtils.getToken());
            builder.disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS, CacheFlag.VOICE_STATE);
            builder.setActivity(Activity.watching(String.format("Isart Students %s", EmojiUtils.WINK.getDisplay(false))));

            jda = builder.build().awaitReady();
            jda.addEventListener(new SlashCommandListener(jda));

            System.out.println("[System - IsartBot] Successfully started program!\n");
        } catch (Exception e) {
            System.out.println("[System - IsartBot] Error while starting program " + e + " !");
            System.exit(1);
        }
    }

    public static JDA getJda() {
        return jda;
    }
}
