package com.noideaindustry.isartbot.commands.utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface ISlashCommand {

    default String getName() {
        return this.getClass().getSimpleName().toLowerCase().replaceAll("slashcommand", "");
    }

    String getDescription();

    default List<RSlashArgument> getArgs() {
        return new ArrayList<>();
    }

    void handle(final SlashCommandInteractionEvent event);

    default Collection<Permission> permissionsNeeded() {
        return Collections.emptyList();
    }

    default ListenerAdapter getListener() {
        return null;
    }
}
