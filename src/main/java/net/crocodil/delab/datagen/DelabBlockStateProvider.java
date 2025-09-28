package net.crocodil.delab.datagen;

import net.crocodil.delab.Delab;
import net.crocodil.delab.blocks.DelabBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DelabBlockStateProvider extends BlockStateProvider {
    public DelabBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Delab.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        String name = getBlockName(DelabBlocks.SEA_WORKSHOP.get());
        simpleBlockWithItem(DelabBlocks.SEA_WORKSHOP.get(), models().cubeBottomTop(
                name,
                getTexture(name +"_side"),
                getTexture(name +"_bottom"),
                getTexture(name +"_top")

        ));
        createFurnace(DelabBlocks.ALLOYS_FURNACE.get());

    }

    public ResourceLocation getTexture(String path) {
        return ResourceLocation.fromNamespaceAndPath(Delab.MODID, "block/" + path);
    }
    private String getBlockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
    private void createFurnace(Block furnace)
    {
        String furnaceName = getBlockName(furnace);

        ModelFile furnaceModel = models().orientable(furnaceName,
                getTexture(furnaceName + "_side"),
                getTexture(furnaceName + "_front"),
                getTexture(furnaceName + "_top"));

        ModelFile furnaceModelLit = models().orientable(furnaceName + "_on",
                getTexture(furnaceName + "_side"),
                getTexture(furnaceName + "_front_on"),
                getTexture(furnaceName + "_top"));

        Property<?> prop = furnace.getStateDefinition().getProperty("lit");
        BooleanProperty litProp;
        if (prop instanceof BooleanProperty) {
            litProp = (BooleanProperty) prop;
        } else {
            litProp = BlockStateProperties.LIT;
        }
        getVariantBuilder(furnace).forAllStates(state -> {
            boolean lit = state.getValue(litProp);
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot;
            switch (facing) {
                case SOUTH -> yRot = 180;
                case WEST  -> yRot = 270;
                case NORTH -> yRot = 0;
                case EAST  -> yRot = 90;
                default -> yRot = 0;
            }

            ModelFile chosen = lit ? furnaceModelLit : furnaceModel;
            return ConfiguredModel.builder()
                    .modelFile(chosen)
                    .rotationY(yRot)
                    .build();
        });

    }


}
