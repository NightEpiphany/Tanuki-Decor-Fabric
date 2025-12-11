package com.moigferdsrte.tanukidecor.mixin;

import com.moigferdsrte.tanukidecor.events.TagsUpdatedEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.TagCollector;
import net.minecraft.core.RegistryAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TagCollector.class)
public class TagCollectorMixin {

    @Inject(method = "updateTags", at = @At("RETURN"))
    public void updateTags(RegistryAccess registryAccess, boolean bl, CallbackInfo ci) {
        var event = new TagsUpdatedEvent(registryAccess, true, bl);
        event.post();
    }
}
