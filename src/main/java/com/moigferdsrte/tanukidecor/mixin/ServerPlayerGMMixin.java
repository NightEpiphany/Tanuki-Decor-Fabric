package com.moigferdsrte.tanukidecor.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import com.moigferdsrte.tanukidecor.block.RotatingMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.moigferdsrte.tanukidecor.block.RotatingMultiblock.FACING;

@Mixin(ServerPlayerGameMode.class)
public class ServerPlayerGMMixin {

    @Shadow
    protected ServerLevel level;

    @Inject(method = "destroyBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;playerWillDestroy(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/level/block/state/BlockState;", shift = At.Shift.AFTER))
    private void destroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0)BlockState blockState) {
        if (blockState.getBlock() instanceof RotatingMultiblock rotatingMultiblock) {
            BlockPos centerPos = rotatingMultiblock.getMultiblockHandler().getCenterPos(pos, blockState, blockState.getValue(FACING));
            if (centerPos.equals(pos)) {
                TanukiDecorFabric.LOGGER.debug("Special Center pos execution: {}" , centerPos);
                rotatingMultiblock.destroy(this.level, pos, blockState);
            }
        }
    }
}
