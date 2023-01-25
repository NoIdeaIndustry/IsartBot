package com.noideaindustry.isartbot.commands;

import com.noideaindustry.isartbot.notifications.StaredNotification;
import com.noideaindustry.isartbot.notifications.UnstaredNotification;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
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

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        StaredNotification.notify(event);
        System.out.println("Emoji added");
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        UnstaredNotification.notify(event);
        System.out.println("Emoji removed");
    }
}
