package com.noideaindustry.isartbot.commands.command;

import com.google.gson.JsonObject;
import com.noideaindustry.app.Application;
import com.noideaindustry.isartbot.commands.utils.ISlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class FoodTruckSlashCommand implements ISlashCommand {
    @Override
    public String getDescription() {
        return "Gets the current food truck schedule.";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {

        try {
            final JsonObject jsonObject = Application.getInstance().getIsartBot().getRedisUtils().getFoodTrucks();

            for (final String key : jsonObject.keySet()) {
                if (jsonObject.get(key) instanceof JsonObject) {

                }
            }
        } catch (Exception ignored) {

        }
    }
}
