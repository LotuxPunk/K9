package com.vandendaelen.k9.entity;

import com.vandendaelen.k9.utils.handlers.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityK9 extends EntityWolf {
    public EntityK9(World worldIn) {
        super(worldIn);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundHandler.ENTITY_K9_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundHandler.ENTITY_K9_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundHandler.ENTITY_K9_DEATH;
    }
}
