package com.kazireno.hetogteu.mixins;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.missile.EntityMissileAntiBallistic;
import com.hbm.entity.missile.EntityMissileBaseNT;
import com.hbm.entity.missile.EntityMissileStealth;
import com.hbm.entity.projectile.EntityMeteor;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.tileentity.machine.TileEntityMachineRadarNT;

@Mixin(value = EntityMissileAntiBallistic.class, remap = false)
public abstract class MixinHBMMissileAntiBallisticChanges extends Entity {

    @Shadow(remap = false)
    public Entity tracking;

    public MixinHBMMissileAntiBallisticChanges(World worldIn) {
        super(worldIn);
    }

    @Inject(method = "targetMissile", at = @At("HEAD"), cancellable = true)
    private void hetogteu$targetMissilesAndMeteors(CallbackInfo ci) {
        Entity closest = null;
        double dist = 1000.0D;

        for (Entity e : TileEntityMachineRadarNT.matchingEntities) {
            if (e.dimension != this.dimension) continue;

            boolean isTarget = (e instanceof EntityMissileBaseNT && !(e instanceof EntityMissileStealth))
                || e instanceof EntityMeteor;
            if (!isTarget) continue;

            Vec3 vec = Vec3.createVectorHelper(e.posX - this.posX, e.posY - this.posY, e.posZ - this.posZ);
            double d = vec.lengthVector();
            if (d < dist) {
                closest = e;
                dist = d; // 更新列表，让导弹锁定实际最近的
            }
        }

        this.tracking = closest;
        ci.cancel();
    }

    @Inject(method = "aimAtTarget", at = @At("TAIL"))
    private void hetogteu$killMeteorOnIntercept(CallbackInfo ci) {
        if (this.tracking == null || this.tracking.isDead || !(this.tracking instanceof EntityMeteor)) return;
        double dx = this.tracking.posX - this.posX;
        double dy = this.tracking.posY - this.posY;
        double dz = this.tracking.posZ - this.posZ;
        if (dx * dx + dy * dy + dz * dz < 100.0D) { // 绕过陨石伤害来源判断
            ExplosionLarge.spawnShrapnelShower(
                this.tracking.worldObj,
                this.tracking.posX,
                this.tracking.posY,
                this.tracking.posZ,
                this.tracking.motionX,
                this.tracking.motionY,
                this.tracking.motionZ,
                25,
                0.075);
            MeteoriteFirework(this.worldObj, this.tracking.posX, this.tracking.posY, this.tracking.posZ);
            this.tracking.setDead();
        }
    }

    @Unique
    // 陨石雨，用于替代防空导弹击毁陨石的掉落
    public void MeteoriteFirework(World world, double x, double y, double z) {
        Block[] blockpool = new Block[] { ModBlocks.block_meteor, ModBlocks.block_meteor_broken,
            ModBlocks.block_meteor_molten, ModBlocks.block_meteor_treasure, ModBlocks.ore_meteor };
        Random rand = world.rand;

        int count = rand.nextInt(37) + 27;
        for (int i = 0; i < count; i++) {

            double theta = rand.nextDouble() * Math.PI * 2.0;
            double cosPhi = 2.0 * rand.nextDouble() - 1.0; // [-1, 1]
            double sinPhi = Math.sqrt(1.0 - cosPhi * cosPhi);

            double dirX = sinPhi * Math.cos(theta);
            double dirY = cosPhi;
            double dirZ = sinPhi * Math.sin(theta);

            double dist = 0.5 + rand.nextDouble() * 2.5;

            double spawnX = x + dirX * dist;
            double spawnY = y + Math.abs(dirY) * dist * 0.6 + 1.5; // 只往上偏，避免入地
            double spawnZ = z + dirZ * dist;

            Block block = blockpool[rand.nextInt(blockpool.length)];

            EntityFallingBlock fallingBlock = new EntityFallingBlock(
                world,
                spawnX,
                spawnY,
                spawnZ,
                block,
                rand.nextInt(4));

            // 让方块向外飞散
            double speed = 0.15 + rand.nextDouble() * 0.35;
            fallingBlock.motionX = dirX * speed;
            fallingBlock.motionY = 0.25 + rand.nextDouble() * 0.35; // 统一向上再落下
            fallingBlock.motionZ = dirZ * speed;

            // 重置生命周期
            fallingBlock.field_145812_b = 1;

            world.spawnEntityInWorld(fallingBlock);
        }
    }
}
