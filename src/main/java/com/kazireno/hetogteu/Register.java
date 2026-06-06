package com.kazireno.hetogteu;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.hbm.items.ModItems;
import com.kazireno.hetogteu.block.BlockHeToEu;
import com.kazireno.hetogteu.tile.TileEntityHeToEu;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.ItemList;

public class Register {

    // Voltage tier: ULV (0)
    public static final BlockHeToEu HETOEU_ULV_1A = new BlockHeToEu(0, 1);
    public static final BlockHeToEu HETOEU_ULV_4A = new BlockHeToEu(0, 4);
    public static final BlockHeToEu HETOEU_ULV_16A = new BlockHeToEu(0, 16);
    public static final BlockHeToEu HETOEU_ULV_64A = new BlockHeToEu(0, 64);

    // Voltage tier: LV (1)
    public static final BlockHeToEu HETOEU_LV_1A = new BlockHeToEu(1, 1);
    public static final BlockHeToEu HETOEU_LV_4A = new BlockHeToEu(1, 4);
    public static final BlockHeToEu HETOEU_LV_16A = new BlockHeToEu(1, 16);
    public static final BlockHeToEu HETOEU_LV_64A = new BlockHeToEu(1, 64);

    // Voltage tier: MV (2)
    public static final BlockHeToEu HETOEU_MV_1A = new BlockHeToEu(2, 1);
    public static final BlockHeToEu HETOEU_MV_4A = new BlockHeToEu(2, 4);
    public static final BlockHeToEu HETOEU_MV_16A = new BlockHeToEu(2, 16);
    public static final BlockHeToEu HETOEU_MV_64A = new BlockHeToEu(2, 64);

    // Voltage tier: HV (3)
    public static final BlockHeToEu HETOEU_HV_1A = new BlockHeToEu(3, 1);
    public static final BlockHeToEu HETOEU_HV_4A = new BlockHeToEu(3, 4);
    public static final BlockHeToEu HETOEU_HV_16A = new BlockHeToEu(3, 16);
    public static final BlockHeToEu HETOEU_HV_64A = new BlockHeToEu(3, 64);

    // Voltage tier: EV (4)
    public static final BlockHeToEu HETOEU_EV_1A = new BlockHeToEu(4, 1);
    public static final BlockHeToEu HETOEU_EV_4A = new BlockHeToEu(4, 4);
    public static final BlockHeToEu HETOEU_EV_16A = new BlockHeToEu(4, 16);
    public static final BlockHeToEu HETOEU_EV_64A = new BlockHeToEu(4, 64);

    // Voltage tier: IV (5)
    public static final BlockHeToEu HETOEU_IV_1A = new BlockHeToEu(5, 1);
    public static final BlockHeToEu HETOEU_IV_4A = new BlockHeToEu(5, 4);
    public static final BlockHeToEu HETOEU_IV_16A = new BlockHeToEu(5, 16);
    public static final BlockHeToEu HETOEU_IV_64A = new BlockHeToEu(5, 64);

    // Voltage tier: LuV (6)
    public static final BlockHeToEu HETOEU_LuV_1A = new BlockHeToEu(6, 1);
    public static final BlockHeToEu HETOEU_LuV_4A = new BlockHeToEu(6, 4);
    public static final BlockHeToEu HETOEU_LuV_16A = new BlockHeToEu(6, 16);
    public static final BlockHeToEu HETOEU_LuV_64A = new BlockHeToEu(6, 64);

    // Voltage tier: ZPM (7)
    public static final BlockHeToEu HETOEU_ZPM_1A = new BlockHeToEu(7, 1);
    public static final BlockHeToEu HETOEU_ZPM_4A = new BlockHeToEu(7, 4);
    public static final BlockHeToEu HETOEU_ZPM_16A = new BlockHeToEu(7, 16);
    public static final BlockHeToEu HETOEU_ZPM_64A = new BlockHeToEu(7, 64);

    // Voltage tier: UV (8)
    public static final BlockHeToEu HETOEU_UV_1A = new BlockHeToEu(8, 1);
    public static final BlockHeToEu HETOEU_UV_4A = new BlockHeToEu(8, 4);
    public static final BlockHeToEu HETOEU_UV_16A = new BlockHeToEu(8, 16);
    public static final BlockHeToEu HETOEU_UV_64A = new BlockHeToEu(8, 64);

