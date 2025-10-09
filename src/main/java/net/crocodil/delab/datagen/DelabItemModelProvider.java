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
        basicItem(DelabItems.FROZEN_CORE.get());
        basicItem(DelabItems.HARD_LEAF.get());
        basicItem(DelabItems.EVIL_SPITTER_SEED.get());
        basicItem(DelabItems.FURIOUS_SPIRIT.get());
        basicItem(DelabItems.ABOMINATION_INGOT.get());
        basicItem(DelabItems.FROZEN_INGOT.get());
        basicItem(DelabItems.WILD_INGOT.get());
        basicItem(DelabItems.FROZEN_FLESH.get());
        basicItem(DelabItems.FRESH_FLESH.get());
        basicItem(DelabItems.ANCIENT_TOFU.get());
        basicItem(DelabItems.DEFENDER_OFFERING.get());
        basicItem(DelabItems.MUD_BALL.get());
        basicItem(DelabItems.FROZEN_BALL.get());

        basicItem(DelabItems.ABOMINATION_HELMET.get());
        basicItem(DelabItems.ABOMINATION_CHESTPLATE.get());
        basicItem(DelabItems.ABOMINATION_LEGGINGS.get());
        basicItem(DelabItems.ABOMINATION_BOOTS.get());

        basicItem(DelabItems.FROZEN_HELMET.get());
        basicItem(DelabItems.FROZEN_CHESTPLATE.get());
        basicItem(DelabItems.FROZEN_LEGGINGS.get());
        basicItem(DelabItems.FROZEN_BOOTS.get());

        handheldItem(DelabItems.WOODEN_HAMMER.asItem());
        handheldItem(DelabItems.STONE_HAMMER.asItem());
        handheldItem(DelabItems.IRON_HAMMER.asItem());
        handheldItem(DelabItems.GOLDEN_HAMMER.asItem());
        handheldItem(DelabItems.DIAMOND_HAMMER.asItem());
        handheldItem(DelabItems.NETHERITE_HAMMER.asItem());
        handheldItem(DelabItems.ABOMINATION_HAMMER.asItem());
        handheldItem(DelabItems.FROZEN_AXE.asItem());
        handheldItem(DelabItems.WILD_KATANA.asItem());


        simpleBlockItem(DelabBlocks.ALLOYS_FURNACE.get());

        withExistingParent(DelabItems.MUDAUR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(DelabItems.FROZEN_COWING_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
