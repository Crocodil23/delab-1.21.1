package net.crocodil.delab;

import net.crocodil.delab.blocks.DelabBlocks;

import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.enchants.DelabEnchantments;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
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

        }
    }
}
