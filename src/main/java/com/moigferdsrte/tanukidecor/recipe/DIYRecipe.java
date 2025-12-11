/*
 * Copyright (c) 2024 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.recipe;

import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import com.moigferdsrte.tanukidecor.TDRegistry;
import com.moigferdsrte.tanukidecor.block.entity.DIYWorkbenchBlockEntity;
import org.jetbrains.annotations.NotNull;

public class DIYRecipe implements Recipe<RecipeInput> {

    private final ItemStack result;

    public DIYRecipe(ItemStack result) {
        this.result = result;
    }

    @Override
    public boolean matches(RecipeInput pInput, Level pLevel) {
        for (int i = 0; i < 4; i++) {
            Ingredient ingredient = DIYWorkbenchBlockEntity.INGREDIENTS[i];
            ItemStack itemStack = pInput.getItem(i);
            if (!ingredient.test(itemStack)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(RecipeInput pInput, HolderLookup.Provider pProvider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return (pWidth * pHeight) >= 4;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider pProvider) {
        return this.result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return TDRegistry.RecipeReg.DIY_SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return TDRegistry.RecipeReg.DIY;
    }

    /// / SERIALIZER ////

    public static class Serializer implements RecipeSerializer<DIYRecipe> {

        public static final ResourceLocation CATEGORY = ResourceLocation.fromNamespaceAndPath(TanukiDecorFabric.MOD_ID, "diy");

        public static final MapCodec<DIYRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                ).apply(instance, DIYRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, DIYRecipe> STREAM_CODEC = StreamCodec.composite(
                ItemStack.STREAM_CODEC,
                recipe -> recipe.result,
                DIYRecipe::new
        );

        @Override
        public @NotNull MapCodec<DIYRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, DIYRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
