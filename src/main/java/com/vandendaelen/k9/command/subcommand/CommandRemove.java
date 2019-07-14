package com.vandendaelen.k9.command.subcommand;


import com.vandendaelen.k9.command.K9Command;
import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.utils.helpers.K9Helper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.UUID;

public class CommandRemove extends CommandTemplate {

    public CommandRemove(K9Command parent) {
        super(parent);
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Entity player = sender.getCommandSenderEntity();
        if (player instanceof EntityPlayer){
            UUID playerid = player.getUniqueID();
            if (K9Helper.hasK9(playerid)){
                UUID k9id = K9Helper.getK9(playerid);
                EntityK9 k9 = (EntityK9) server.getEntityFromUuid(k9id);
                if (k9 != null)
                    k9.setDead();
                K9Helper.removeK9(playerid, k9id);
                sender.sendMessage(new TextComponentString("K-9 removed"));
            }
            else throw new CommandException("You don't own a K9");
        }
        else throw new CommandException("You must execute this command as a player");
    }
}
