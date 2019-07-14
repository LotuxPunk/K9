package com.vandendaelen.k9.command;

import com.vandendaelen.k9.command.subcommand.CommandRemove;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;

import java.text.MessageFormat;

public class K9Command extends CommandTreeBase {
    public K9Command() {
        addSubcommand(new CommandRemove(this));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getName() {
        return "k9";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        StringBuilder usageString = new StringBuilder();
        usageString.append("/"+getName()+" ");
        for (ICommand subcommand : getSubCommands()) {
            usageString.append(MessageFormat.format("{0} | ", subcommand.getName()));
        }
        return usageString.toString();
    }
}
