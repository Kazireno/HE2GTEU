package com.kazireno.hetogteu;

import com.kazireno.hetogteu.block.BlockHeToEu;
import com.kazireno.hetogteu.tile.TileEntityHeToEu;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
    modid = HEtoGTEU.MODID,
    version = HEtoGTEU.MODVERSION,
    name = "HE2GTEU",
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:hbm")
public class HEtoGTEU {

    public static final String MODID = "hetogteu";
    public static final String MODVERSION = "0.0.1";

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

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
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
}
