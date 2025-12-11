/**
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 **/

package com.moigferdsrte.tanukidecor.network;

import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeHolder;
import com.moigferdsrte.tanukidecor.menu.DIYWorkbenchMenu;
import com.moigferdsrte.tanukidecor.recipe.DIYRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record ServerBoundSelectDIYRecipePacket(ResourceLocation recipeId) implements CustomPacketPayload {

    public static final Type<ServerBoundSelectDIYRecipePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(TanukiDecorFabric.MOD_ID, "select_diy_recipe"));

    private static final TagKey<Item> DIY_BLACKLIST_TAG_KEY = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(TanukiDecorFabric.MOD_ID, "diy_blacklist"));

    public static final StreamCodec<FriendlyByteBuf, ServerBoundSelectDIYRecipePacket> STREAM_CODEC = StreamCodec.composite(
            ResourceLocation.STREAM_CODEC,
            ServerBoundSelectDIYRecipePacket::recipeId,
            ServerBoundSelectDIYRecipePacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    /**
     * Handles the packet when it is received on the server.
     *
     * @param payload the packet payload
     * @param ctx the packet context
     */
    public static void handle(final ServerBoundSelectDIYRecipePacket payload, ServerPlayNetworking.Context ctx) {
        ServerPlayer player = ctx.player();
        System.out.println(111);
            // validate menu
            if (!(player.containerMenu instanceof DIYWorkbenchMenu menu)) {
                return;
            }
            // validate crafting
            if (!TanukiDecorFabric.CONFIG.isDIYWorkbenchEnabled) {
                return;
            }
            // validate recipe
            final Optional<RecipeHolder<?>> oRecipe = player.level().getRecipeManager().byKey(payload.recipeId());
            if (oRecipe.isEmpty() || !(oRecipe.get().value() instanceof DIYRecipe recipe)) {
                return;
            }
            // validate result
            if (recipe.getResultItem(player.level().registryAccess()).is(DIY_BLACKLIST_TAG_KEY)) {
                return;
            }
            // update menu
            menu.setRecipe(recipe);
    }
}
