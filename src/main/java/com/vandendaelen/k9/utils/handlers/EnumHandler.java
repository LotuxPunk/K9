package com.vandendaelen.k9.utils.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
    public static enum EnumType implements IStringSerializable{
        PLAURIUM(0,"plaurium");

        private static final EnumType[] META_LOCKUP = new EnumType[values().length];
        private final int meta;
        private final String name, unlocalizedName;

        EnumType(int meta, String name, String unlocalizedName) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        EnumType(int meta, String name) {
            this(meta, name,name);
        }

        @Override
        public String getName() {
            return name;
        }

        public int getMeta() {
            return meta;
        }

        public String getUnlocalizedName() {
            return unlocalizedName;
        }

        @Override
        public String toString() {
            return name;
        }

        public static EnumType byMetadata(int meta){
            return META_LOCKUP[meta];
        }

        static {
            for(EnumType enumtype:values()){
                META_LOCKUP[enumtype.getMeta()]=enumtype;
            }
        }
    }
}
