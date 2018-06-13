package com.vandendaelen.k9.gen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class K9OreGen implements IWorldGenerator {

    private WorldGenerator plaurtium_ore;

    public K9OreGen(){
        //plaurtium_ore = new WorldGenMinable();
        //TODO Generation des minerais
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

    }
}