    // Voltage tier: UHV (9)
    public static final BlockHeToEu HETOEU_UHV_1A = new BlockHeToEu(9, 1);
    public static final BlockHeToEu HETOEU_UHV_4A = new BlockHeToEu(9, 4);
    public static final BlockHeToEu HETOEU_UHV_16A = new BlockHeToEu(9, 16);
    public static final BlockHeToEu HETOEU_UHV_64A = new BlockHeToEu(9, 64);

    // Voltage tier: UEV (10)
    public static final BlockHeToEu HETOEU_UEV_1A = new BlockHeToEu(10, 1);
    public static final BlockHeToEu HETOEU_UEV_4A = new BlockHeToEu(10, 4);
    public static final BlockHeToEu HETOEU_UEV_16A = new BlockHeToEu(10, 16);
    public static final BlockHeToEu HETOEU_UEV_64A = new BlockHeToEu(10, 64);

    // Voltage tier: UIV (11)
    public static final BlockHeToEu HETOEU_UIV_1A = new BlockHeToEu(11, 1);
    public static final BlockHeToEu HETOEU_UIV_4A = new BlockHeToEu(11, 4);
    public static final BlockHeToEu HETOEU_UIV_16A = new BlockHeToEu(11, 16);
    public static final BlockHeToEu HETOEU_UIV_64A = new BlockHeToEu(11, 64);

    // Voltage tier: UMV (12)
    public static final BlockHeToEu HETOEU_UMV_1A = new BlockHeToEu(12, 1);
    public static final BlockHeToEu HETOEU_UMV_4A = new BlockHeToEu(12, 4);
    public static final BlockHeToEu HETOEU_UMV_16A = new BlockHeToEu(12, 16);
    public static final BlockHeToEu HETOEU_UMV_64A = new BlockHeToEu(12, 64);

    // Voltage tier: UXV (13)
    public static final BlockHeToEu HETOEU_UXV_1A = new BlockHeToEu(13, 1);
    public static final BlockHeToEu HETOEU_UXV_4A = new BlockHeToEu(13, 4);
    public static final BlockHeToEu HETOEU_UXV_16A = new BlockHeToEu(13, 16);
    public static final BlockHeToEu HETOEU_UXV_64A = new BlockHeToEu(13, 64);

    // Voltage tier: MAX (14)
    public static final BlockHeToEu HETOEU_MAX_1A = new BlockHeToEu(14, 1);
    public static final BlockHeToEu HETOEU_MAX_4A = new BlockHeToEu(14, 4);
    public static final BlockHeToEu HETOEU_MAX_16A = new BlockHeToEu(14, 16);
    public static final BlockHeToEu HETOEU_MAX_64A = new BlockHeToEu(14, 64);

