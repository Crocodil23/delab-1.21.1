package net.crocodil.delab.blocks;

import net.crocodil.delab.Delab;
import net.crocodil.delab.items.DelabItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DelabBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Delab.MODID);
    public static final DeferredBlock<Block> SEA_WORKSHOP = registerBlock("sea_workshop",
             ()-> new Block(BlockBehaviour.Properties.of()
                     .strength(2.0F, 5.0F)
                     .sound(SoundType.STONE)
                     .requiresCorrectToolForDrops()));

    public static final DeferredBlock<AlloysFurnaceBlock> ALLOYS_FURNACE = registerBlock("alloys_furnace",
            ()-> new AlloysFurnaceBlock(BlockBehaviour.Properties.of()
                    .strength(3.5F)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> state.getValue(AlloysFurnaceBlock.LIT) ? 13 : 0)
                    .requiresCorrectToolForDrops()));


    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> tmp = BLOCKS.register(name, block);
        registrBlockItem(name, tmp);
        return tmp;
    }

    public static <T extends Block> void registrBlockItem(String name, DeferredBlock<T> block)
    {
        DelabItems.ITEMS.register(name,()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
}
