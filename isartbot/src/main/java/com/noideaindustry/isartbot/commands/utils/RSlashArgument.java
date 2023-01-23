package com.noideaindustry.isartbot.commands.utils;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public record RSlashArgument(OptionType type, String name, String description, boolean required) {
}
