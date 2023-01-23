package com.noideaindustry.isartbot.models;

import com.noideaindustry.isartbot.utils.EmojiUtils;

public enum FoodType {
    PIZZA("Pizza", EmojiUtils.PIZZA),
    BURGER("Burger", EmojiUtils.BURGER),
    GALETTE_BRETONNE("Galette Bretonne", EmojiUtils.BREAD),
    HOT_DOG("Hot Dog", EmojiUtils.HOT_DOG),
    FISH_CHIPS("Fish and Chips", EmojiUtils.FISH);

    private final String key;
    private final EmojiUtils emoji;

    FoodType(final String key, final EmojiUtils emoji) {
        this.key = key;
        this.emoji = emoji;
    }

    public String getKey() {
        return key;
    }

    public EmojiUtils getEmoji() {
        return emoji;
    }
}
