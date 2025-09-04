package net.crocodil.delab.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.crocodil.delab.effects.DelabMobEffects;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public abstract class ItemInHandLayerMixin<T extends LivingEntity, M, A> {

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


        if (entity.hasEffect(DelabMobEffects.SHADOW_STRIKE)) {
            ci.cancel();
        }
    }
}