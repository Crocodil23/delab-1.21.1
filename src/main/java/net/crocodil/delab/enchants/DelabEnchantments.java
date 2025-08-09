package net.crocodil.delab.enchants;

import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.*;

public class DelabEnchantments {

    public static final ResourceKey<Enchantment> DOUBLE_STRIKE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "double_strike"));
    public static final ResourceKey<Enchantment> HEAD_HUNTER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "head_hunter"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);
        register(context, DOUBLE_STRIKE, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.DAGGERS_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.DAGGERS_ENCHANTABLE),
                        10,
                        5,
                        Enchantment.dynamicCost(1, 11),
                        Enchantment.dynamicCost(21, 11),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)));
        register(context, HEAD_HUNTER, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.DAGGERS_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.DAGGERS_ENCHANTABLE),
                        2,
                        3,
                        Enchantment.dynamicCost(15, 9),
                        Enchantment.dynamicCost(65, 9),
                        4,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(DelabTags.Enchantments.LOOTING_EXCLUSIVE)));
    }
    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
