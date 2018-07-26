package com.vandendaelen.k9.entities;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.handlers.SoundHandler;
import com.vandendaelen.k9.utils.helpers.PlayerHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRedstone;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.UUID;


public class EntityK9 extends EntityWolf implements IRangedAttackMob, IEnergyStorage {

    private static final DataParameter<Integer> BATTERY = EntityDataManager.createKey(EntityK9.class,DataSerializers.VARINT);

    public static final int INVENTORY_SIZE = 9;

    private final int ENERGY_MAX = 100000;
    private final int ENERGY_MIN = 0;
    private final int ENERGY_LOW = ENERGY_MAX / 100 * 20;
    private final int ENERGY_RAY_CONSUMPTION = 1500;

    private final int REDSTONE_ENERGY_RESTORE = 8000;

    public EntityK9(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
    }

    @Override
    protected void initEntityAI() {
        setPathPriority(PathNodeType.WATER, -1.0F);
        setAIMoveSpeed(0.7F);
        setMoveForward(0);
        enablePersistence();

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackRanged(this, 0.5D, 10, 25F));
        this.targetTasks.addTask(4, new EntityAIMoveTowardsTarget(this, 1.0D, 20));
        this.tasks.addTask(3, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIBeg(this, 8.0F));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, false));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(BATTERY,0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
        UUID ownerID = getOwnerId();

        if(player.getHeldItem(hand).getItem() instanceof ItemRedstone && this.canReceive(REDSTONE_ENERGY_RESTORE)){
            int stock = player.getHeldItem(hand).getCount();
            player.getHeldItem(hand).setCount(--stock);
            addEnergy(REDSTONE_ENERGY_RESTORE);
            return EnumActionResult.SUCCESS;
        }
        else if(player.getUniqueID().equals(ownerID)){
            player.openGui(K9.instance, Reference.GUI_ID_CONTAINER, world, this.getEntityId(), 0, 0);
            return EnumActionResult.SUCCESS;
        }

        PlayerHelper.sendMessage(player,"Isn't your K9 !",true);
        return EnumActionResult.FAIL;
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        final PathNavigateGround navigator = new PathNavigateGround(this, worldIn);
        navigator.setCanSwim(true);
        return navigator;
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
        //Vec3d look;
        if(getBattery() >= ENERGY_RAY_CONSUMPTION){
            removeEnergy(ENERGY_RAY_CONSUMPTION);
            //look = target.getPositionVector().subtract(this.getPositionVector());
            EntityK9Ray ray = new EntityK9Ray(world,this);

            x = posX + this.getLookVec().x;
            y = posY + this.getEyeHeight();
            z = posZ + this.getLookVec().z;

            ray.setPosition(x,y,z);
            world.spawnEntity(ray);

            world.playSound(null,getPosition(),SoundHandler.ENTITY_K9_LASER_SHOOT,SoundCategory.HOSTILE,1F,1F);
        }
        else {
            //Todo Add a sound when K9 can't shoot
        }

    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    
    //Inventory functions

    private ItemStackHandler itemStackHandler = new ItemStackHandler(INVENTORY_SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
        if (compound.hasKey("energy")){
            setBattery(compound.getInteger("energy"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);
        compound.setInteger("energy",getBattery());
        compound.setTag("items", itemStackHandler.serializeNBT());
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(this.getPosition()) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        return super.getCapability(capability, facing);

    }

    //Energy functions

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (ENERGY_MAX - getBattery() >= maxReceive) {
            return maxReceive;
        }
        return ENERGY_MAX - getBattery();
    }

    @Override
    public int getMaxEnergyStored() {
        return ENERGY_MAX;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return getBattery();
    }

    @Override
    public boolean canReceive() {
        return getBattery() < ENERGY_MAX;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    public boolean canReceive(int value) {
        return getBattery() + value < ENERGY_MAX;
    }

    public int getLevelEnergy(){
        double level = (double)getBattery() / ENERGY_MAX;
        return (int)Math.floor(level * 100);
    }

    public void addEnergy(int value){
        setBattery(getBattery() + value);
    }

    public void removeEnergy(int value){
        setBattery(getBattery() - value);
    }

    public void setBattery(int value){
        this.dataManager.set(BATTERY, value);
    }

    public int getBattery(){
        return this.dataManager.get(BATTERY);
    }
}
