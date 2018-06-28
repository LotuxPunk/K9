package com.vandendaelen.k9.utils.condition;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;

import java.util.function.BooleanSupplier;

public class NotTARDISCondition implements IConditionFactory {

    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        return new BooleanSupplier() {

            @Override
            public boolean getAsBoolean() {
                return !(Loader.isModLoaded("tardis"));
            }};
    }
}
