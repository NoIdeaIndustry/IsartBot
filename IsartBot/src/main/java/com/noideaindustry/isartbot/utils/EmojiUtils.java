package com.noideaindustry.isartbot.utils;

import net.dv8tion.jda.api.entities.emoji.Emoji;

public enum EmojiUtils {
    BLANK("U+2800"),
    SPARKLES("U+2728"),
    BOOM("U+1F4A5"),
    COUVERT("U+1F37D"),
    CHICKEN("U+1F414"),
    ALARM_CLOCK("U+23F0"),
    FLOWING_HOURGLASS("U+23F3"),
    PIZZA("U+1F355"),
    BURGER("U+1F354"),
    BREAD("U+1FAD3"),
    HOT_DOG("U+1F32D"),
    FISH("U+1F41F"),
    WINK("U+1F609");

    private final Emoji emoji;
    private final String unicode;
    EmojiUtils(final String unicode) {
        this.unicode = unicode;
        this.emoji = Emoji.fromFormatted(unicode);
    }

    public String getUnicode() {
        return unicode;
    }

    public Emoji getEmoji() {
        return this.emoji;
    }

    public String getDisplay() {
        return this.getDisplay(true);
    }

    public String getDisplay(final boolean displaySpace) {
        return this.getEmoji().getName() + (displaySpace ? BLANK.getEmoji().getName() : "");
    }
}
