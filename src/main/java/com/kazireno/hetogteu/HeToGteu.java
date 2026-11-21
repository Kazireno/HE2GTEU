package com.kazireno.hetogteu;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kazireno.hetogteu.configs.FluidMappingConfigs;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HeToGteu.MODID, version = Tags.VERSION, name = "HeToGteu", acceptedMinecraftVersions = "[1.7.10]")
public class HeToGteu {

    public static final String MODID = "hetogteu";
    public static final Logger LOG = LogManager.getLogger(MODID);
    private File configDir = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDir = event.getModConfigurationDirectory();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        FluidMappingConfigs.init(configDir);
    }
}
