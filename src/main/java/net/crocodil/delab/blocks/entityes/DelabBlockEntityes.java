package net.crocodil.delab.blocks.entityes;

import net.crocodil.delab.Delab;
import net.crocodil.delab.blocks.DelabBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DelabBlockEntityes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Delab.MODID);

    public static final Supplier<BlockEntityType<AlloysFurnaceBlockEntity>> ALLOYS_FURNACE_BE =
            BLOCK_ENTITIES.register("alloys_furnace", () -> BlockEntityType.Builder.of(
                    AlloysFurnaceBlockEntity::new, DelabBlocks.ALLOYS_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}