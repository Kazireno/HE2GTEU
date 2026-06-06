package com.kazireno.hetogteu.tile;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

import api.hbm.energymk2.IEnergyReceiverMK2;
import gregtech.api.enums.GTValues;
import gregtech.api.interfaces.tileentity.IBasicEnergyContainer;
import gregtech.api.interfaces.tileentity.IEnergyConnected;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.common.data.GTBlockEventTracker;
import io.netty.buffer.ByteBuf;

public class TileEntityHeToEu extends TileEntity implements IEnergyReceiverMK2, IBasicEnergyContainer {

    // <---------------GT5 EU--------------->
    public int tier; // 电压等级
    public long amperage;
    public long euStored = 0L; // 储存的EU
    public long _euGet = 0;

    public long euMaxStore() {
        return GTValues.V[tier] * 20L * 30L;
    } // 提供30秒的输出缓存
      // <---------------HBM HE--------------->

    public long heStored; // 储存的HE
    public long _heCost = 0;

    public long heMaxStore() {
        return GTValues.V[tier] * 20L * 20L * 30L;
    } // 提供30秒消耗的缓存

    private boolean isLoaded = true;
    private ByteBuf lastPackedBuf;
    public int ticker;

    public static String GetVString(int tier) {
        return switch (tier) {
            case 0 -> "ULV";
            case 1 -> "LV";
            case 2 -> "MV";
            case 3 -> "HV";
            case 4 -> "EV";
            case 5 -> "IV";
            case 6 -> "LuV";
            case 7 -> "ZPM";
            case 8 -> "UV";
            case 9 -> "UHV";
            case 10 -> "UEV";
            case 11 -> "UIV";
            case 12 -> "UMV";
            case 13 -> "UXV";
            case 14 -> "MAX";
            default -> "ERR";
        };
    }

    public TileEntityHeToEu(int Tier, long Amperage) {
        this.tier = Tier;
        this.amperage = Amperage;
    }

    public TileEntityHeToEu() {}

    @Override
    public long getPower() {
        return heStored;
    }

    @Override
    public void setPower(long l) {
        this.heStored = l;
    }

    @Override
    public long getMaxPower() {
        return heMaxStore();
    }

