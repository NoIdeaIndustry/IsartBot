package com.noideaindustry.isartbot.commands;

import com.noideaindustry.isartbot.commands.utils.ISlashCommand;
import com.noideaindustry.isartbot.commands.utils.RSlashArgument;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashCommandHandler {
    private final Map<String, ISlashCommand> slashCommands = new HashMap<>();

    public SlashCommandHandler() {

    }

    private void addSlashCommand(final ISlashCommand slashCommand) {
        if (!this.slashCommands.containsKey(slashCommand.getName())) {
            this.slashCommands.put(slashCommand.getName(), slashCommand);
        }
    }

    public void handleSlashCommand(final SlashCommandInteractionEvent event) {
        final String invoke = event.getName();

        if (this.slashCommands.containsKey(invoke)) {
            final ISlashCommand slashCommand = getSlashCommand(invoke);
            if (!event.getMember().hasPermission(slashCommand.permissionsNeeded())) {
                return;
            }

            try {
                slashCommand.handle(event);
            } catch (Exception ignored) {
            }
        }
    }

    public void registerSlashCommands(final JDA jda) {
        System.out.println("SlashCommand registering...");
        final List<CommandData> commands = new ArrayList<>();

        this.getSlashCommands().forEach((name, slashCommand) -> {
            if (slashCommand.getArgs().isEmpty()) {
                commands.add(Commands.slash(slashCommand.getName(), slashCommand.getDescription()));
                System.out.printf(" - Command: \"/%s\"\n", slashCommand.getName());
            } else {
                final SlashCommandData command = Commands.slash(slashCommand.getName(), slashCommand.getDescription());
                final List<OptionData> options = new ArrayList<>();
                for (final RSlashArgument arg : slashCommand.getArgs()) {
                    options.add(new OptionData(arg.type(), arg.name(), arg.description(), arg.required()));
                }
                command.addOptions(options);
                commands.add(command);
                System.out.printf(" - Command: \"/%s\" with %d arguments\n", slashCommand.getName(), slashCommand.getArgs().size());
            }
        });

        jda.updateCommands().addCommands(commands).queue();
    }

    public ISlashCommand getSlashCommand(final String name) {
        return this.slashCommands.get(name);
    }

    public Map<String, ISlashCommand> getSlashCommands() {
        return this.slashCommands;
    }
}
