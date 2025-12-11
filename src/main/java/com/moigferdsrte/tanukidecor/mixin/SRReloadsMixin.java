package com.moigferdsrte.tanukidecor.mixin;

import com.moigferdsrte.tanukidecor.events.TagsUpdatedEvent;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.ReloadableServerResources;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReloadableServerResources.class)
public class SRReloadsMixin {
    @Shadow
    @Final
    private ReloadableServerRegistries.Holder fullRegistryHolder;

    @Inject(method = "updateRegistryTags()V", at = @At("RETURN"))
    private void updateRegistryTags(CallbackInfo ci) {
        var event = new TagsUpdatedEvent(this.fullRegistryHolder.get(), false, false);
        event.post();
    }

}
