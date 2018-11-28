package com.vandendaelen.k9.utils;

public class TSource extends DamageSource {

    private String message;
    private boolean blockable;

    public TSource(String name, boolean blockable) {
        super(name);
        this.message = "damagesrc."+Reference.MODID+"." + name;
        this.blockable = blockable;
    }

    public TSource(String name) {
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