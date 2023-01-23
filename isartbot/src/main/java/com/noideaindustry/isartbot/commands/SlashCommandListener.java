package com.noideaindustry.isartbot.commands;

import com.noideaindustry.app.Application;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {
    private final SlashCommandHandler slashCommandHandler;

    public SlashCommandListener(final JDA jda) {
        this.slashCommandHandler = new SlashCommandHandler();
        this.slashCommandHandler.registerSlashCommands(jda);

        System.out.println("SlashListener registering...");
        for (final String commandName : this.slashCommandHandler.getSlashCommands().keySet()) {
            final ListenerAdapter commandListener = this.slashCommandHandler.getSlashCommand(commandName).getListener();
            if (commandListener == null) continue;

            jda.addEventListener(commandListener);
            System.out.printf(" - Listener: \"%s\"\n", commandListener.getClass().getSimpleName());
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;

        this.slashCommandHandler.handleSlashCommand(event);
    }
}
