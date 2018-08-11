package com.vandendaelen.k9.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageK9Piloting implements IMessage {

    private static BlockPos destination= BlockPos.ORIGIN;
    private static BlockPos tardisBP = BlockPos.ORIGIN;
    private static int dimension = 0;

    public MessageK9Piloting() {
    }

    public MessageK9Piloting(BlockPos destination, int dim, BlockPos tBP) {
        this.destination = destination.toImmutable();
        this.dimension = dim;
        this.tardisBP = tBP.toImmutable();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        destination = BlockPos.fromLong(buf.readLong());
        tardisBP = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(destination.toLong());
        buf.writeLong(tardisBP.toLong());
        buf.writeInt(dimension);
    }

    public static class Handler implements IMessageHandler<MessageK9Piloting, IMessage>{
        @Override
        public IMessage onMessage(MessageK9Piloting message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    WorldServer ws = DimensionManager.getWorld(TDimensions.TARDIS_ID);

                    if (ws.isBlockLoaded(tardisBP))) {
                        TileEntity te = ws.getTileEntity(tardisBP);
                        if (te instanceof TileEntityTardis){
                            ((TileEntityTardis) te).setDesination(destination,dimension);
                        }
                    }
                }
            });
            return null;
        }
    }
}