    public static void RegistryBlock() {
        // block
        // ULV
        GameRegistry.registerBlock(HETOEU_ULV_1A, "hetoeu_ulv_1a");
        GameRegistry.registerBlock(HETOEU_ULV_4A, "hetoeu_ulv_4a");
        GameRegistry.registerBlock(HETOEU_ULV_16A, "hetoeu_ulv_16a");
        GameRegistry.registerBlock(HETOEU_ULV_64A, "hetoeu_ulv_64a");

        // LV
        GameRegistry.registerBlock(HETOEU_LV_1A, "hetoeu_lv_1a");
        GameRegistry.registerBlock(HETOEU_LV_4A, "hetoeu_lv_4a");
        GameRegistry.registerBlock(HETOEU_LV_16A, "hetoeu_lv_16a");
        GameRegistry.registerBlock(HETOEU_LV_64A, "hetoeu_lv_64a");

        // MV
        GameRegistry.registerBlock(HETOEU_MV_1A, "hetoeu_mv_1a");
        GameRegistry.registerBlock(HETOEU_MV_4A, "hetoeu_mv_4a");
        GameRegistry.registerBlock(HETOEU_MV_16A, "hetoeu_mv_16a");
        GameRegistry.registerBlock(HETOEU_MV_64A, "hetoeu_mv_64a");

        // HV
        GameRegistry.registerBlock(HETOEU_HV_1A, "hetoeu_hv_1a");
        GameRegistry.registerBlock(HETOEU_HV_4A, "hetoeu_hv_4a");
        GameRegistry.registerBlock(HETOEU_HV_16A, "hetoeu_hv_16a");
        GameRegistry.registerBlock(HETOEU_HV_64A, "hetoeu_hv_64a");

        // EV
        GameRegistry.registerBlock(HETOEU_EV_1A, "hetoeu_ev_1a");
        GameRegistry.registerBlock(HETOEU_EV_4A, "hetoeu_ev_4a");
        GameRegistry.registerBlock(HETOEU_EV_16A, "hetoeu_ev_16a");
        GameRegistry.registerBlock(HETOEU_EV_64A, "hetoeu_ev_64a");

        // IV
        GameRegistry.registerBlock(HETOEU_IV_1A, "hetoeu_iv_1a");
        GameRegistry.registerBlock(HETOEU_IV_4A, "hetoeu_iv_4a");
        GameRegistry.registerBlock(HETOEU_IV_16A, "hetoeu_iv_16a");
        GameRegistry.registerBlock(HETOEU_IV_64A, "hetoeu_iv_64a");

        // LuV
        GameRegistry.registerBlock(HETOEU_LuV_1A, "hetoeu_luv_1a");
        GameRegistry.registerBlock(HETOEU_LuV_4A, "hetoeu_luv_4a");
        GameRegistry.registerBlock(HETOEU_LuV_16A, "hetoeu_luv_16a");
        GameRegistry.registerBlock(HETOEU_LuV_64A, "hetoeu_luv_64a");

        // ZPM
        GameRegistry.registerBlock(HETOEU_ZPM_1A, "hetoeu_zpm_1a");
        GameRegistry.registerBlock(HETOEU_ZPM_4A, "hetoeu_zpm_4a");
        GameRegistry.registerBlock(HETOEU_ZPM_16A, "hetoeu_zpm_16a");
        GameRegistry.registerBlock(HETOEU_ZPM_64A, "hetoeu_zpm_64a");

        // UV
        GameRegistry.registerBlock(HETOEU_UV_1A, "hetoeu_uv_1a");
        GameRegistry.registerBlock(HETOEU_UV_4A, "hetoeu_uv_4a");
        GameRegistry.registerBlock(HETOEU_UV_16A, "hetoeu_uv_16a");
        GameRegistry.registerBlock(HETOEU_UV_64A, "hetoeu_uv_64a");

        // UHV
        GameRegistry.registerBlock(HETOEU_UHV_1A, "hetoeu_uhv_1a");
        GameRegistry.registerBlock(HETOEU_UHV_4A, "hetoeu_uhv_4a");
        GameRegistry.registerBlock(HETOEU_UHV_16A, "hetoeu_uhv_16a");
        GameRegistry.registerBlock(HETOEU_UHV_64A, "hetoeu_uhv_64a");

        // UEV
        GameRegistry.registerBlock(HETOEU_UEV_1A, "hetoeu_uev_1a");
        GameRegistry.registerBlock(HETOEU_UEV_4A, "hetoeu_uev_4a");
        GameRegistry.registerBlock(HETOEU_UEV_16A, "hetoeu_uev_16a");
        GameRegistry.registerBlock(HETOEU_UEV_64A, "hetoeu_uev_64a");

        // UIV
        GameRegistry.registerBlock(HETOEU_UIV_1A, "hetoeu_uiv_1a");
        GameRegistry.registerBlock(HETOEU_UIV_4A, "hetoeu_uiv_4a");
        GameRegistry.registerBlock(HETOEU_UIV_16A, "hetoeu_uiv_16a");
        GameRegistry.registerBlock(HETOEU_UIV_64A, "hetoeu_uiv_64a");

        // UMV
        GameRegistry.registerBlock(HETOEU_UMV_1A, "hetoeu_umv_1a");
        GameRegistry.registerBlock(HETOEU_UMV_4A, "hetoeu_umv_4a");
        GameRegistry.registerBlock(HETOEU_UMV_16A, "hetoeu_umv_16a");
        GameRegistry.registerBlock(HETOEU_UMV_64A, "hetoeu_umv_64a");

        // UXV
        GameRegistry.registerBlock(HETOEU_UXV_1A, "hetoeu_uxv_1a");
        GameRegistry.registerBlock(HETOEU_UXV_4A, "hetoeu_uxv_4a");
        GameRegistry.registerBlock(HETOEU_UXV_16A, "hetoeu_uxv_16a");
        GameRegistry.registerBlock(HETOEU_UXV_64A, "hetoeu_uxv_64a");

        // MAX
        GameRegistry.registerBlock(HETOEU_MAX_1A, "hetoeu_max_1a");
        GameRegistry.registerBlock(HETOEU_MAX_4A, "hetoeu_max_4a");
        GameRegistry.registerBlock(HETOEU_MAX_16A, "hetoeu_max_16a");
        GameRegistry.registerBlock(HETOEU_MAX_64A, "hetoeu_max_64a");

        // tile
        GameRegistry.registerTileEntity(TileEntityHeToEu.class, "hetoeu");
    }

