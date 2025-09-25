package net.crocodil.delab.enchants;

import net.crocodil.delab.Delab;
import net.crocodil.delab.DelabTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

public class DelabEnchantments {

    public static final ResourceKey<Enchantment> DOUBLE_STRIKE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "double_strike"));
    public static final ResourceKey<Enchantment> HEAD_HUNTER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "head_hunter"));
    public static final ResourceKey<Enchantment> SHADOW_STRIKE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "shadow_strike"));
    public static final ResourceKey<Enchantment> DESTROYER = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "destroyer"));
    public static final ResourceKey<Enchantment> EARTH_STRIKE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "earth_strike"));
    public static final ResourceKey<Enchantment> POWERFUL_THROW = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "powerful_throw"));
    public static final ResourceKey<Enchantment> CAVALRY_STRIKE = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(Delab.MODID, "cavalry_strike"));


    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);
        register(context, DOUBLE_STRIKE, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.DAGGER_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.DAGGER_ENCHANTABLE),
                        10,
                        5,
                        Enchantment.dynamicCost(1, 11),
                        Enchantment.dynamicCost(21, 11),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)));
        register(context, HEAD_HUNTER, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.DAGGER_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.DAGGER_ENCHANTABLE),
                        2,
                        3,
                        Enchantment.dynamicCost(15, 9),
                        Enchantment.dynamicCost(65, 9),
                        4,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(DelabTags.Enchantments.LOOTING_EXCLUSIVE)));
        register(context, SHADOW_STRIKE, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.DAGGER_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.DAGGER_ENCHANTABLE),
                        10,
                        5,
                        Enchantment.dynamicCost(1, 11),
                        Enchantment.dynamicCost(21, 11),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)));
        register(context, DESTROYER, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.HAMMER_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.HAMMER_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(15),
                        Enchantment.constantCost(65),
                        8,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.withDefaultNamespace("enchantment.efficiency"),
                                Attributes.MINING_EFFICIENCY,
                                new LevelBasedValue.LevelsSquared(-4.0F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
                .exclusiveWith(enchantments.getOrThrow(DelabTags.Enchantments.EFFICIENCY_EXCLUSIVE)));
        register(context, EARTH_STRIKE, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.HAMMER_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.HAMMER_ENCHANTABLE),
                        10,
                        5,
                        Enchantment.dynamicCost(1, 11),
                        Enchantment.dynamicCost(21, 11),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)));
        register(context, POWERFUL_THROW, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.SPEAR_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.SPEAR_ENCHANTABLE),
                        10,
                        5,
                        Enchantment.dynamicCost(1, 11),
                        Enchantment.dynamicCost(21, 11),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)));
        register(context, CAVALRY_STRIKE, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(DelabTags.Items.SPEAR_ENCHANTABLE),
                        items.getOrThrow(DelabTags.Items.SPEAR_ENCHANTABLE),
                        10,
                        5,
                        Enchantment.dynamicCost(1, 11),
                        Enchantment.dynamicCost(21, 11),
                        2,
                        EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)));
    }
    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
