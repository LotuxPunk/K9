package com.vandendaelen.k9.command.subcommand;

import com.vandendaelen.k9.command.K9Command;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public abstract class CommandTemplate extends CommandBase {
    private K9Command parent;

    public CommandTemplate(K9Command parent) {
        this.parent = parent;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + parent.getName() + " " + getName();
    }
}
