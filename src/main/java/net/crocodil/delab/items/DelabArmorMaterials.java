package net.crocodil.delab.items;

import net.crocodil.delab.Delab;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class DelabArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, Delab.MODID);


    public static final Holder<ArmorMaterial> ABOMINATION = ARMOR_MATERIALS.register("abomination", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            12,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(DelabItems.ABOMINATION_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "abomination"))),
            0.0F,
            0.0F
    ));

    public static final Holder<ArmorMaterial> FROZEN = ARMOR_MATERIALS.register("frozen", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            12,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(DelabItems.FROZEN_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "frozen"))),
            0.0F,
            0.0F
    ));

    public static final Holder<ArmorMaterial> WILD = ARMOR_MATERIALS.register("wild", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            12,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(DelabItems.WILD_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Delab.MODID, "wild"))),
            0.0F,
            0.0F
    ));

    public static void register(IEventBus bus)
    {
        ARMOR_MATERIALS.register(bus);
    }

    public static boolean isFullSetOff(Holder<ArmorMaterial> material, LivingEntity living)
    {
        Iterable<ItemStack> armors = living.getArmorSlots();
        for (ItemStack itemStack : armors)
        {
            if(!(itemStack.getItem() instanceof ArmorItem armorItem))
                return false;
            else if (armorItem.getMaterial() != material)
                return false;
        }
        return true;
    }
}