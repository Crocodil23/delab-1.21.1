package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
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

    }
}
