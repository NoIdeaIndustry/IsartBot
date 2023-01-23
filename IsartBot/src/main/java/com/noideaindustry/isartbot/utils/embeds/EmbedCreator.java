package com.noideaindustry.isartbot.utils.embeds;

import com.noideaindustry.isartbot.utils.ConstantsUtils;
import net.dv8tion.jda.api.EmbedBuilder;

import java.time.Instant;

public class EmbedCreator {
    public static EmbedBuilder createEmbed(final String title, final String description, final EmbedField... fields) {
        final EmbedBuilder embed = new EmbedBuilder().setColor(ConstantsUtils.PRIMARY_COLOR);

        if(title != null) {
            embed.setTitle(title);
        }

        if(description != null) {
            embed.setDescription(description);
        }

        if(fields != null && fields[0] != null) {
            for(final var field : fields) {
                createField(embed, field.getPrimaryValue(), field.getSecondValue(), field.isSameLine());
            }
        }

        embed.setFooter("IsartBot", ConstantsUtils.ICON).setTimestamp(Instant.now());

        return embed;
    }

    public static void createField(final EmbedBuilder embed, final String firstValue, final String secondValue) {
        embed.addField(firstValue, " • " + secondValue, false);
    }

    public static void createField(final EmbedBuilder embed, final String firstValue, final String secondValue, final boolean sameLine) {
        embed.addField(firstValue, " • " + secondValue, sameLine);
    }
}
