package com.noideaindustry.isartbot.commands.command;

import com.noideaindustry.app.Application;
import com.noideaindustry.isartbot.commands.utils.ISlashCommand;
import com.noideaindustry.isartbot.commands.utils.RSlashArgument;
import com.noideaindustry.isartbot.utils.EmojiUtils;
import com.noideaindustry.isartbot.utils.embeds.EmbedCreator;
import com.noideaindustry.isartbot.utils.embeds.EmbedField;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StarboardSlashCommand implements ISlashCommand {
    @Override
    public String getDescription() {
        return "Sets the current starboard channel, if none was set before, this command also enables the starboard!";
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        final Guild guild = event.getGuild();
        final List<OptionMapping> commandOptions = event.getOptions();

        final String channelId = commandOptions.get(0).getAsString();
        final TextChannel starboardChannel = guild.getTextChannelById(channelId);

        if (starboardChannel == null) {
            final EmbedBuilder embed = EmbedCreator.createEmbed(
                    String.format("%s%s", EmojiUtils.BOOM.getDisplay(), "Selected channel does not exist in current guild..."),
                    "Please select an existing and valid channel, with access permissions for the bot!"
            );
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            return;
        }

        final boolean wasExecuted = Application.getInstance().getIsartBot().getRedisUtils().setStarboardChannel(guild.getId(), channelId);
        final EmbedBuilder embed = EmbedCreator.createEmbed(
                String.format("%s", wasExecuted ?
                        String.format("%s%s \"*%s*\"", EmojiUtils.SPARKLES.getDisplay(), "Successfully set starboard channel to", starboardChannel.getName()) :
                        String.format("%s%s", EmojiUtils.BOOM.getDisplay(), "Failed setting starboard channel!")
                )
        );

        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
    }

    @Override
    public Collection<Permission> permissionsNeeded() {
        return Collections.singleton(Permission.ADMINISTRATOR);
    }

    @Override
    public List<RSlashArgument> getArgs() {
        final List<RSlashArgument> argsList = new ArrayList<>();

        argsList.add(new RSlashArgument(OptionType.STRING, "channel", "The channel id of the starboard.", true));

        return argsList;
    }
}
