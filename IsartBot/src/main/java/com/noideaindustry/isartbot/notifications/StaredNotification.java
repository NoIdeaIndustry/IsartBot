package com.noideaindustry.isartbot.notifications;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class StaredNotification {
    public static void notify(final MessageReactionAddEvent event) {
        final Emoji reaction = event.getEmoji();
        final boolean isStarCode = reaction.getAsReactionCode().equals(":star:");

        if(isStarCode) {
            System.out.println("YES");
        }
    }
}
