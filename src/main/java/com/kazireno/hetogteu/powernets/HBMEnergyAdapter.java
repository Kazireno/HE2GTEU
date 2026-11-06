package com.kazireno.hetogteu.powernets;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import api.hbm.energymk2.IEnergyConductorMK2;
import api.hbm.energymk2.IEnergyReceiverMK2;
import api.hbm.energymk2.Nodespace;
import api.hbm.energymk2.PowerNetMK2;
import gregtech.api.metatileentity.BaseMetaTileEntity;

/// HBM能量节点
public class HBMEnergyAdapter {

    // 绑定的方块实体
    private final BaseMetaTileEntity owner;
    // 记录已注册的网络
    private PowerNetMK2 registeredNet = null;

    public HBMEnergyAdapter(BaseMetaTileEntity owner) {
        this.owner = owner;
    }

    public void tick() {
        if (owner.getWorldObj() == null || owner.getWorldObj().isRemote) return;

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int nx = owner.xCoord + dir.offsetX;
            int ny = owner.yCoord + dir.offsetY;
            int nz = owner.zCoord + dir.offsetZ;

            TileEntity neighbor = owner.getWorldObj()
                .getTileEntity(nx, ny, nz);
            if (!(neighbor instanceof IEnergyConductorMK2 con)) continue;
            if (!con.canConnect(dir.getOpposite())) continue;

            Nodespace.PowerNode node = Nodespace.getNode(owner.getWorldObj(), nx, ny, nz);
            if (node == null || node.net == null || !node.net.isValid()) continue;

            PowerNetMK2 net = node.net;
            if (net != registeredNet) {
                net.addReceiver((IEnergyReceiverMK2) (Object) owner);
                registeredNet = net;
            }
        }
    }

    public void onUnload() {
        if (registeredNet != null) {
            registeredNet.removeReceiver((IEnergyReceiverMK2) (Object) owner);
            registeredNet = null;
        }
    }
}
