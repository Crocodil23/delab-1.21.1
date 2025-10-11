package net.crocodil.delab.client.render;

import net.crocodil.delab.client.model.WildArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class WildClientExtensions implements IClientItemExtensions {
    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (equipmentSlot == EquipmentSlot.LEGS) {
            return new WildArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(WildArmorModel.LAYER_LOCATION_INNER));
        } else {
            return new WildArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(WildArmorModel.LAYER_LOCATION_OUTER));
        }
    }
}