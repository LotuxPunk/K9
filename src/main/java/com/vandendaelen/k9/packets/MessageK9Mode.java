package com.vandendaelen.k9.packets;

import com.vandendaelen.k9.entities.EntityK9;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class MessageK9Mode implements IMessage {

    private int mode;
    private UUID k9ID;

    public MessageK9Mode() {
    }

    public MessageK9Mode(int mode, UUID k9ID) {
        this.mode = mode;
        this.k9ID = k9ID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        mode = pBuf.readInt();
        k9ID = pBuf.readUniqueId();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        pBuf.writeInt(mode);
        pBuf.writeUniqueId(k9ID);
    }

    public static class Handler implements IMessageHandler<MessageK9Mode, IMessage> {
        @Override
        public IMessage onMessage(MessageK9Mode message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    EntityK9 k9 = (EntityK9) ctx.getServerHandler().player.getServerWorld().getEntityFromUuid(message.k9ID);
                    k9.setMode(message.mode);
                }
            });
            return null;
        }
    }
}
