package com.kazireno.hetogteu.mixins;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTank;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.tileentity.TileEntityProxyBase;
import com.hbm.tileentity.machine.storage.TileEntityBarrel;
import com.hbm.tileentity.machine.storage.TileEntityMachineBigAssTank;
import com.hbm.tileentity.machine.storage.TileEntityMachineFluidTank;
import com.hbm.tileentity.machine.storage.TileEntityMachineOrbus;
import com.kazireno.hetogteu.utils.FluidMapping;

@Mixin(value = TileEntityBarrel.class, remap = false)
@Implements(@Interface(iface = IFluidHandler.class, prefix = "hetogteu$", remap = Interface.Remap.NONE))
public abstract class MixinHBMTankReceiver extends TileEntityMachineBase {

    @Shadow(remap = false)
    public FluidTank tank;

    @Shadow(remap = false)
    public short mode;

    public MixinHBMTankReceiver(int slotCount) {
        super(slotCount);
    }

    @Unique
    private boolean canAcceptInput() {
        return this.mode == 0 || this.mode == 1;
    }

    @Unique
    private boolean canProvideOutput() {
        return this.mode == 1 || this.mode == 2;
    }

    @Unique
    private boolean isBroken() {
        return (Object) this instanceof TileEntityMachineFluidTank
            && ((TileEntityMachineFluidTank) (Object) this).hasExploded;
    }

    // -------- 端口坐标计算 --------
    @Unique
    private List<int[]> computePortPositions() {
        List<int[]> list = new ArrayList<>();
        int meta = this.getBlockMetadata();

        if ((Object) this instanceof TileEntityMachineFluidTank) {
            // 4 端口：y 层斜对角 ±1
            ForgeDirection dir = ForgeDirection.getOrientation(meta - 10);
            ForgeDirection rot = dir.getRotation(ForgeDirection.UP);
            list.add(new int[] { xCoord + dir.offsetX + rot.offsetX, yCoord, zCoord + dir.offsetZ + rot.offsetZ });
            list.add(new int[] { xCoord + dir.offsetX - rot.offsetX, yCoord, zCoord + dir.offsetZ - rot.offsetZ });
            list.add(new int[] { xCoord - dir.offsetX - rot.offsetX, yCoord, zCoord - dir.offsetZ - rot.offsetZ });
            list.add(new int[] { xCoord - dir.offsetX + rot.offsetX, yCoord, zCoord - dir.offsetZ + rot.offsetZ });
        } else if ((Object) this instanceof TileEntityMachineBigAssTank) {
            // 2 端口：y 层沿朝向 ±6
            ForgeDirection dir = ForgeDirection.getOrientation(meta - 10);
            list.add(new int[] { xCoord + dir.offsetX * 6, yCoord, zCoord + dir.offsetZ * 6 });
            list.add(new int[] { xCoord - dir.offsetX * 6, yCoord, zCoord - dir.offsetZ * 6 });
        } else if ((Object) this instanceof TileEntityMachineOrbus) {
            // 8 端口：底部 4 + 顶部 y+4 层 4
            ForgeDirection dir = ForgeDirection.getOrientation(meta - 10)
                .getOpposite();
            ForgeDirection rot = dir.getRotation(ForgeDirection.DOWN);
            list.add(new int[] { xCoord, yCoord, zCoord });
            list.add(new int[] { xCoord + dir.offsetX, yCoord, zCoord + dir.offsetZ });
            list.add(new int[] { xCoord + rot.offsetX, yCoord, zCoord + rot.offsetZ });
            list.add(new int[] { xCoord + dir.offsetX + rot.offsetX, yCoord, zCoord + dir.offsetZ + rot.offsetZ });
            list.add(new int[] { xCoord, yCoord + 4, zCoord });
            list.add(new int[] { xCoord + dir.offsetX, yCoord + 4, zCoord + dir.offsetZ });
            list.add(new int[] { xCoord + rot.offsetX, yCoord + 4, zCoord + rot.offsetZ });
            list.add(new int[] { xCoord + dir.offsetX + rot.offsetX, yCoord + 4, zCoord + dir.offsetZ + rot.offsetZ });
        }
        return list;
    }

    @Unique
    private boolean isOwnProxy(TileEntity te) {
        return te instanceof TileEntityProxyBase && ((TileEntityProxyBase) te).getTE() == this;
    }

