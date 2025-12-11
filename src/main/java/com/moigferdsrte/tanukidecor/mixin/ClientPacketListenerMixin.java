package com.moigferdsrte.tanukidecor.mixin;

import com.moigferdsrte.tanukidecor.events.RecipesUpdatedEvent;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Shadow
    @Final
    private RecipeManager recipeManager;

    @Inject(method = "handleUpdateRecipes", at = @At("TAIL"))
    public void handleUpdateRecipes(ClientboundUpdateRecipesPacket clientboundUpdateRecipesPacket, CallbackInfo ci) {
       var event = new RecipesUpdatedEvent(this.recipeManager);
       event.post();
    }

}
