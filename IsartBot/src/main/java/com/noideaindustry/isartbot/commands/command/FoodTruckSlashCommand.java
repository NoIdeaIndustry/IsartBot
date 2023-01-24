package com.noideaindustry.isartbot.commands.command;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.noideaindustry.isartbot.commands.utils.ISlashCommand;
import com.noideaindustry.isartbot.models.FoodTruck;
import com.noideaindustry.isartbot.models.FoodType;
import com.noideaindustry.isartbot.utils.ConstantsUtils;
import com.noideaindustry.isartbot.utils.EmojiUtils;
import com.noideaindustry.isartbot.utils.JsonUtils;
import com.noideaindustry.isartbot.utils.StringUtils;
import com.noideaindustry.isartbot.utils.embeds.EmbedCreator;
import com.noideaindustry.isartbot.utils.embeds.EmbedField;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

public class FoodTruckSlashCommand implements ISlashCommand {
    @Override
    public String getDescription() {
        return "Gets the current food truck schedule.";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        final List<FoodTruck> foodTrucks = new ArrayList<>();
        try {
            final HttpURLConnection connection = (HttpURLConnection) new URL("https://my.isartdigital.com/api/foodtruck").openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Content-Type", "application/json");

            final int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                final StringBuilder response = new StringBuilder();

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                final JsonArray jsonArray = (JsonArray) JsonParser.parseString(response.toString());
                for (int i = 0; i < jsonArray.size(); i++) {
                    final JsonUtils jsonUtils = new JsonUtils(jsonArray.get(i).getAsJsonObject());

                    final FoodTruck foodTruck = new FoodTruck(
                            ConstantsUtils.ISO_DATE.parse(jsonUtils.getMember("jour")),
                            jsonUtils.getMember("nom").toUpperCase(Locale.ROOT),
                            jsonUtils.getMember("description"),
                            jsonUtils.getMember("lien")
                    );
                    foodTrucks.add(foodTruck);
                }

                foodTrucks.sort(Comparator.comparing(FoodTruck::getDate));

                final List<EmbedField> embedFields = new ArrayList<>();
                for (final FoodTruck foodTruck : foodTrucks) {
                    final WeekFields weekFields = WeekFields.of(Locale.getDefault());
                    final int firstWeek = LocalDate.now().get(weekFields.weekOfWeekBasedYear());
                    if (foodTruck.getDate().getDay() <= firstWeek) {
                        embedFields.add(new EmbedField(
                                String.format("%s %s *(%s)*", foodTruck.getType().getEmoji().getDisplay(), foodTruck.getName(), foodTruck.getType().getKey()),
                                String.format("`%s` | [Facebook](%s).", StringUtils.capitalize(ConstantsUtils.formatDate(foodTruck.getDate())), foodTruck.getSocial()),
                                false,
                                true
                        ));
                    }
                }

                if (embedFields.size() > 1) {
                    embedFields.add(0, new EmbedField("", String.format("%s**Today's Truck**", EmojiUtils.ALARM_CLOCK.getDisplay()), false, false));
                    embedFields.add(2, new EmbedField("", String.format("%s**Upcoming Trucks**", EmojiUtils.FLOWING_HOURGLASS.getDisplay()), false, false));
                }

                final EmbedBuilder embed = EmbedCreator.createEmbed(
                        String.format("%sFood trucks list", EmojiUtils.COUVERT.getDisplay()),
                        "",
                        embedFields.toArray(EmbedField[]::new)
                );

                event.replyEmbeds(embed.build()).queue();
            }
        } catch (Exception ignored) {
        }
    }
}
