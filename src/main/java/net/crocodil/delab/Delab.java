package net.crocodil.delab;

import com.mojang.logging.LogUtils;
import net.crocodil.delab.client.render.*;
import net.crocodil.delab.entity.DelabEntityTypes;
import net.crocodil.delab.blocks.DelabBlocks;
import net.crocodil.delab.blocks.entityes.DelabBlockEntityes;
import net.crocodil.delab.effects.DelabMobEffects;
import net.crocodil.delab.gui.AlloysFurnace.AlloysFurnaceScreen;
import net.crocodil.delab.gui.DelabMenuTypes;
import net.crocodil.delab.items.DelabArmorMaterials;
import net.crocodil.delab.items.DelabItemProperties;
import net.crocodil.delab.items.DelabItems;
import net.crocodil.delab.loot.DelabLootModifiers;
import net.crocodil.delab.recipes.DelabRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

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
        DelabBlockEntityes.register(modEventBus);
        DelabMenuTypes.register(modEventBus);
        DelabMobEffects.register(modEventBus);
        DelabSounds.register(modEventBus);
        DelabEntityTypes.register(modEventBus);
        DelabRecipes.register(modEventBus);
        DelabArmorMaterials.register(modEventBus);
        DelabLootModifiers.register(modEventBus);

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
            event.accept(DelabItems.ABOMINATION_HAMMER);

            event.accept(DelabItems.WOODEN_SPEAR);
            event.accept(DelabItems.STONE_SPEAR);
            event.accept(DelabItems.IRON_SPEAR);
            event.accept(DelabItems.GOLDEN_SPEAR);
            event.accept(DelabItems.DIAMOND_SPEAR);
            event.accept(DelabItems.NETHERITE_SPEAR);

            event.accept(DelabItems.FROZEN_AXE);

            event.accept(DelabItems.ABOMINATION_HELMET);
            event.accept(DelabItems.ABOMINATION_CHESTPLATE);
            event.accept(DelabItems.ABOMINATION_LEGGINGS);
            event.accept(DelabItems.ABOMINATION_BOOTS);

            event.accept(DelabItems.FROZEN_HELMET);
            event.accept(DelabItems.FROZEN_CHESTPLATE);
            event.accept(DelabItems.FROZEN_LEGGINGS);
            event.accept(DelabItems.FROZEN_BOOTS);

            event.accept(DelabItems.MUD_BALL);
            event.accept(DelabItems.FROZEN_BALL);
            event.accept(DelabItems.FROZEN_BOW);
            event.accept(DelabItems.ABOMINATION_BOW);

        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(DelabItems.ABOMINATION_DUST);
            event.accept(DelabItems.FROZEN_CORE);
            event.accept(DelabItems.HARD_LEAF);
            event.accept(DelabItems.FURIOUS_SPIRIT);
            event.accept(DelabItems.ABOMINATION_INGOT);
            event.accept(DelabItems.FROZEN_INGOT);
            event.accept(DelabItems.WILD_INGOT);

            event.accept(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE);
            event.accept(DelabItems.RECHARGE_CRYSTAL);

        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
        {
            event.accept(DelabBlocks.ALLOYS_FURNACE);
            event.accept(DelabBlocks.SEA_WORKSHOP);
        }
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
        {
            event.accept(DelabItems.MUDAUR_SPAWN_EGG);
            event.accept(DelabItems.FROZEN_COWING_SPAWN_EGG);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
        {
            event.accept(DelabItems.FROZEN_FLESH);
            event.accept(DelabItems.FRESH_FLESH);
            event.accept(DelabItems.ANCIENT_TOFU);
            event.accept(DelabItems.DEFENDER_OFFERING);
        }
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            event.accept(DelabItems.EVIL_SPITTER_SEED);
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
            EntityRenderers.register(DelabEntityTypes.THROWING_SPEAR.get(), ThrowingSpearRenderer::new);
            EntityRenderers.register(DelabEntityTypes.MUD_BALL.get(), ThrownItemRenderer::new);
            EntityRenderers.register(DelabEntityTypes.FROZEN_BALL.get(), ThrownItemRenderer::new);
            EntityRenderers.register(DelabEntityTypes.MUDAUR.get(), MudaurRenderer::new);
            EntityRenderers.register(DelabEntityTypes.FROZEN_COWING.get(), FrozenCowingRenderer::new);


            DelabItemProperties.CreateCustomProperties();


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

            event.registerItem(new AbominationClientExtensions(),
                    DelabItems.ABOMINATION_CHESTPLATE,
                    DelabItems.ABOMINATION_HELMET,
                    DelabItems.ABOMINATION_LEGGINGS,
                    DelabItems.ABOMINATION_BOOTS);

            event.registerItem(new FrozenClientExtentions(),
                    DelabItems.FROZEN_CHESTPLATE,
                    DelabItems.FROZEN_HELMET,
                    DelabItems.FROZEN_LEGGINGS,
                    DelabItems.FROZEN_BOOTS);

        }
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(DelabMenuTypes.ALLOYS_FURNACE_MENU.get(), AlloysFurnaceScreen::new);
        }
    }
}
