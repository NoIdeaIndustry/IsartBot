package com.noideaindustry.isartbot.models;

import com.noideaindustry.isartbot.utils.EmojiUtils;

public enum FoodType {
    UNDEFINED("Undefined", EmojiUtils.COUVERT),
    PIZZA("Pizza", EmojiUtils.PIZZA),
    POULET("Poulet", EmojiUtils.CHICKEN),
    BURGER("Burger", EmojiUtils.BURGER),
    GALETTE_BRETONNE("Galette Bretonne", EmojiUtils.BREAD),
    HOT_DOG("Hot Dog", EmojiUtils.HOT_DOG),
    FISH_CHIPS("Fish & Chips", EmojiUtils.FISH);

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

    public static FoodType getTypeFromId(final String id) {
        final String lowerId = id.toLowerCase();
        if(lowerId.contains("pizza")) return FoodType.PIZZA;
        else if(lowerId.contains("poulet")) return FoodType.POULET;
        else if(lowerId.contains("chips")) return FoodType.FISH_CHIPS;
        else if(lowerId.contains("dog")) return FoodType.HOT_DOG;
        else if(lowerId.contains("bretonne")) return FoodType.GALETTE_BRETONNE;
        else if(lowerId.contains("burger")) return FoodType.BURGER;
        else return FoodType.UNDEFINED;
    }
}
