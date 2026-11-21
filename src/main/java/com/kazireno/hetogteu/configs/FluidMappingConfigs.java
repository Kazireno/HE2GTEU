package com.kazireno.hetogteu.configs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.kazireno.hetogteu.utils.FluidMapping;

public final class FluidMappingConfigs {

    private static final String CAT = "fluids";
    private static final String KEY = "mappings";
    private static Configuration cfg;

    private FluidMappingConfigs() {}

    public static void init(File configDir) {
        cfg = new Configuration(new File(configDir, "Hbm_FluidMapping.cfg"));
        cfg.load();
        try {
            generateMissingEntries();
        } finally {
            cfg.save();
        }
        buildMappings();
    }

    private static String[] readLines() {
        return cfg.getStringList(
            KEY,
            CAT,
            new String[0],
            "格式: <HBM流体注册名>=<Forge流体1>,<Forge流体2>,...\n" + "  每个 HBM 流体可映射多个 Forge 流体（英文逗号分隔）。\n"
                + "  HBM->Forge 仅使用第一个；Forge->HBM 全部生效。\n"
                + "  Forge 名可为注册名，或 modid:name（找不到会退回 name 再找一次）。\n"
                + "  等号右侧留空代表不映射。");
    }

    private static void generateMissingEntries() {
        String[] current = readLines();
        Set<String> known = new HashSet<>();
        if (current != null) for (String line : current) {
            int eq = line.indexOf('=');
            if (eq > 0) known.add(
                line.substring(0, eq)
                    .trim());
        }

        List<String> merged = new ArrayList<>();
        if (current != null) merged.addAll(Arrays.asList(current));

        for (FluidType type : Fluids.getAll()) {
            if (known.contains(type.getName())) continue;
            Fluid match = FluidRegistry.getFluid(type.getName());
            String right = match == null ? "" : FluidRegistry.getFluidName(match);
            merged.add(type.getName() + "=" + right);
        }

        if (merged.size() != (current == null ? 0 : current.length)) {
            cfg.get(CAT, KEY, new String[0], "")
                .set(merged.toArray(new String[0]));
        }
    }

    private static void buildMappings() {
        FluidMapping.clear();
        String[] lines = readLines();
        if (lines == null) return;
        for (String line : lines) {
            if (line == null || line.trim()
                .isEmpty()) continue;
            int eq = line.indexOf('=');
            if (eq <= 0) continue;
            String hbmName = line.substring(0, eq)
                .trim();
            String right = line.substring(eq + 1)
                .trim();
            if (right.isEmpty()) continue;

            FluidType hbm = findHbm(hbmName);
            if (hbm == null) {
                System.out.println("跳过未知 HBM 流体: " + hbmName);
                continue;
            }
            List<String> forges = new ArrayList<>();
            for (String s : right.split(",")) {
                String t = s.trim();
                if (!t.isEmpty()) forges.add(t);
            }
            FluidMapping.put(hbm, forges);
        }
        System.out.println("流体映射加载完成: " + FluidMapping.size() + " 条");
    }

    private static FluidType findHbm(String name) {
        for (FluidType t : Fluids.getAll()) {
            if (t.getName()
                .equalsIgnoreCase(name)) return t;
        }
        return null;
    }
}
