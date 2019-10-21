package com.vandendaelen.k9.entities;

import com.vandendaelen.k9.K9;
import com.vandendaelen.k9.objects.items.ItemK9Remote;
import com.vandendaelen.k9.utils.K9Source;
import com.vandendaelen.k9.utils.K9Strings;
import com.vandendaelen.k9.utils.Reference;
import com.vandendaelen.k9.utils.handlers.SoundHandler;
import com.vandendaelen.k9.utils.helpers.K9Helper;
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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.UUID;


public class EntityK9 extends EntityWolf implements IRangedAttackMob, IEnergyStorage {

    private static final DataParameter<Integer> BATTERY = EntityDataManager.createKey(EntityK9.class,DataSerializers.VARINT);
    private static final DataParameter<Boolean> IS_MARK_II = EntityDataManager.createKey(EntityK9.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> MODE = EntityDataManager.createKey(EntityK9.class,DataSerializers.VARINT);
    
    private final EntityAINearestAttackableTarget<EntityLivingBase> full_mode = new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, false);
    private final EntityAINearestAttackableTarget<EntityMob> mob_mode = new EntityAINearestAttackableTarget<>(this, EntityMob.class, false);

    public static final int INVENTORY_SIZE = 9;

    private final int ENERGY_MAX = 100000;
    private final int ENERGY_MIN = 0;
    private final int ENERGY_LOW = ENERGY_MAX / 100 * 20;
    private final int ENERGY_RAY_CONSUMPTION = 1500;

    private final int REDSTONE_ENERGY_RESTORE = 8000;

    public EntityK9(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
        applyK9Mode(MODE.getId());
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
        this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIBeg(this, 8.0F));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
    }

    private void applyK9Mode(int mode){
        switch (mode){
            case 1:
                this.targetTasks.addTask(1,mob_mode);
                break;
            case 2:
                this.targetTasks.removeTask(mob_mode);
                this.targetTasks.addTask(1,full_mode);
                break;
            default:
                this.targetTasks.removeTask(full_mode);
                break;
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(BATTERY,0);
        this.dataManager.register(IS_MARK_II, rand.nextInt(10) >= 5);
        this.dataManager.register(MODE,1);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        UUID ownerID = getOwnerId();

        if (player.getUniqueID().equals(ownerID)){
            if (player.getHeldItem(hand).getItem() instanceof ItemRedstone && this.canReceive(REDSTONE_ENERGY_RESTORE)) {
                player.getHeldItem(hand).shrink(1);
                addEnergy(REDSTONE_ENERGY_RESTORE);
                return true;
            }

            if (player.getHeldItem(hand).getItem() instanceof ItemK9Remote){
                ItemK9Remote remote = (ItemK9Remote) player.getHeldItem(hand).getItem();
                remote.setK9ID(player.getHeldItem(hand), this.getUniqueID());
                K9.logger.debug(remote.getK9ID(player.getHeldItem(hand)));
                return true;
            }

            if (player.getUniqueID().equals(ownerID)) {
                player.openGui(K9.instance, Reference.GUI_ID_CONTAINER, world, this.getEntityId(), 0, 0);
                return true;
            }
        }

        PlayerHelper.sendMessage(player, new TextComponentTranslation(K9Strings.K9.NOT_OWNER).getUnformattedComponentText(), true);
        return false;
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
   //     if(getBattery() >= ENERGY_RAY_CONSUMPTION){
    //        removeEnergy(ENERGY_RAY_CONSUMPTION);
            //look = target.getPositionVector().subtract(this.getPositionVector());
            EntityLaserRay laser = new EntityLaserRay(world, this, 8, new K9Source("K9"),new Vec3d(0,1,0));
            laser.setThrowerId(getEntityId());
            x = posX + this.getLookVec().x;
            y = posY + this.getEyeHeight();
            z = posZ + this.getLookVec().z;

            laser.shoot(x, y, z, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
            this.world.spawnEntity(laser);

            world.playSound(null,getPosition(),SoundHandler.ENTITY_K9_LASER_SHOOT,SoundCategory.HOSTILE,1F,1F);
      //  }
    //    else {
            //Todo Add a sound when K9 can't shoot
    //    }

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
        if (compound.hasKey("mark2")){
            setMarkII(compound.getBoolean("mark2"));
        }
        if (compound.hasKey("mode")){
            setMode(compound.getInteger("mode"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);
        compound.setInteger("energy",getBattery());
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setBoolean("mark2",isMarkII());
        compound.setInteger("mode",getMode());
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

    public boolean isMarkII(){
        return this.dataManager.get(IS_MARK_II);
    }

    public void setMarkII(boolean value){
        this.dataManager.set(IS_MARK_II,value);
    }

    public int getMode(){
        return this.dataManager.get(MODE);
    }

    public void setMode(int value){
        this.dataManager.set(MODE,value);
        applyK9Mode(value);
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

    @Override
    public void onDeath(DamageSource p_onDeath_1_) {
        K9Helper.removeK9(getOwnerId(),getUniqueID());
        super.onDeath(p_onDeath_1_);
    }
}
