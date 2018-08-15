package com.vandendaelen.k9.packets;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.utils.Reference;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRemoteOpenGUI implements IMessage {

    private static int k9ID;

    public MessageRemoteOpenGUI() {
    }

    public MessageRemoteOpenGUI(int k9ID) {
        this.k9ID = k9ID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        k9ID = pBuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer pBuf = new PacketBuffer(buf);
        pBuf.writeInt(k9ID);
    }

    public static class Handler implements IMessageHandler<MessageRemoteOpenGUI, IMessage> {

        public Handler() {
            System.out.println("Instanciate");
        }

        @Override
        public IMessage onMessage(MessageRemoteOpenGUI message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    player.openGui(K9.instance, Reference.GUI_ID_REMOTE,player.world,message.k9ID,0,0);
                }
            });
            return null;
        }
    }
}
