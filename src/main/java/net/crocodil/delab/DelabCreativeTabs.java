package net.crocodil.delab;

import net.crocodil.delab.blocks.DelabBlocks;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DelabCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> DELAB_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Delab.MODID);

    public static final Supplier<CreativeModeTab> DELAB =
            DELAB_TABS.register("delab_tab", () -> CreativeModeTab.builder()
                    .icon(()-> new ItemStack(DelabItems.RECHARGE_CRYSTAL.get()))
                    .title(Component.translatable("creativetab.delab.delab_tab"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(DelabItems.ABOMINATION_DUST);
                        output.accept(DelabItems.ABOMINATION_INGOT);
                        output.accept(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(DelabItems.RECHARGE_CRYSTAL);

                        output.accept(DelabItems.WOODEN_DAGGER);
                        output.accept(DelabItems.STONE_DAGGER);
                        output.accept(DelabItems.IRON_DAGGER);
                        output.accept(DelabItems.GOLDEN_DAGGER);
                        output.accept(DelabItems.DIAMOND_DAGGER);
                        output.accept(DelabItems.NETHERITE_DAGGER);

                        output.accept(DelabItems.WOODEN_HAMMER);
                        output.accept(DelabItems.STONE_HAMMER);
                        output.accept(DelabItems.IRON_HAMMER);
                        output.accept(DelabItems.GOLDEN_HAMMER);
                        output.accept(DelabItems.DIAMOND_HAMMER);
                        output.accept(DelabItems.NETHERITE_HAMMER);
                        output.accept(DelabItems.ABOMINATION_HAMMER);

                        output.accept(DelabItems.WOODEN_SPEAR);
                        output.accept(DelabItems.STONE_SPEAR);
                        output.accept(DelabItems.IRON_SPEAR);
                        output.accept(DelabItems.GOLDEN_SPEAR);
                        output.accept(DelabItems.DIAMOND_SPEAR);
                        output.accept(DelabItems.NETHERITE_SPEAR);

                        output.accept(DelabItems.ABOMINATION_HELMET);
                        output.accept(DelabItems.ABOMINATION_CHESTPLATE);
                        output.accept(DelabItems.ABOMINATION_LEGGINGS);
                        output.accept(DelabItems.ABOMINATION_BOOTS);

                        output.accept(DelabItems.MUD_BALL);
                        output.accept(DelabItems.ABOMINATION_BOW);


                        output.accept(DelabBlocks.SEA_WORKSHOP);
                        output.accept(DelabBlocks.ALLOYS_FURNACE);

                        output.accept(DelabItems.MUDAUR_SPAWN_EGG);
                    }).build());


    public static void register(IEventBus bus)
    {
        DELAB_TABS.register(bus);
    }
}
