package com.vandendaelen.k9.world.gen;

import com.vandendaelen.k9.objects.blocks.BlockOres;
import com.vandendaelen.k9.utils.handlers.EnumHandler;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator {
    private WorldGenerator ore_overworld_plaurium;

    public WorldGenCustomOres() {
        //this.ore_overworld_plaurium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.PLAURIUM),3,BlockMatcher.forBlock(Blocks.STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()){
            case -1:
                break;
            case 0:
                runGenerator(ore_overworld_plaurium,world,random,chunkX,chunkZ,10,0,40);
                break;
            case 1:
                break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight){
        if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256){
            throw new IllegalArgumentException("Ore generated out of bounds");
        }

        int heightDiff = maxHeight - minHeight +1;

        for (int i = 0; i < chance; i++){
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world,rand,new BlockPos(x,y,z));
        }

    }
}
