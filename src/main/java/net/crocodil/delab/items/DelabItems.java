package net.crocodil.delab.items;

import net.crocodil.delab.Delab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DelabItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Delab.MODID);

    public static final DeferredItem<Item> RECHARGE_CRYSTAL = ITEMS.register("recharge_crystal",
            ()-> new Item(new Item.Properties()));
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

    public static void register(IEventBus bus)
    {
        ITEMS.register(bus);
    }
}
