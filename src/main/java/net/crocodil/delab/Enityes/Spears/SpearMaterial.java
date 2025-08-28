package net.crocodil.delab.Enityes.Spears;

import com.google.common.collect.Maps;
import net.crocodil.delab.Delab;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public enum SpearMaterial {
    WOODEN(0),
    STONE(1),
    IRON(2),
    GOLDEN(3),
    DIAMOND(4),
    NETHERITE(5);

    private static final SpearMaterial[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SpearMaterial::getId)).toArray(SpearMaterial[]::new);
    private final int id;

    SpearMaterial(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SpearMaterial byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}
