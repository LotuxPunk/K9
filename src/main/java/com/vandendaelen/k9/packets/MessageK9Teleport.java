package com.vandendaelen.k9.packets;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.utils.K9Teleporter;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

public class MessageK9Teleport implements IMessage {

    private static UUID uuid;

    public MessageK9Teleport() {
    }

    public MessageK9Teleport(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        uuid = pBuf.readUniqueId();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        pBuf.writeUniqueId(uuid);
    }

    public static class Handler implements IMessageHandler<MessageK9Teleport, IMessage> {
        @Override
        public IMessage onMessage(MessageK9Teleport message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    EntityPlayerMP player= ctx.getServerHandler().player;
                    EntityK9 k9 = (EntityK9)player.getServerWorld().getEntityFromUuid(message.uuid);
                    if (k9 != null) K9Teleporter.move(k9, player.dimension, player.getPosition());
                }
            });
            return null;
        }
    }
}