    public static void RegistryRecipe() {
        Recipe(HETOEU_ULV_1A);
        Recipe(HETOEU_ULV_4A);
        Recipe(HETOEU_ULV_16A);
        Recipe(HETOEU_ULV_64A);

        Recipe(HETOEU_LV_1A);
        Recipe(HETOEU_LV_4A);
        Recipe(HETOEU_LV_16A);
        Recipe(HETOEU_LV_64A);

        Recipe(HETOEU_MV_1A);
        Recipe(HETOEU_MV_4A);
        Recipe(HETOEU_MV_16A);
        Recipe(HETOEU_MV_64A);

        Recipe(HETOEU_HV_1A);
        Recipe(HETOEU_HV_4A);
        Recipe(HETOEU_HV_16A);
        Recipe(HETOEU_HV_64A);

        Recipe(HETOEU_EV_1A);
        Recipe(HETOEU_EV_4A);
        Recipe(HETOEU_EV_16A);
        Recipe(HETOEU_EV_64A);

        Recipe(HETOEU_IV_1A);
        Recipe(HETOEU_IV_4A);
        Recipe(HETOEU_IV_16A);
        Recipe(HETOEU_IV_64A);

        Recipe(HETOEU_LuV_1A);
        Recipe(HETOEU_LuV_4A);
        Recipe(HETOEU_LuV_16A);
        Recipe(HETOEU_LuV_64A);

        Recipe(HETOEU_ZPM_1A);
        Recipe(HETOEU_ZPM_4A);
        Recipe(HETOEU_ZPM_16A);
        Recipe(HETOEU_ZPM_64A);

        Recipe(HETOEU_UV_1A);
        Recipe(HETOEU_UV_4A);
        Recipe(HETOEU_UV_16A);
        Recipe(HETOEU_UV_64A);

        Recipe(HETOEU_UHV_1A);
        Recipe(HETOEU_UHV_4A);
        Recipe(HETOEU_UHV_16A);
        Recipe(HETOEU_UHV_64A);

        Recipe(HETOEU_UEV_1A);
        Recipe(HETOEU_UEV_4A);
        Recipe(HETOEU_UEV_16A);
        Recipe(HETOEU_UEV_64A);

        Recipe(HETOEU_UIV_1A);
        Recipe(HETOEU_UIV_4A);
        Recipe(HETOEU_UIV_16A);
        Recipe(HETOEU_UIV_64A);

        Recipe(HETOEU_UMV_1A);
        Recipe(HETOEU_UMV_4A);
        Recipe(HETOEU_UMV_16A);
        Recipe(HETOEU_UMV_64A);

        Recipe(HETOEU_UXV_1A);
        Recipe(HETOEU_UXV_4A);
        Recipe(HETOEU_UXV_16A);
        Recipe(HETOEU_UXV_64A);

        Recipe(HETOEU_MAX_1A);
        Recipe(HETOEU_MAX_4A);
        Recipe(HETOEU_MAX_16A);
        Recipe(HETOEU_MAX_64A);
    }

