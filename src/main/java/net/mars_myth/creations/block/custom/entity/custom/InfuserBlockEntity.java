package net.mars_myth.creations.block.custom.entity.custom;


import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.mars_myth.creations.block.custom.entity.ImplementedInventory;
import net.mars_myth.creations.init.ModBlockEntities;
import net.mars_myth.creations.init.ModRecipes;
import net.mars_myth.creations.recipe.InfuserRecipe;
import net.mars_myth.creations.recipe.InfuserRecipeInput;
import net.mars_myth.creations.screen.custom.InfuserScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class InfuserBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;

    private int progress = 0;
    private int maxProgress = 70;
    private final int DEFAULT_MAX_PROGRESS = 70;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public InfuserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFUSER_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.creations.infuser");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new InfuserScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("mixing.progress", progress);
        nbt.putInt("mixing.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        this.progress = nbt.getInt("mixing.progress");
        this.maxProgress = nbt.getInt("mixing.max_progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private boolean hasCraftingFinished() {
        if (progress == DEFAULT_MAX_PROGRESS) {
            return true;
        }
        return false;
    }

    private void increaseCraftingProgress() {
        if (progress < maxProgress) {
            this.progress++;
        }
    }


    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        getCurrentRecipe().ifPresent(recipe -> {
            ItemStack output = recipe.value().output();
            // Only craft if the recipe is valid
            if (hasRecipe()) {
                // Remove ingredients and add the output item
                removeStack(INPUT_SLOT, 1);
                removeStack(INPUT_SLOT_2, 1);
                setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                        getStack(OUTPUT_SLOT).getCount() + output.getCount()));
            }
        });
    }


    private boolean hasRecipe() {
        Optional<RecipeEntry<InfuserRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        ItemStack output = recipe.get().value().output();
        return canInsertItemIntoOutputSlot(output) && canInsertAmountIntoOutputSlot(output.getCount());
    }

    public Optional<RecipeEntry<InfuserRecipe>> getCurrentRecipe() {
        return Objects.requireNonNull(this.getWorld()).getRecipeManager()
                .getFirstMatch(ModRecipes.INFUSING_TYPE, new InfuserRecipeInput(
                        inventory.get(0),
                        inventory.get(1)
                ), this.getWorld());
    }




    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        ItemStack current = getStack(OUTPUT_SLOT);
        return current.isEmpty() || current.getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int amount) {
        ItemStack current = getStack(OUTPUT_SLOT);
        return current.getCount() + amount <= current.getMaxCount();
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
