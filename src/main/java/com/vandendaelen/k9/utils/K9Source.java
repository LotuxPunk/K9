package com.vandendaelen.k9.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class K9Source extends DamageSource {

    private String message;
    private boolean blockable;

    public K9Source(String name, boolean blockable) {
        super(name);
        this.message = "damagesrc."+Reference.MODID+"." + name;
        this.blockable = blockable;
    }

    public K9Source(String name) {
        this(name, false);
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entity) {
        return new TextComponentTranslation(message, entity.getName());
    }

    @Override
    public boolean isUnblockable() {
        return !blockable;
    }

}