    static ItemStack[] item_Hbm_circuit = { new ItemStack(ModItems.circuit, 1, 0),
        new ItemStack(ModItems.circuit, 1, 7), new ItemStack(ModItems.circuit, 1, 5),
        new ItemStack(ModItems.circuit, 1, 8), new ItemStack(ModItems.circuit, 1, 9),
        new ItemStack(ModItems.circuit, 1, 13), new ItemStack(ModItems.circuit, 1, 14),
        new ItemStack(ModItems.circuit, 1, 17) };

    static ItemStack[] item_HBM_wire = { new ItemStack(ModItems.wire_dense, 1, 31),
        new ItemStack(ModItems.wire_dense, 1, 35), new ItemStack(ModItems.wire_dense, 1, 48),
        new ItemStack(ModItems.wire_dense, 1, 45) };

    static ItemStack[] item_GT_hull = { new ItemStack(ItemList.MACHINE_HULLS[0].getItem(), 1, 10),
        new ItemStack(ItemList.MACHINE_HULLS[1].getItem(), 1, 11),
        new ItemStack(ItemList.MACHINE_HULLS[2].getItem(), 1, 12),
        new ItemStack(ItemList.MACHINE_HULLS[3].getItem(), 1, 13),
        new ItemStack(ItemList.MACHINE_HULLS[4].getItem(), 1, 14),
        new ItemStack(ItemList.MACHINE_HULLS[5].getItem(), 1, 15),
        new ItemStack(ItemList.MACHINE_HULLS[6].getItem(), 1, 16),
        new ItemStack(ItemList.MACHINE_HULLS[7].getItem(), 1, 17),
        new ItemStack(ItemList.MACHINE_HULLS[8].getItem(), 1, 18),
        new ItemStack(ItemList.MACHINE_HULLS[9].getItem(), 1, 19),
        new ItemStack(ItemList.MACHINE_HULLS[10].getItem(), 1, 11230),
        new ItemStack(ItemList.MACHINE_HULLS[11].getItem(), 1, 11231),
        new ItemStack(ItemList.MACHINE_HULLS[12].getItem(), 1, 11232),
        new ItemStack(ItemList.MACHINE_HULLS[13].getItem(), 1, 11233),
        new ItemStack(ItemList.MACHINE_HULLS[14].getItem(), 1, 11234) };

    static String[] item_plate = { "plateWroughtIron", "plateSteel", "plateAluminium", "plateStainlessSteel",
        "plateTitanium", "plateTungstenSteel", "plateRhodium-PlatedPalladium", "plateIridium", "plateOsmium",
        "plateNeutronium", "plateBedrockium", "plateBlackPlutonium", "plateSpaceTime", "plateMagmatter",
        "hbm:item.undefined" };

    static String[] item_GT_circuit = { "circuitPrimitive", "circuitBasic", "circuitGood", "circuitAdvanced",
        "circuitData", "circuitElite", "circuitMaster", "circuitUltimate", "circuitSuperconductor", "circuitInfinite",
        "circuitBio", "circuitOptical", "circuitExotic", "circuitCosmic", "circuitTranscendent", };

    static String[] item_GT_cable = { "Lead", "Tin", "AnyCopper", "Gold", "Aluminium", "Tungsten", "VanadiumGallium",
        "Naquadah", "NaquadahAlloy", "SuperconductorUV", "Draconium", "NetherStar", "Quantium", "BlackPlutonium",
        "SpaceTime" };

    static void Recipe(BlockHeToEu blockHeToEu) {
        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(blockHeToEu, 1),
                "ABC",
                "DEF",
                "ABC",
                'A',
                item_Hbm_circuit[(int) blockHeToEu.getInfo()[0] / 2],
                'B',
                item_plate[(int) blockHeToEu.getInfo()[0]],
                'C',
                item_GT_circuit[(int) blockHeToEu.getInfo()[0]],
                'D',
                item_HBM_wire[(int) blockHeToEu.getInfo()[0] / 4],
                'E',
                item_GT_hull[(int) blockHeToEu.getInfo()[0]],
                'F',
                ("cableGt" + getCable(blockHeToEu.getInfo()[1]) + item_GT_cable[(int) blockHeToEu.getInfo()[0]])));
    }

    static String getCable(long a) {
        return switch ((int) a) {
            case 1 -> "01";
            case 4 -> "02";
            case 16 -> "04";
            case 64 -> "16";
            default -> "16";
        };
    }
}
