package net.crocodil.delab.items;

import net.crocodil.delab.Delab;
import net.crocodil.delab.entity.DelabEntityTypes;
import net.crocodil.delab.items.Hammers.AbominationHammerItem;
import net.crocodil.delab.items.Hammers.HammerItem;
import net.crocodil.delab.items.balls.FrozenBallItem;
import net.crocodil.delab.items.balls.MudBallItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class DelabItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Delab.MODID);

    public static final DeferredItem<Item> RECHARGE_CRYSTAL = ITEMS.register("recharge_crystal",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ABOMINATION_DUST = ITEMS.register("abomination_dust",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROZEN_CORE = ITEMS.register("frozen_core",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ABOMINATION_INGOT = ITEMS.register("abomination_ingot",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROZEN_INGOT = ITEMS.register("frozen_ingot",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FROZEN_FLESH = ITEMS.register("frozen_flesh",
            ()-> new Item(new Item.Properties().food(DelabFoodProperties.FROZEN_FLESH)));

    public static final DeferredItem<Item> FRESH_FLESH = ITEMS.register("fresh_flesh",
            ()-> new Item(new Item.Properties().food(DelabFoodProperties.FRESH_FLESH)));


    public static final DeferredItem<MudBallItem> MUD_BALL = ITEMS.register("mud_ball",
            ()-> new MudBallItem(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<FrozenBallItem> FROZEN_BALL = ITEMS.register("frozen_ball",
            ()-> new FrozenBallItem(new Item.Properties().stacksTo(16)));


    public static final DeferredItem<Item> ADVENTURE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("adventure_upgrade_smithing_template",
            DelabSmithingTemplateItem::createAdventureUpgradeTemplate);

    public  static final  DeferredItem<AxeItem> FROZEN_AXE = ITEMS.register("frozen_axe",
            () -> new AxeItem(DelabToolTiers.FROZEN_INGOT, new
                    Item.Properties().attributes(AxeItem.createAttributes(DelabToolTiers.FROZEN_INGOT, 6.0F, -3.1F)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.frozen_axe_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


    public  static final  DeferredItem<DaggerItem> WOODEN_DAGGER = ITEMS.register("wooden_dagger",
            () -> new DaggerItem(Tiers.WOOD, new DaggerItem.Properties().attributes(DaggerItem.createAttributes(Tiers.WOOD, 1, -2F))));
    public  static final  DeferredItem<DaggerItem> STONE_DAGGER = ITEMS.register("stone_dagger",
            () -> new DaggerItem(Tiers.STONE, new Item.Properties().attributes(DaggerItem.createAttributes(Tiers.STONE, 1, -2F))));
    public  static final  DeferredItem<DaggerItem> IRON_DAGGER = ITEMS.register("iron_dagger",
            () -> new DaggerItem(Tiers.IRON, new Item.Properties().attributes(DaggerItem.createAttributes(Tiers.IRON, 1, -2F))));
    public  static final  DeferredItem<DaggerItem> GOLDEN_DAGGER = ITEMS.register("golden_dagger",
            () -> new DaggerItem(Tiers.GOLD, new Item.Properties().attributes(DaggerItem.createAttributes(Tiers.GOLD, 1, -2F))));
    public  static final  DeferredItem<DaggerItem> DIAMOND_DAGGER = ITEMS.register("diamond_dagger",
            () -> new DaggerItem(Tiers.DIAMOND, new Item.Properties().attributes(DaggerItem.createAttributes(Tiers.DIAMOND, 1, -2F))));
    public  static final  DeferredItem<DaggerItem> NETHERITE_DAGGER = ITEMS.register("netherite_dagger",
            () -> new DaggerItem(Tiers.NETHERITE, new Item.Properties().fireResistant().attributes(DaggerItem.createAttributes(Tiers.NETHERITE, 1, -2.4F))));

    public  static final  DeferredItem<HammerItem> WOODEN_HAMMER = ITEMS.register("wooden_hammer",
            () -> new HammerItem(Tiers.WOOD, new DaggerItem.Properties().attributes(HammerItem.createAttributes(Tiers.WOOD, 5, -3.0F))));
    public  static final  DeferredItem<HammerItem> STONE_HAMMER = ITEMS.register("stone_hammer",
            () -> new HammerItem(Tiers.STONE, new Item.Properties().attributes(HammerItem.createAttributes(Tiers.STONE, 5, -3.0F))));
    public  static final  DeferredItem<HammerItem> IRON_HAMMER = ITEMS.register("iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties().attributes(HammerItem.createAttributes(Tiers.IRON, 5, -3.0F))));
    public  static final  DeferredItem<HammerItem> GOLDEN_HAMMER = ITEMS.register("golden_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties().attributes(HammerItem.createAttributes(Tiers.GOLD, 5, -3.0F))));
    public  static final  DeferredItem<HammerItem> DIAMOND_HAMMER = ITEMS.register("diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties().attributes(HammerItem.createAttributes(Tiers.DIAMOND, 5, -3.0F))));
    public  static final  DeferredItem<HammerItem> NETHERITE_HAMMER = ITEMS.register("netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties().fireResistant()
                    .attributes(HammerItem.createAttributes(Tiers.NETHERITE, 5, -3.0F))));

    public  static final  DeferredItem<AbominationHammerItem> ABOMINATION_HAMMER = ITEMS.register("abomination_hammer",
            () -> new AbominationHammerItem(DelabToolTiers.ABOMINATION_INGOT, new Item.Properties().fireResistant()
                    .attributes(AbominationHammerItem.createAttributes(DelabToolTiers.ABOMINATION_INGOT, 5, -3.0F)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.abomination_hammer_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public  static final  DeferredItem<SpearItem> WOODEN_SPEAR = ITEMS.register("wooden_spear",
            () -> new SpearItem(Tiers.WOOD, new DaggerItem.Properties().attributes(SpearItem.createAttributes(Tiers.WOOD, 2, -2.6F))));
    public  static final  DeferredItem<SpearItem> STONE_SPEAR = ITEMS.register("stone_spear",
            () -> new SpearItem(Tiers.STONE, new Item.Properties().attributes(SpearItem.createAttributes(Tiers.STONE, 2, -2.6F))));
    public  static final  DeferredItem<SpearItem> IRON_SPEAR = ITEMS.register("iron_spear",
            () -> new SpearItem(Tiers.IRON, new Item.Properties().attributes(SpearItem.createAttributes(Tiers.IRON, 2, -2.6F))));
    public  static final  DeferredItem<SpearItem> GOLDEN_SPEAR = ITEMS.register("golden_spear",
            () -> new SpearItem(Tiers.GOLD, new Item.Properties().attributes(SpearItem.createAttributes(Tiers.GOLD, 2, -2.6F))));
    public  static final  DeferredItem<SpearItem> DIAMOND_SPEAR = ITEMS.register("diamond_spear",
            () -> new SpearItem(Tiers.DIAMOND, new Item.Properties().attributes(SpearItem.createAttributes(Tiers.DIAMOND, 2, -2.6F))));
    public  static final  DeferredItem<SpearItem> NETHERITE_SPEAR = ITEMS.register("netherite_spear",
            () -> new SpearItem(Tiers.NETHERITE, new Item.Properties().fireResistant()
                    .attributes(SpearItem.createAttributes(Tiers.NETHERITE, 2, -2.6F))));

    public static final DeferredItem<Item> WOODEN_SPEAR_INVENTORY = ITEMS.register("wooden_spear_inventory",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STONE_SPEAR_INVENTORY = ITEMS.register("stone_spear_inventory",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_SPEAR_INVENTORY = ITEMS.register("iron_spear_inventory",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_SPEAR_INVENTORY = ITEMS.register("golden_spear_inventory",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_SPEAR_INVENTORY = ITEMS.register("diamond_spear_inventory",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_SPEAR_INVENTORY = ITEMS.register("netherite_spear_inventory",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<TieredBowItem> ABOMINATION_BOW = ITEMS.register("abomination_bow",
            ()-> new TieredBowItem(new Item.Properties(), DelabToolTiers.ABOMINATION_INGOT){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.abomination_bow_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


    public  static final DeferredItem<TieredBowItem> FROZEN_BOW = ITEMS.register("frozen_bow",
            ()-> new TieredBowItem(new Item.Properties(), DelabToolTiers.FROZEN_INGOT){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.frozen_bow_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ArmorItem> ABOMINATION_HELMET = ITEMS.register("abomination_helmet",
            () -> new ArmorItem(DelabArmorMaterials.ABOMINATION, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.abomination_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ArmorItem> ABOMINATION_CHESTPLATE = ITEMS.register("abomination_chestplate",
            () -> new ArmorItem(DelabArmorMaterials.ABOMINATION, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.abomination_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ArmorItem> ABOMINATION_LEGGINGS = ITEMS.register("abomination_leggings",
            () -> new ArmorItem(DelabArmorMaterials.ABOMINATION, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.abomination_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> ABOMINATION_BOOTS = ITEMS.register("abomination_boots",
            () -> new ArmorItem(DelabArmorMaterials.ABOMINATION, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.abomination_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> FROZEN_HELMET = ITEMS.register("frozen_helmet",
            () -> new ArmorItem(DelabArmorMaterials.FROZEN, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.frozen_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ArmorItem> FROZEN_CHESTPLATE = ITEMS.register("frozen_chestplate",
            () -> new ArmorItem(DelabArmorMaterials.FROZEN, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.frozen_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<ArmorItem> FROZEN_LEGGINGS = ITEMS.register("frozen_leggings",
            () -> new ArmorItem(DelabArmorMaterials.FROZEN, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.frozen_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<ArmorItem> FROZEN_BOOTS = ITEMS.register("frozen_boots",
            () -> new ArmorItem(DelabArmorMaterials.FROZEN, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19)))
            {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
                {
                    if(!Screen.hasControlDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.delab.ctrl_down"));
                    } else {
                        tooltipComponents.add(Component.translatable("tooltip.delab.frozen_armor_tooltip"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> MUDAUR_SPAWN_EGG = ITEMS.register("mudaur_spawn_egg",
            () -> new DeferredSpawnEggItem(DelabEntityTypes.MUDAUR, 0x874b0f, 7969893,
                    new Item.Properties()));
    public static final DeferredItem<Item> FROZEN_COWING_SPAWN_EGG = ITEMS.register("frozen_cowing_spawn_egg",
            () -> new DeferredSpawnEggItem(DelabEntityTypes.FROZEN_COWING, 4470310, 14543594,
                    new Item.Properties()));


    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
