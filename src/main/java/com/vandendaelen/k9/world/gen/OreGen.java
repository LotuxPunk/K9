package com.vandendaelen.k9.world.gen;

import com.vandendaelen.k9.init.K9Blocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;
import java.util.function.Predicate;

public class OreGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()) {
            //Nether
            case -1:
                break;
            //Overworld
            case 0:
                runGenerator(K9Blocks.ore_plaurium.getDefaultState(), 4, 12, 10, 30, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
                break;
            //End
            case 1:
                break;
            //Everything else
            default:
                runGenerator(K9Blocks.ore_plaurium.getDefaultState(), 4, 12, 10, 30, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
                break;
        }
    }

    private void runGenerator(IBlockState blockToGen, int blockAmount, int chancesToSpawn, int minHeight, int maxHeight, Predicate<IBlockState> blockToReplace, World world, Random rand, int chunk_X, int chunk_Z){
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, (com.google.common.base.Predicate<IBlockState>) blockToReplace);
        int heightdiff = maxHeight - minHeight +1;
        for (int i=0; i<chancesToSpawn; i++){
            int x = chunk_X * 16 +rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightdiff);
            int z = chunk_Z * 16 + rand.nextInt(16);

            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
