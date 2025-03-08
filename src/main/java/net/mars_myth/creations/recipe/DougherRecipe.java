package net.mars_myth.creations.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.mars_myth.creations.init.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record DougherRecipe(Ingredient inputItem, ItemStack output) implements Recipe<DougherRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(DougherRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(DougherRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DOUGHER_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DOUGHER_TYPE;
    }

    public static class Serializer implements RecipeSerializer<DougherRecipe> {
        public static final MapCodec<DougherRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(DougherRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(DougherRecipe::output)
        ).apply(inst, DougherRecipe::new));
        public static final PacketCodec<RegistryByteBuf, DougherRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, DougherRecipe::inputItem,
                        ItemStack.PACKET_CODEC, DougherRecipe::output,
                        DougherRecipe::new);

        @Override
        public MapCodec<DougherRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DougherRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}