package com.moigferdsrte.tanukidecor.mixin;

import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin {

    @Shadow
    @Final
    private BlockEntityType<?> type;

    @Inject(method = "isValidBlockState", at = @At("HEAD"), cancellable = true)
    private void isValidBlockState(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getNamespace().equals(TanukiDecorFabric.MOD_ID) && this.type == null) cir.setReturnValue(true);
    }
}
