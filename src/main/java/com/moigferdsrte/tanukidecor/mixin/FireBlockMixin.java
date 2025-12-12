package com.moigferdsrte.tanukidecor.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import com.moigferdsrte.tanukidecor.block.api.IBlockStateExtension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    @Redirect(method = "checkBurnOut", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/FireBlock;getBurnOdds(Lnet/minecraft/world/level/block/state/BlockState;)I"))
    private int getBurnOdds(FireBlock instance, BlockState blockState, Level level, BlockPos pos, int chance, RandomSource random, int age) {
        if (BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getNamespace().equals(TanukiDecorFabric.MOD_ID)) {
            IBlockStateExtension extension = (IBlockStateExtension) blockState;
            return extension.getFlammability(level, pos);
        }
        return instance.getBurnOdds(blockState);
    }

    @Redirect(method = "getIgniteOdds(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)I", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"))
    private int getIgniteOdds(int a, int b, LevelReader levelReader, BlockPos blockPos, @Local BlockState blockState, @Local Direction direction) {
        if (BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getNamespace().equals(TanukiDecorFabric.MOD_ID)) {
            IBlockStateExtension extension = (IBlockStateExtension) blockState;
            return Math.max(extension.getFireSpreadSpeed(levelReader, blockPos.relative(direction), direction.getOpposite()), b);
        }return Math.max(a, b);
    }
}
