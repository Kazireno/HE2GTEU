package com.kazireno.hetogteu.utils;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.hbm.inventory.fluid.FluidType;

public final class FluidMapping {

    private static final Map<FluidType, Fluid> HBM_TO_FORGE = new IdentityHashMap<>();
    private static final Map<Fluid, FluidType> FORGE_TO_HBM = new IdentityHashMap<>();

    private FluidMapping() {}

    public static void put(FluidType hbm, List<String> forgeNames) {
        if (hbm == null || forgeNames == null || forgeNames.isEmpty()) return;
        Fluid first = null;
        for (String raw : forgeNames) {
            String name = raw.trim();
            if (name.isEmpty()) continue;
            Fluid forge = FluidRegistry.getFluid(name);
            if (forge == null) {
                int colon = name.indexOf(':');
                if (colon > 0) forge = FluidRegistry.getFluid(name.substring(colon + 1));
            }
            if (forge == null) {
                // System.out.println("跳过不存在的 Forge 流体: " + name + " (HBM: " + hbm.getName() + ")");
                continue;
            }
            if (first == null) first = forge;
            FORGE_TO_HBM.put(forge, hbm);
        }
        if (first != null) HBM_TO_FORGE.put(hbm, first);
    }

    public static Fluid toForge(FluidType hbm) {
        return hbm == null ? null : HBM_TO_FORGE.get(hbm);
    }

    public static FluidType toHbm(Fluid forge) {
        return forge == null ? null : FORGE_TO_HBM.get(forge);
    }

    public static int size() {
        return HBM_TO_FORGE.size();
    }

    public static void clear() {
        HBM_TO_FORGE.clear();
        FORGE_TO_HBM.clear();
    }
}
