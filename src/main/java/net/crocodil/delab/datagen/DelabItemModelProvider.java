package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.blocks.DelabBlocks;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DelabItemModelProvider extends ItemModelProvider {
    public DelabItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Delab.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(DelabItems.RECHARGE_CRYSTAL.get());
        basicItem(DelabItems.ADVENTURE_UPGRADE_SMITHING_TEMPLATE.get());
        basicItem(DelabItems.ABOMINATION_DUST.get());
        basicItem(DelabItems.ABOMINATION_INGOT.get());
        basicItem(DelabItems.MUD_BALL.get());

        handheldItem(DelabItems.WOODEN_HAMMER.asItem());
        handheldItem(DelabItems.STONE_HAMMER.asItem());
        handheldItem(DelabItems.IRON_HAMMER.asItem());
        handheldItem(DelabItems.GOLDEN_HAMMER.asItem());
        handheldItem(DelabItems.DIAMOND_HAMMER.asItem());
        handheldItem(DelabItems.NETHERITE_HAMMER.asItem());

        simpleBlockItem(DelabBlocks.ALLOYS_FURNACE.get());


    }
}
