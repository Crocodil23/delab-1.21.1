package net.crocodil.delab.blocks.entityes;

import net.crocodil.delab.DelabTags;
import net.crocodil.delab.blocks.AlloysFurnaceBlock;
import net.crocodil.delab.gui.AlloysFurnace.AlloyFurnaceMenu;
import net.crocodil.delab.items.DelabItems;
import net.crocodil.delab.recipes.DelabRecipes;
import net.crocodil.delab.recipes.alloysFurnace.AlloysFurnaceRecipe;
import net.crocodil.delab.recipes.alloysFurnace.AlloysFurnaceRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloysFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == FUEL) {
                return isFuel(stack);
            } else if(slot == RESULT)
                return false;
            else
                return  true;
        }
    };
    private static final int INGREDIENT_ONE = 0;
    private static final int INGREDIENT_TWO = 1;
    private static final int INGREDIENT_THREE = 2;
    private static final int FUEL = 3;
    private static final int RESULT = 4;

    protected  final ContainerData data;

    private int progress = 0;
    private int progress_max = 200;
    private int lit = 0;
    private int lit_duration = 0;
    public AlloysFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(DelabBlockEntityes.ALLOYS_FURNACE_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> AlloysFurnaceBlockEntity.this.progress;
                    case 1 -> AlloysFurnaceBlockEntity.this.progress_max;
                    case 2 -> AlloysFurnaceBlockEntity.this.lit;
                    case 3 -> AlloysFurnaceBlockEntity.this.lit_duration;
                    default -> 0;
                };

            }

            @Override
            public void set(int i, int value) {
                switch (i){
                    case 0: AlloysFurnaceBlockEntity.this.progress = value;
                    case 1: AlloysFurnaceBlockEntity.this.progress_max = value;
                    case 2: AlloysFurnaceBlockEntity.this.lit = value;
                    case 3: AlloysFurnaceBlockEntity.this.lit_duration = value;

                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.delab.alloys_furnace");
    }
    public boolean isLit()
    {
        return  lit > 0;
    }
    public static boolean isFuel(ItemStack stack) {
        return stack.getBurnTime((RecipeType)null) > 0;
    }
    public void tick(Level level1, BlockPos blockPos, BlockState blockState)
    {
        if(isLit())
            lit--;
        else
        {
            if(isFuel(itemHandler.getStackInSlot(FUEL)) && hasRecipe())
            {
                lit = itemHandler.getStackInSlot(FUEL).getBurnTime(null);
                lit_duration = lit;
                itemHandler.extractItem(FUEL,1,false);
            }

        }
        if(hasRecipe()) {
            if(isLit())
            {
                progress++;
                if(progress == progress_max) {
                    craftItem();
                    progress = 0;
                }
            } else
                progress -= 2;

        } else {
            progress = 0;
        }


        level1.setBlock(blockPos, blockState.setValue(AlloysFurnaceBlock.LIT, isLit()), 3);
        setChanged(level1, blockPos, blockState);

    }

    private void craftItem()
    {
        Optional<RecipeHolder<AlloysFurnaceRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INGREDIENT_ONE, 1, false);
        itemHandler.extractItem(INGREDIENT_TWO, 1, false);
        itemHandler.extractItem(INGREDIENT_THREE, 1, false);
        itemHandler.setStackInSlot(RESULT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(RESULT).getCount() + output.getCount()));
    }

    private boolean IsCorrectIngredients()
    {
        ItemStack[] stacks = new ItemStack[3];
        stacks[0] = itemHandler.getStackInSlot(INGREDIENT_ONE);
        stacks[1] = itemHandler.getStackInSlot(INGREDIENT_TWO);
        stacks[2] = itemHandler.getStackInSlot(INGREDIENT_THREE);

        Item[] items = new Item[3];
        items[0] = Items.COPPER_INGOT;
        items[1] = Items.IRON_INGOT;
        items[2] = Items.GOLD_INGOT;

        for (int i = 0; i < 3; i++) {
            boolean isCorrect = false;
            for (int j = 0; j < 3; j++) {
                if(stacks[j].is(items[i]))
                {
                    isCorrect = true;
                    break;
                }
            }
            if (!isCorrect)
                return false;
        }
        return true;

    }
    private boolean hasRecipe() {
        Optional<RecipeHolder<AlloysFurnaceRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInputResultSlot(output);
    }

    private Optional<RecipeHolder<AlloysFurnaceRecipe>> getCurrentRecipe() {
        NonNullList<ItemStack> inputs = NonNullList.create();
        inputs.add(itemHandler.getStackInSlot(INGREDIENT_ONE));
        inputs.add(itemHandler.getStackInSlot(INGREDIENT_TWO));
        inputs.add(itemHandler.getStackInSlot(INGREDIENT_THREE));
        return this.level.getRecipeManager()
                .getRecipeFor(DelabRecipes.ALLOYS_FURNACE_TYPE.get(), new AlloysFurnaceRecipeInput(inputs), level);
    }

    private boolean canInputResultSlot(ItemStack result)
    {
        return itemHandler.getStackInSlot(RESULT).isEmpty() ||
                (itemHandler.getStackInSlot(RESULT).is(result.getItem())
                && itemHandler.getStackInSlot(RESULT).getCount() <= 63);
    }


    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AlloyFurnaceMenu(i, inventory, this, this.data);
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("alloys_furnace.progress", progress);
        pTag.putInt("alloys_furnace..max_progress", progress_max);
        pTag.putInt("alloys_furnace.lit", lit);
        pTag.putInt("alloys_furnace..lit_duration", lit_duration);


        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("alloys_furnace..progress");
        progress_max = pTag.getInt("alloys_furnace..max_progress");
        lit = pTag.getInt("alloys_furnace.lit");
        lit_duration = pTag.getInt("alloys_furnace..lit_duration");
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


}
