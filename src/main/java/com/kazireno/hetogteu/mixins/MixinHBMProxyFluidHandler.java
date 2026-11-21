package com.kazireno.hetogteu.mixins;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.hbm.tileentity.TileEntityProxyCombo;

@Mixin(value = TileEntityProxyCombo.class, remap = false)
@Implements(@Interface(iface = IFluidHandler.class, prefix = "hetogteu$", remap = Interface.Remap.NONE))
public abstract class MixinHBMProxyFluidHandler {

    // 目标类中的包私有字段，用来判断这个代理方块是否代理流体
    @Shadow(remap = false)
    boolean fluid;

    // getCoreObject() 是 protected，用 @Invoker 调用最稳妥
    @Invoker("getCoreObject")
    protected abstract Object invokeGetCoreObject();

    // 辅助方法：不加 hetogteu$ 前缀，避免被 @Implements 误匹配
    @Unique
    private IFluidHandler resolveFluidHandler() {
        if (!this.fluid) return null;
        Object core = invokeGetCoreObject();
        return core instanceof IFluidHandler ? (IFluidHandler) core : null;
    }

    @Unique
    public int hetogteu$fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        IFluidHandler h = resolveFluidHandler();
        return h == null ? 0 : h.fill(from, resource, doFill);
    }

    @Unique
    public FluidStack hetogteu$drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        IFluidHandler h = resolveFluidHandler();
        return h == null ? null : h.drain(from, resource, doDrain);
    }

    @Unique
    public FluidStack hetogteu$drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        IFluidHandler h = resolveFluidHandler();
        return h == null ? null : h.drain(from, maxDrain, doDrain);
    }

    @Unique
    public boolean hetogteu$canFill(ForgeDirection from, Fluid fluid) {
        IFluidHandler h = resolveFluidHandler();
        return h != null && h.canFill(from, fluid);
    }

    @Unique
    public boolean hetogteu$canDrain(ForgeDirection from, Fluid fluid) {
        IFluidHandler h = resolveFluidHandler();
        return h != null && h.canDrain(from, fluid);
    }

    @Unique
    public FluidTankInfo[] hetogteu$getTankInfo(ForgeDirection from) {
        IFluidHandler h = resolveFluidHandler();
        return h == null ? new FluidTankInfo[0] : h.getTankInfo(from);
    }
}
