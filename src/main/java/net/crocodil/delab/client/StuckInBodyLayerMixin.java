package net.crocodil.delab.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.crocodil.delab.effects.DelabMobEffects;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.StuckInBodyLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StuckInBodyLayer.class)
public abstract class StuckInBodyLayerMixin<T extends LivingEntity> {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void onRender(PoseStack poseStack,
                          MultiBufferSource bufferSource,
                          int packedLight,
                          T entity,
                          float limbSwing,
                          float limbSwingAmount,
                          float partialTick,
                          float ageInTicks,
                          float netHeadYaw,
                          float headPitch,
                          CallbackInfo ci) {
        if (entity != null && (entity.isInvisible() || entity.hasEffect(DelabMobEffects.SHADOW_STRIKE))) {
            ci.cancel();
        }
    }
}
