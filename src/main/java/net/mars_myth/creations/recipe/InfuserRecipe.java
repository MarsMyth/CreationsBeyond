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

public record InfuserRecipe(Ingredient inputItem, ItemStack output) implements Recipe<InfuserRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(InfuserRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0)) && inputItem.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(InfuserRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
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
        return ModRecipes.INFUSER_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.INFUSER_TYPE;
    }

    public static class Serializer implements RecipeSerializer<InfuserRecipe> {
        public static final MapCodec<InfuserRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(InfuserRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(InfuserRecipe::output)
        ).apply(inst, InfuserRecipe::new));
        public static final PacketCodec<RegistryByteBuf, InfuserRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, InfuserRecipe::inputItem,
                        ItemStack.PACKET_CODEC, InfuserRecipe::output,
                        InfuserRecipe::new);

        @Override
        public MapCodec<InfuserRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, InfuserRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}