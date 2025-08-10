package net.crocodil.delab.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.crocodil.delab.effects.DelabMobEffects;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


/**
 * Cancel humanoid armor rendering when the entity is invisible (potion or flag).
 * Adjust the float parameters if your decompiled method has a different order/number.
 */
@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M, A> {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(PoseStack matrices,
                          MultiBufferSource buffers,
                          int packedLight,
                          T entity,
                          float limbSwing,
                          float limbSwingAmount,
                          float age,
                          float headYaw,
                          float headPitch,
                          float partialTicks,
                          CallbackInfo ci) {

        if (entity == null) return;

        // Most robust: check both the potion effect and the entity invisibility flag
        boolean invisibleByPotion = false;
        try {
            invisibleByPotion = entity.hasEffect(DelabMobEffects.SHADOW_STRIKE);
        } catch (Throwable ignored) { /* method name may differ in your mappings */ }

        if (entity.isInvisible() || invisibleByPotion) {
            ci.cancel();
        }
    }
}