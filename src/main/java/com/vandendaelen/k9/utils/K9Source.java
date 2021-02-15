package com.vandendaelen.k9.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

public class K9Source extends DamageSource {

    private String message;
    private boolean blockable;
    private UUID owner;

    public K9Source(String name, boolean blockable) {
        super(name);
        this.message = "damagesrc."+Reference.MODID+"." + name;
        this.blockable = blockable;
    }

    public K9Source(String name) {
        this(name, false);
    }

    public static String getUserName(UUID uuid) {
        if (FMLCommonHandler.instance().getMinecraftServerInstance() == null || uuid == null) return "Unknown Timelord";
        PlayerProfileCache cache = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerProfileCache();
        GameProfile profile = cache.getProfileByUUID(uuid);
        if (profile != null) {
            return profile.getName();
        }
        return "Unknown Timelord";
    }

    public UUID getOwner() {
        return owner;
    }

    public K9Source setOwner(UUID owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public boolean isUnblockable() {
        return !blockable;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entity) {
        return new TextComponentTranslation(message, entity.getName(), getUserName(owner));
    }

}