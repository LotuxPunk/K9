package com.vandendaelen.k9.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//Mainly inspired by https://github.com/Spectre0987/TardisMod/blob/master/src/main/java/net/tardis/mod/common/entities/EntityDalekRay.java

public class EntityK9Ray extends EntityThrowable {
    public static final float SPEED = 2F;

    public EntityK9Ray(World worldIn) {
        super(worldIn);
    }

    public EntityK9Ray(World worldIn, EntityLivingBase base) {
        super(worldIn, base);
        this.shoot(base, base.rotationPitch,base.rotationYaw,0,SPEED,0);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result != null && result.entityHit instanceof EntityLivingBase) {
            result.entityHit.attackEntityFrom(this.getThrower() != null ? DamageSource.causeIndirectDamage(this, getThrower()) : DamageSource.FIREWORKS, 10F);
        }
        if (result.typeOfHit == result.typeOfHit.BLOCK) this.setDead();
    }

    @Override
    protected float getGravityVelocity() {
        return 0.000F;
    }
}