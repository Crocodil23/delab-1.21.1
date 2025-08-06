package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.blocks.DelabBlocks;
import net.minecraft.ResourceLocationException;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredBlock;

public class DelabBlockStateProvider extends BlockStateProvider {
    public DelabBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Delab.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        String tmp = getBlockName(DelabBlocks.SEA_WORKSHOP.get());
        simpleBlockWithItem(DelabBlocks.SEA_WORKSHOP.get(), models().cubeBottomTop(
                tmp,
                getTexture(tmp +"_side"),
                getTexture(tmp +"_bottom"),
                getTexture(tmp +"_top")

        ));
    }

    public ResourceLocation getTexture(String path) {
        return ResourceLocation.fromNamespaceAndPath(Delab.MODID, "block/" + path);
    }
    private String getBlockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

}
