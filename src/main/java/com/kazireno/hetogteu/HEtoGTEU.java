package com.kazireno.hetogteu;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = HEtoGTEU.MODID,
    version = HEtoGTEU.MODVERSION,
    name = "HE2GTEU",
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:hbm;required-after:gregtech")
public class HEtoGTEU {

    public static final String MODID = "hetogteu";
    public static final String MODVERSION = "0.0.2";

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        Register.RegistryBlock();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Register.RegistryRecipe();
    }
}
