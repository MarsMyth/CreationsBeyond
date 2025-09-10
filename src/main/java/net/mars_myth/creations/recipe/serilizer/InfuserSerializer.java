package net.mars_myth.creations.recipe.serilizer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.mars_myth.creations.recipe.InfuserRecipe;
import net.mars_myth.creations.recipe.InfuserRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class InfuserSerializer implements RecipeSerializer<InfuserRecipe> {

    // Custom Codec for ItemStack
    public static final Codec<ItemStack> ITEMSTACK_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item").forGetter(itemStack -> Registries.ITEM.getId(itemStack.getItem()).toString()),
                    Codec.INT.fieldOf("count").forGetter(ItemStack::getCount)
            ).apply(instance, (item, count) -> new ItemStack(Registries.ITEM.get(Identifier.tryParse(item)), count))
    );

    public static final MapCodec<InfuserRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient1").forGetter(InfuserRecipe::getinputItem1),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient2").forGetter(InfuserRecipe::getinputItem2),
            ITEMSTACK_CODEC.fieldOf("result").forGetter(InfuserRecipe::output)
    ).apply(inst, InfuserRecipe::new));

    public static final PacketCodec<RegistryByteBuf, InfuserRecipe> STREAM_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, InfuserRecipe::getinputItem1,
            Ingredient.PACKET_CODEC, InfuserRecipe::getinputItem2,
            ItemStack.PACKET_CODEC,
            InfuserRecipe::output,
            InfuserRecipe::new
    );



    @Override
    public MapCodec<InfuserRecipe> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, InfuserRecipe> packetCodec() {
        return STREAM_CODEC;
    }
}

