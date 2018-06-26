package com.vandendaelen.k9.entities;

import com.vandendaelen.k9.utils.handlers.SoundHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class EntityK9 extends EntityWolf implements IRangedAttackMob {
    public EntityK9(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
    }

    @Override
    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackRanged(this, 0.1D, 30, 25F));
        this.targetTasks.addTask(4, new EntityAIMoveTowardsTarget(this, 0.023D, 30));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIBeg(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    }

    @Override
    public EntityWolf createChild(EntityAgeable ageable) {
        return null;

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundHandler.ENTITY_K9_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundHandler.ENTITY_K9_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundHandler.ENTITY_K9_DEATH;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        double x, y, z;
        Vec3d look = target.getPositionVector().subtract(this.getPositionVector());
        EntityK9Ray ray = new EntityK9Ray(world,this);

        x = posX+this.getLookVec().x;
        y = posY + this.getEyeHeight();
        z = posZ + this.getLookVec().z;

        ray.setPosition(x,y,z);
        world.spawnEntity(ray);

        //Todo Add sound on fire
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }
}