    @Override
    public boolean isLoaded() {
        return this.isLoaded;
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        this.isLoaded = false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (euStored > GTValues.V[tier] * amperage) {
            euStored -= IEnergyConnected.Util.emitEnergyToNetwork(GTValues.V[tier], amperage, this) * GTValues.V[tier];
        }

        _heCost = 0;
        _euGet = 0;
        if (heStored >= 20 & this.ticker >= 5) {
            if (euStored < euMaxStore()) {
                _euGet = Math.min(euMaxStore() - euStored, heStored / 20);
                _heCost = _euGet * 20;
                heStored -= _heCost;
                euStored += _euGet;
            }
            ticker = 0;
        }

        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            this.trySubscribe(
                this.worldObj,
                this.xCoord + dir.offsetX,
                this.yCoord + dir.offsetY,
                this.zCoord + dir.offsetZ,
                dir);
        }
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        ticker++;
        this.markDirty();
    }

    //

    @Override
    public long injectEnergyUnits(ForgeDirection side, long aVoltage, long aAmperage) {
        return 0;
    }

    @Override
    public boolean inputEnergyFrom(ForgeDirection side) {
        return false;
    }

    @Override
    public boolean outputsEnergyTo(ForgeDirection side) {
        return true;
    }

    @Override
    public byte getColorization() {
        return 0;
    }

    @Override
    public byte setColorization(byte aColor) {
        return 0;
    }

    @Override
    public boolean isUniversalEnergyStored(long aEnergyAmount) {
        return true;
    }

    @Override
    public long getUniversalEnergyStored() {
        return euStored;
    }

    @Override
    public long getUniversalEnergyCapacity() {
        return euMaxStore();
    }

    @Override
    public long getOutputAmperage() {
        return amperage;
    }

    @Override
    public long getOutputVoltage() {
        return GTValues.V[tier];
    }

    @Override
    public long getInputAmperage() {
        return 0;
    }

    @Override
    public long getInputVoltage() {
        return 0;
    }

    @Override
    public boolean decreaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooLessEnergy) {
        return false;
    }

    @Override
    public boolean increaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooMuchEnergy) {
        return false;
    }

    @Override
    public boolean drainEnergyUnits(ForgeDirection side, long aVoltage, long aAmperage) {
        return false;
    }

    @Override
    public long getAverageElectricInput() {
        return 0;
    }

    @Override
    public long getAverageElectricOutput() {
        return 0;
    }

    @Override
    public long getStoredEU() {
        return euStored;
    }

    @Override
    public long getEUCapacity() {
        return euMaxStore();
    }

    @Override
    public World getWorld() {
        return this.worldObj;
    }

    @Override
    public int getXCoord() {
        return this.xCoord;
    }

    @Override
    public short getYCoord() {
        return (short) this.yCoord;
    }

    @Override
    public int getZCoord() {
        return this.zCoord;
    }

    @Override
    public boolean isServerSide() {
        return !this.worldObj.isRemote;
    }

    @Override
    public boolean isClientSide() {
        return this.worldObj.isRemote;
    }

    @Override
    public int getRandomNumber(int aRange) {
        return this.worldObj.rand.nextInt(aRange);
    }

    @Override
    public TileEntity getTileEntity(int aX, int aY, int aZ) {
        return this.worldObj.getTileEntity(aX, aY, aZ);
    }

    @Override
    public TileEntity getTileEntityOffset(int aX, int aY, int aZ) {
        return this.getTileEntity(aX, aY, aZ);
    }

    @Override
    public TileEntity getTileEntityAtSide(ForgeDirection side) {
        return this.getTileEntityAtSideAndDistance(side, 1);
    }

    @Override
    public TileEntity getTileEntityAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getTileEntity(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public IInventory getIInventory(int aX, int aY, int aZ) {
        TileEntity te = this.worldObj.getTileEntity(aX, aY, aZ);
        if (!(te instanceof IInventory)) return null;

        return (IInventory) te;
    }

    @Override
    public IInventory getIInventoryOffset(int aX, int aY, int aZ) {
        return this.getIInventory(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public IInventory getIInventoryAtSide(ForgeDirection side) {
        return this.getIInventoryAtSideAndDistance(side, 1);
    }

    @Override
    public IInventory getIInventoryAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getIInventory(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public IFluidHandler getITankContainer(int aX, int aY, int aZ) {
        TileEntity te = this.worldObj.getTileEntity(aX, aY, aZ);
        if (!(te instanceof IFluidHandler)) return null;

        return (IFluidHandler) te;
    }

    @Override
    public IFluidHandler getITankContainerOffset(int aX, int aY, int aZ) {
        return this.getITankContainer(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public IFluidHandler getITankContainerAtSide(ForgeDirection side) {
        return this.getITankContainerAtSideAndDistance(side, 1);
    }

    @Override
    public IFluidHandler getITankContainerAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getITankContainer(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public IGregTechTileEntity getIGregTechTileEntity(int aX, int aY, int aZ) {
        TileEntity te = this.worldObj.getTileEntity(aX, aY, aZ);
        if (!(te instanceof IGregTechTileEntity)) return null;

        return (IGregTechTileEntity) te;
    }

    @Override
    public IGregTechTileEntity getIGregTechTileEntityOffset(int aX, int aY, int aZ) {
        return this.getIGregTechTileEntity(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public IGregTechTileEntity getIGregTechTileEntityAtSide(ForgeDirection side) {
        return this.getIGregTechTileEntityAtSideAndDistance(side, 1);
    }

    @Override
    public IGregTechTileEntity getIGregTechTileEntityAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getIGregTechTileEntity(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public Block getBlock(int aX, int aY, int aZ) {
        return this.worldObj.getBlock(aX, aY, aZ);
    }

    @Override
    public Block getBlockOffset(int aX, int aY, int aZ) {
        return this.getBlock(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public Block getBlockAtSide(ForgeDirection side) {
        return this.getBlockAtSideAndDistance(side, 1);
    }

    @Override
    public Block getBlockAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getBlock(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public int getMetaID(int aX, int aY, int aZ) {
        return (byte) this.worldObj.getBlockMetadata(aX, aY, aZ);
    }

    @Override
    public int getMetaIDOffset(int aX, int aY, int aZ) {
        return this.getMetaID(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public int getMetaIDAtSide(ForgeDirection side) {
        return this.getMetaIDAtSideAndDistance(side, 1);
    }

    @Override
    public int getMetaIDAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getMetaID(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public byte getLightLevel(int aX, int aY, int aZ) {
        return (byte) (this.worldObj.getLightBrightness(aX, aY, aZ) * 15.0F);
    }

    @Override
    public byte getLightLevelOffset(int aX, int aY, int aZ) {
        return this.getLightLevel(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public byte getLightLevelAtSide(ForgeDirection side) {
        return this.getLightLevelAtSideAndDistance(side, 1);
    }

    @Override
    public byte getLightLevelAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getLightLevel(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public boolean getOpacity(int aX, int aY, int aZ) {
        return GTUtility.isOpaqueBlock(this.worldObj, aX, aY, aZ);
    }

    @Override
    public boolean getOpacityOffset(int aX, int aY, int aZ) {
        return this.getOpacity(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public boolean getOpacityAtSide(ForgeDirection side) {
        return this.getOpacityAtSideAndDistance(side, 1);
    }

    @Override
    public boolean getOpacityAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getOpacity(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public boolean getSky(int aX, int aY, int aZ) {
        return this.worldObj.canBlockSeeTheSky(aX, aY, aZ);
    }

    @Override
    public boolean getSkyOffset(int aX, int aY, int aZ) {
        return this.getSky(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public boolean getSkyAtSide(ForgeDirection side) {
        return this.getSkyAtSideAndDistance(side, 1);
    }

    @Override
    public boolean getSkyAtSideAndDistance(ForgeDirection side, int aDistance) {
        return false;
    }

    @Override
    public boolean getAir(int aX, int aY, int aZ) {
        return GTUtility.isBlockAir(this.worldObj, aX, aY, aZ);
    }

    @Override
    public boolean getAirOffset(int aX, int aY, int aZ) {
        return this.getAir(this.xCoord + aX, this.yCoord + aY, this.zCoord + aZ);
    }

    @Override
    public boolean getAirAtSide(ForgeDirection side) {
        return this.getAirAtSideAndDistance(side, 1);
    }

    @Override
    public boolean getAirAtSideAndDistance(ForgeDirection side, int aDistance) {
        return this.getAir(
            this.getOffsetX(side, aDistance),
            this.getOffsetY(side, aDistance),
            this.getOffsetZ(side, aDistance));
    }

    @Override
    public BiomeGenBase getBiome() {
        return this.getBiome(this.xCoord, this.zCoord);
    }

    @Override
    public BiomeGenBase getBiome(int aX, int aZ) {
        return this.worldObj.getBiomeGenForCoords(aX, aZ);
    }

    @Override
    public final int getOffsetX(ForgeDirection side, int multiplier) {
        return xCoord + side.offsetX * multiplier;
    }

    @Override
    public final short getOffsetY(ForgeDirection side, int multiplier) {
        return (short) (yCoord + side.offsetY * multiplier);
    }

    @Override
    public final int getOffsetZ(ForgeDirection side, int multiplier) {
        return zCoord + side.offsetZ * multiplier;
    }

    public boolean isDead = false;

    @Override
    public boolean isDead() {
        return isDead || isInvalidTileEntity();
    }

    @Override
    public void sendBlockEvent(byte aID, byte aValue) {
        GTBlockEventTracker.enqueue(worldObj, xCoord, yCoord, zCoord, aID, aValue);
    }

    @Override
    public long getTimer() {
        return 0;
    }

    @Override
    public void setLightValue(byte aLightValue) {

    }

    @Override
    public boolean isInvalidTileEntity() {
        return this.isInvalid();
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setLong("heStored", heStored);
        tag.setLong("euStored", euStored);
        tag.setInteger("tier", tier);
        tag.setLong("amperage", amperage);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        this.heStored = tag.getLong("heStored");
        this.euStored = tag.getLong("euStored");
        this.tier = tag.getInteger("tier");
        this.amperage = tag.getLong("amperage");
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.heStored = nbt.getLong("heStored");
        this.euStored = nbt.getLong("euStored");
        this.tier = nbt.getInteger("tier");
        this.amperage = nbt.getLong("amperage");
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setLong("heStored", this.heStored);
        nbt.setLong("euStored", this.euStored);
        nbt.setInteger("tier", this.tier);
        nbt.setLong("amperage", this.amperage);
    }
}
