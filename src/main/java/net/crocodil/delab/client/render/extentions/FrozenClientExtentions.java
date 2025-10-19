package net.crocodil.delab.client.render.extentions;

import net.crocodil.delab.client.model.FrozenArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class FrozenClientExtentions implements IClientItemExtensions {
    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
        if (equipmentSlot == EquipmentSlot.LEGS) {
            return new FrozenArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(FrozenArmorModel.LAYER_LOCATION_INNER));
        } else {
            return new FrozenArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(FrozenArmorModel.LAYER_LOCATION_OUTER));
        }
    }
}