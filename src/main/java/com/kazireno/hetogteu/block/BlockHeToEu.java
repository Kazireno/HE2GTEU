package com.kazireno.hetogteu.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import com.hbm.blocks.ILookOverlay;
import com.hbm.util.i18n.I18nUtil;
import com.kazireno.hetogteu.tile.TileEntityHeToEu;

public class BlockHeToEu extends BlockContainer implements ILookOverlay {

    int _tier;
    long _amperage;

    public BlockHeToEu(final int tier, final long amperage) {
        super(Material.iron);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setCreativeTab(CreativeTabs.tabTools);
        this._tier = tier;
        this._amperage = amperage;
        this.setBlockName(
            "hetoeu_" + TileEntityHeToEu.GetVString(_tier)
                .toLowerCase() + "_" + _amperage + "a");
        this.setBlockTextureName("");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityHeToEu(_tier, _amperage);
    }

    public void printHook(RenderGameOverlayEvent.Pre event, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof TileEntityHeToEu) {
            TileEntityHeToEu converter = (TileEntityHeToEu) tile;
            List<String> text = new ArrayList();
            text.add(
                EnumChatFormatting.GREEN + "-> "
                    + EnumChatFormatting.RESET
                    + converter.heStored
                    + " / "
                    + converter.heMaxStore()
                    + " HE");
            text.add(
                EnumChatFormatting.RED + "<- "
                    + EnumChatFormatting.RESET
                    + converter.euStored
                    + " / "
                    + converter.euMaxStore()
                    + " EU"
                    + " ("
                    + TileEntityHeToEu.GetVString(converter.tier)
                    + " "
                    + converter.amperage
                    + "A)");
            ILookOverlay.printGeneric(
                event,
                I18nUtil.resolveKey(this.getUnlocalizedName() + ".name", new Object[0]),
                16776960,
                4210688,
                text);
        }
    }
}
