package net.crocodil.delab;

import net.crocodil.delab.Enityes.DelabEntities;
import net.crocodil.delab.Enityes.Spears.ThrowingSpear;
import net.crocodil.delab.blocks.DelabBlocks;

import net.crocodil.delab.client.render.SpearItemRenderer;
import net.crocodil.delab.client.render.ThrowingSpearRenderer;
import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Delab.MODID)
public class Delab {

    public static final String MODID = "delab";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Delab(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        DelabCreativeTabs.register(modEventBus);
        DelabItems.register(modEventBus);
        DelabBlocks.register(modEventBus);
        DelabMobEffects.register(modEventBus);
        DelabSounds.register(modEventBus);
        DelabEntities.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    private void commonSetup(FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.COMBAT)
        {
            event.accept(DelabItems.WOODEN_DAGGER);
            event.accept(DelabItems.STONE_DAGGER);
            event.accept(DelabItems.IRON_DAGGER);
            event.accept(DelabItems.GOLDEN_DAGGER);
            event.accept(DelabItems.DIAMOND_DAGGER);
            event.accept(DelabItems.NETHERITE_DAGGER);

            event.accept(DelabItems.WOODEN_HAMMER);
            event.accept(DelabItems.STONE_HAMMER);
            event.accept(DelabItems.IRON_HAMMER);
            event.accept(DelabItems.GOLDEN_HAMMER);
            event.accept(DelabItems.DIAMOND_HAMMER);
            event.accept(DelabItems.NETHERITE_HAMMER);

            event.accept(DelabItems.WOODEN_SPEAR);
            event.accept(DelabItems.STONE_SPEAR);
            event.accept(DelabItems.IRON_SPEAR);
            event.accept(DelabItems.GOLDEN_SPEAR);
            event.accept(DelabItems.DIAMOND_SPEAR);
            event.accept(DelabItems.NETHERITE_SPEAR);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(DelabItems.RECHARGE_CRYSTAL);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
        {
            event.accept(DelabBlocks.SEA_WORKSHOP);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(DelabEntities.THROWING_SPEAR.get(), ThrowingSpearRenderer::new);
            event.enqueueWork(() -> {
                ItemProperties.register(
                        DelabItems.WOODEN_SPEAR.get(),
                        ResourceLocation.withDefaultNamespace("throwing"),
                        (stack, level, entity, seed) -> {
                            if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                                return 1.0F;
                            }
                            return 0.0F;
                        }
                );
            });
            event.enqueueWork(() -> {
                ItemProperties.register(
                        DelabItems.STONE_SPEAR.get(),
                        ResourceLocation.withDefaultNamespace("throwing"),
                        (stack, level, entity, seed) -> {
                            if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                                return 1.0F;
                            }
                            return 0.0F;
                        }
                );
            });
            event.enqueueWork(() -> {
                ItemProperties.register(
                        DelabItems.IRON_SPEAR.get(),
                        ResourceLocation.withDefaultNamespace("throwing"),
                        (stack, level, entity, seed) -> {
                            if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                                return 1.0F;
                            }
                            return 0.0F;
                        }
                );
            });
            event.enqueueWork(() -> {
                ItemProperties.register(
                        DelabItems.GOLDEN_SPEAR.get(),
                        ResourceLocation.withDefaultNamespace("throwing"),
                        (stack, level, entity, seed) -> {
                            if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                                return 1.0F;
                            }
                            return 0.0F;
                        }
                );
            });
            event.enqueueWork(() -> {
                ItemProperties.register(
                        DelabItems.DIAMOND_SPEAR.get(),
                        ResourceLocation.withDefaultNamespace("throwing"),
                        (stack, level, entity, seed) -> {
                            if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                                return 1.0F;
                            }
                            return 0.0F;
                        }
                );
            });
            event.enqueueWork(() -> {
                ItemProperties.register(
                        DelabItems.NETHERITE_SPEAR.get(),
                        ResourceLocation.withDefaultNamespace("throwing"),
                        (stack, level, entity, seed) -> {
                            if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
                                return 1.0F;
                            }
                            return 0.0F;
                        }
                );
            });

        }
        @SubscribeEvent
        public static void onClientExtensions(RegisterClientExtensionsEvent event) {
            event.registerItem( new IClientItemExtensions() {
                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    return new SpearItemRenderer(
                            Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                            Minecraft.getInstance().getEntityModels()
                    );
                }
            }, DelabItems.WOODEN_SPEAR, DelabItems.STONE_SPEAR, DelabItems.IRON_SPEAR,
                    DelabItems.GOLDEN_SPEAR, DelabItems.DIAMOND_SPEAR, DelabItems.NETHERITE_SPEAR);
        }

    }
}
