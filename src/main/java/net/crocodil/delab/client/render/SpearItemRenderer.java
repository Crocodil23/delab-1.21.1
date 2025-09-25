package net.crocodil.delab.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.crocodil.delab.Delab;
import net.crocodil.delab.client.model.ThrowingSpearModel;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SpearItemRenderer extends BlockEntityWithoutLevelRenderer {
    private ThrowingSpearModel model;
    public SpearItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
        model = new ThrowingSpearModel(modelSet.bakeLayer(ThrowingSpearModel.LAYER_LOCATION));
    }
    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context,
                             PoseStack poseStack, MultiBufferSource buffer,
                             int packedLight, int packedOverlay) {
        Minecraft mc = Minecraft.getInstance();
        ItemStack spear_inventory = new ItemStack(DelabItems.WOODEN_SPEAR_INVENTORY.get());
        ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/wooden_spear.png");
        if(stack.is(DelabItems.STONE_SPEAR))
        {
            TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/stone_spear.png");
            spear_inventory =new ItemStack(DelabItems.STONE_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.IRON_SPEAR))
        {
            TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/iron_spear.png");
            spear_inventory =new ItemStack(DelabItems.IRON_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.GOLDEN_SPEAR))
        {
            TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/golden_spear.png");
            spear_inventory =new ItemStack(DelabItems.GOLDEN_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.DIAMOND_SPEAR))
        {
            TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/diamond_spear.png");
            spear_inventory =new ItemStack(DelabItems.DIAMOND_SPEAR_INVENTORY.get());
        }
        else if(stack.is(DelabItems.NETHERITE_SPEAR))
        {
            TEXTURE = ResourceLocation.fromNamespaceAndPath(Delab.MODID, "textures/entity/spear/netherite_spear.png");
            spear_inventory = new ItemStack(DelabItems.NETHERITE_SPEAR_INVENTORY.get());
        }

        if(context == ItemDisplayContext.GUI || context == ItemDisplayContext.NONE || context == ItemDisplayContext.GROUND)
        {
            if (stack.isEnchanted())
                spear_inventory.set(DataComponents.ENCHANTMENTS, stack.get(DataComponents.ENCHANTMENTS));
            poseStack.pushPose();
            poseStack.translate(0.5,0.5, 0.5);

            mc.getItemRenderer().renderStatic(spear_inventory, context, packedLight, packedOverlay, poseStack, buffer,  mc.level, 0);
            poseStack.popPose();
            return;
        }

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexconsumer1 = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(TEXTURE), false, stack.hasFoil());
        this.model.renderToBuffer(poseStack, vertexconsumer1, packedLight, packedOverlay);
        poseStack.popPose();


    }
}