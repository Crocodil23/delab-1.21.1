package net.crocodil.delab.items;

import net.crocodil.delab.DelabTags;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class DelabItemProperties {

    public static void CreateCustomProperties()
    {
        createSpear(DelabItems.WOODEN_SPEAR.get());
        createSpear(DelabItems.STONE_SPEAR.get());
        createSpear(DelabItems.IRON_SPEAR.get());
        createSpear(DelabItems.GOLDEN_SPEAR.get());
        createSpear(DelabItems.DIAMOND_SPEAR.get());
        createSpear(DelabItems.NETHERITE_SPEAR.get());

        createBow(DelabItems.ABOMINATION_BOW.get());
    }
    private static void createSpear(Item item)
    {
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("throwing"),
                (stack, level, entity, seed) -> {
                    if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                        return 1.0F;
                    }
                    return 0.0F;
                }
        );
    }
    private static void createBow(Item item)
    {
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), (p_344163_, p_344164_, p_344165_, p_344166_) -> {
            if (p_344165_ == null) {
                return 0.0F;
            } else {
                return p_344165_.getUseItem() != p_344163_ ? 0.0F : (float)(p_344163_.getUseDuration(p_344165_) - p_344165_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("pulling"),
                (p_174630_, p_174631_, p_174632_, p_174633_) -> p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F
        );
    }
}
