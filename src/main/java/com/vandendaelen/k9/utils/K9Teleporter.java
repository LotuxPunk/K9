package com.vandendaelen.k9.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class K9Teleporter extends Teleporter {

    public K9Teleporter(WorldServer worldIn) {
        super(worldIn);
    }

    public static boolean teleportDimEntity(Entity entity, BlockPos pos, int targetDim) {
        if (entity.getEntityWorld().isRemote || entity.isRiding() || entity.isBeingRidden() || !entity.isEntityAlive()) {
            return false;
        }
        int from = entity.dimension;
        if (from != targetDim) {
            MinecraftServer server = entity.getServer();
            WorldServer fromDim = server.getWorld(from);
            WorldServer toDim = server.getWorld(targetDim);
            Teleporter teleporter = new K9Teleporter(toDim);

            NBTTagCompound tagCompound = entity.serializeNBT();
            float rotationYaw = entity.rotationYaw;
            float rotationPitch = entity.rotationPitch;
            fromDim.removeEntity(entity);
            Entity newEntity = EntityList.createEntityFromNBT(tagCompound, toDim);

            if (newEntity != null) {
                newEntity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), rotationYaw, rotationPitch);
                newEntity.forceSpawn = true;
                toDim.spawnEntity(newEntity);
                newEntity.forceSpawn = false;
            } else {
                return false;
            }
        }
        if (!entity.world.isBlockLoaded(pos)) {
            entity.world.getChunkFromBlockCoords(pos);
        }
        entity.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D);
        entity.fallDistance = 0;
        return true;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {}

    @Override
    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
        return true;
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return true;
    }

    @Override
    public void removeStalePortalLocations(long worldTime) {}
}
