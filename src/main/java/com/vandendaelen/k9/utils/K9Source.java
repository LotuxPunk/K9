package com.vandendaelen.k9.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.io.IOUtils;

import java.net.URL;
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

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entity) {
        return new TextComponentTranslation(message, entity.getName(), getUserName(owner.toString()));
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

    public String getUserName(String uuid) {
        String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";
        try {
            String json = IOUtils.toString(new URL(url));
            JsonElement element = new JsonParser().parse(json);
            JsonArray nameArray = element.getAsJsonArray();
            JsonObject nameElement = nameArray.get(nameArray.size() - 1).getAsJsonObject();
            nameElement.get("name").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}