    @Unique
    private void tryOutputFrom(int px, int py, int pz) {
        FluidType hbmType = this.tank.getTankType();
        Fluid forgeFluid = FluidMapping.toForge(hbmType);
        if (forgeFluid == null) return;

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity neighbor = this.worldObj.getTileEntity(px + dir.offsetX, py + dir.offsetY, pz + dir.offsetZ);

            if (neighbor == null || neighbor == this) continue;
            if (isOwnProxy(neighbor)) continue; // 跳过属于自己多方块的代理
            if (!(neighbor instanceof IFluidHandler)) continue;

            IFluidHandler handler = (IFluidHandler) neighbor;
            FluidStack toOffer = new FluidStack(forgeFluid, this.tank.getFill());
            int accepted = handler.fill(dir.getOpposite(), toOffer, false);
            if (accepted > 0) {
                int toSend = Math.min(accepted, this.tank.getFill());
                handler.fill(dir.getOpposite(), new FluidStack(forgeFluid, toSend), true);
                this.tank.setFill(this.tank.getFill() - toSend);
                if (this.tank.getFill() <= 0) return;
            }
        }
    }

    /*
     * 开发环境采用updateEntity
     * 编译环境采用func_145845_h
     * 沃不明白.jpg，为什么混淆映射在这个上失效了（
     */
    // @Inject(method = "updateEntity", at = @At("TAIL"), remap = false)
    @Inject(method = "func_145845_h", at = @At("TAIL"), remap = false)
    private void onUpdate(CallbackInfo ci) {
        if (this.mode != 1 && this.mode != 2) return;
        if (isBroken()) return;
        if (this.tank.getFill() <= 0) return;

        // 1) 核心位置（单方块 Barrel / Orbus 底部端口正好在核心）
        tryOutputFrom(this.xCoord, this.yCoord, this.zCoord);
        if (this.tank.getFill() <= 0) return;

        // 2) 多方块：从每个端口坐标出发找邻居
        for (int[] p : computePortPositions()) {
            int px = p[0], py = p[1], pz = p[2];
            if (px == this.xCoord && py == this.yCoord && pz == this.zCoord) continue;

            TileEntity te = this.worldObj.getTileEntity(px, py, pz);
            if (!isOwnProxy(te)) continue; // 必须属于本核心的代理

            tryOutputFrom(px, py, pz);
            if (this.tank.getFill() <= 0) return;
        }
    }

    // -------- IFluidHandler实现 --------
    @Unique
    public int hetogteu$fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (isBroken()) return 0;
        if (resource == null || resource.getFluid() == null) return 0;
        if (!canAcceptInput()) return 0;

        FluidType hbmType = FluidMapping.toHbm(resource.getFluid());
        if (hbmType == null) return 0;

        FluidType currentType = this.tank.getTankType();
        boolean empty = currentType == Fluids.NONE || this.tank.getFill() <= 0;
        if (!empty && currentType != hbmType) return 0;

        int space = this.tank.getMaxFill() - this.tank.getFill();
        int toFill = Math.min(resource.amount, space);
        if (toFill <= 0) return 0;

        if (doFill) {
            if (empty && currentType != hbmType) {
                this.tank.setTankType(hbmType);
            }
            this.tank.setFill(this.tank.getFill() + toFill);
        }
        return toFill;
    }

    @Unique
    public FluidStack hetogteu$drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null) return null;
        if (!canProvideOutput()) return null;

        FluidType currentType = this.tank.getTankType();
        if (currentType == Fluids.NONE) return null;

        Fluid forgeFluid = FluidMapping.toForge(currentType);
        if (forgeFluid == null || resource.getFluid() != forgeFluid) return null;

        return hetogteu$drain(from, resource.amount, doDrain);
    }

    @Unique
    public FluidStack hetogteu$drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (maxDrain <= 0) return null;
        if (!canProvideOutput()) return null;

        FluidType currentType = this.tank.getTankType();
        if (currentType == Fluids.NONE) return null;

        Fluid forgeFluid = FluidMapping.toForge(currentType);
        if (forgeFluid == null) return null;

        int toDrain = Math.min(maxDrain, this.tank.getFill());
        if (toDrain <= 0) return null;

        if (doDrain) {
            this.tank.setFill(this.tank.getFill() - toDrain);
        }
        return new FluidStack(forgeFluid, toDrain);
    }

    @Unique
    public boolean hetogteu$canFill(ForgeDirection from, Fluid fluid) {
        if (isBroken()) return false; // canFill
        if (fluid == null) return false;
        if (!canAcceptInput()) return false;

        FluidType hbmType = FluidMapping.toHbm(fluid);
        if (hbmType == null) return false;

        FluidType currentType = this.tank.getTankType();
        boolean empty = currentType == Fluids.NONE || this.tank.getFill() <= 0;
        if (!empty && currentType != hbmType) return false;

        return this.tank.getFill() < this.tank.getMaxFill();
    }

    @Unique
    public boolean hetogteu$canDrain(ForgeDirection from, Fluid fluid) {
        if (fluid == null) return false;
        if (!canProvideOutput()) return false;

        FluidType currentType = this.tank.getTankType();
        if (currentType == Fluids.NONE) return false;

        Fluid forgeFluid = FluidMapping.toForge(currentType);
        return forgeFluid != null && forgeFluid == fluid && this.tank.getFill() > 0;
    }

    @Unique
    public FluidTankInfo[] hetogteu$getTankInfo(ForgeDirection from) {
        FluidType currentType = this.tank.getTankType();
        Fluid forgeFluid = (currentType == Fluids.NONE) ? null : FluidMapping.toForge(currentType);

        FluidStack stack = null;
        if (forgeFluid != null) {
            stack = new FluidStack(forgeFluid, this.tank.getFill());
        }
        // 必须返回长度为 1 的数组，管道才会认为这个容器有效（
        return new FluidTankInfo[] { new FluidTankInfo(stack, this.tank.getMaxFill()) };
    }
}
