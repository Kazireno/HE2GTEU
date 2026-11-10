package com.kazireno.hetogteu.mixins;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kazireno.hetogteu.powernets.HBMEnergyAdapter;

import api.hbm.energymk2.IEnergyReceiverMK2;
import gregtech.api.metatileentity.BaseMetaTileEntity;

@Mixin(value = BaseMetaTileEntity.class, remap = false)
@Implements(@Interface(iface = IEnergyReceiverMK2.class, prefix = "hbm$", remap = Interface.Remap.NONE))
public abstract class MixinGTEnergyReceiver {

    @Shadow(remap = false)
    public abstract long getStoredEU();

    @Shadow(remap = false)
    public abstract long getEUCapacity();

    @Shadow(remap = false)
    public abstract boolean setStoredEU(long aEnergy);

    @Shadow(remap = false)
    public abstract boolean isEnetInput();

    @Shadow(remap = false)
    public abstract boolean isEnetOutput();

    // 在GT机器中维护一个HBM网络节点实例
    @Unique
    private HBMEnergyAdapter hetogteu$hbmAdapter;

    // 构造函数初始化
    @Inject(method = "<init>*", at = @At("TAIL"), require = 0)
    private void hetogteu$init(CallbackInfo ci) {
        this.hetogteu$hbmAdapter = new HBMEnergyAdapter((BaseMetaTileEntity) (Object) this);
    }

    // 每tick驱动适配器
    @Inject(method = "updateEntityProfiled", at = @At("TAIL"), require = 0)
    private void hetogteu$tick(CallbackInfo ci) {
        if (hetogteu$hbmAdapter != null) hetogteu$hbmAdapter.tick();
    }

    @Unique
    public void hbm$setPower(long power) {
        long eu = power / 20L;
        if (eu < 0) eu = 0;
        long cap = this.getEUCapacity();
        if (eu > cap) eu = cap;
        this.setStoredEU(eu);
    }

    @Unique
    public long hbm$getPower() {
        if (this.isEnetOutput() && !this.isEnetInput()) return 0L;
        return this.getStoredEU() * 20L;
    }

    @Unique
    public long hbm$getMaxPower() {
        if (this.isEnetOutput() && !this.isEnetInput()) return 0L;
        return this.getEUCapacity() * 20L;
    }

    @Unique
    public boolean hbm$isLoaded() {
        return this.getEUCapacity() > 0;
    }

    @Unique
    public long hbm$getReceiverSpeed() {
        return Long.MAX_VALUE;
    }
}
