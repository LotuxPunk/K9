package com.vandendaelen.k9.objects.items;

import com.vandendaelen.k9.entities.EntityK9;
import com.vandendaelen.k9.utils.helpers.K9Helper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;

public class ItemK9Spawner<E extends EntityK9> extends ItemBase {
    private Function<World, E> entityCreator;

    public ItemK9Spawner(String name, Function<World, E> entityCreator) {
        super(name);
        this.entityCreator = entityCreator;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {
            if (!K9Helper.hasK9(player.getUniqueID())){
                EntityK9 entity = entityCreator.apply(worldIn);
                entity.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                worldIn.spawnEntity(entity);

                entity.setTamedBy(player);
                K9Helper.addK9(player.getUniqueID(), entity.getUniqueID());

                player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
                return EnumActionResult.SUCCESS;
            }
            //TODO : Send a message to say NO
            return EnumActionResult.PASS;

        }
        return EnumActionResult.PASS;
    }


}
