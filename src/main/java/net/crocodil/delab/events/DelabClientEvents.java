package net.crocodil.delab.events;

import net.crocodil.delab.Delab;
import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(modid = Delab.MODID, value = Dist.CLIENT)
public class DelabClientEvents
{
    private static final ResourceLocation IN_MUD = ResourceLocation.fromNamespaceAndPath(Delab.MODID,"textures/misc/in_mud.png");

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiEvent.Pre event) {
        GuiGraphics g = event.getGuiGraphics();
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;
        if (mc.player.hasEffect(DelabMobEffects.IN_MUD)) {
            int w = mc.getWindow().getGuiScaledWidth();
            int h = mc.getWindow().getGuiScaledHeight();
            g.blit(IN_MUD, 0, 0, 0, 0, w, h, w, h);
        }
    }

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == DelabItems.ABOMINATION_BOW.get()) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float)ticksUsingItem / 20f;
            if(deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }
}