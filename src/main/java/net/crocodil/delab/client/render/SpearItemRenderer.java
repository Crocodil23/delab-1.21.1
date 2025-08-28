package net.crocodil.delab.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.crocodil.delab.Enityes.DelabEntities;
import net.crocodil.delab.Enityes.Spears.SpearMaterial;
import net.crocodil.delab.Enityes.Spears.ThrowingSpear;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.item.*;

public class SpearItemRenderer extends BlockEntityWithoutLevelRenderer {
    public SpearItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
        Minecraft mc = Minecraft.getInstance();;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context,
                             PoseStack poseStack, MultiBufferSource buffer,
                             int packedLight, int packedOverlay) {
        Minecraft mc = Minecraft.getInstance();
        ItemStack spear_inventory = new ItemStack(DelabItems.WOODEN_SPEAR_INVENTORY.get());
        ThrowingSpear spear = new ThrowingSpear(DelabEntities.THROWING_SPEAR.get(), mc.level);
        if(stack.is(DelabItems.STONE_SPEAR))
        {
            spear.setMaterial(SpearMaterial.STONE);
            spear_inventory =new ItemStack(DelabItems.STONE_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.IRON_SPEAR))
        {
            spear.setMaterial(SpearMaterial.IRON);
            spear_inventory =new ItemStack(DelabItems.IRON_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.GOLDEN_SPEAR))
        {
            spear.setMaterial(SpearMaterial.GOLDEN);
            spear_inventory =new ItemStack(DelabItems.GOLDEN_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.DIAMOND_SPEAR))
        {
            spear.setMaterial(SpearMaterial.DIAMOND);
            spear_inventory =new ItemStack(DelabItems.DIAMOND_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.NETHERITE_SPEAR))
        {
            spear.setMaterial(SpearMaterial.NETHERITE);
            spear_inventory = new ItemStack(DelabItems.NETHERITE_SPEAR_INVENTORY.get());
        }

        if(context == ItemDisplayContext.GUI || context == ItemDisplayContext.NONE || context == ItemDisplayContext.GROUND)
        {
            poseStack.pushPose();
            poseStack.translate(0.5,0.5, 0.5);

            mc.getItemRenderer().renderStatic(spear_inventory, context, packedLight, packedOverlay, poseStack, buffer,  mc.level, 0);
            poseStack.popPose();
            return;
        }
            spear.setNoPhysics(true);

            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            EntityRenderer<? super ThrowingSpear> renderer =
                    mc.getEntityRenderDispatcher().getRenderer(spear);

            renderer.render(spear, 0, 0.0F, poseStack, buffer, packedLight);

            poseStack.popPose();


    }
}