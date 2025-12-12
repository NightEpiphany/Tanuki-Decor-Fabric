package com.moigferdsrte.tanukidecor.block.api;

import com.moigferdsrte.tanukidecor.block.bed.IBedProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface IBlockStateExtension {
    private BlockState self() {
        return (BlockState) this;
    }

    default boolean isBed(BlockGetter level, BlockPos pos, LivingEntity sleeper) {
        if (self().getBlock() instanceof IBedProvider iBedProvider)
           return iBedProvider.isBed(self(), level, pos, sleeper);
        return self().getBlock() instanceof BedBlock;
    }

    default void setBedOccupied(Level level, BlockPos pos, LivingEntity sleeper, boolean occupied) {
        if (self().getBlock() instanceof IBedProvider iBedProvider)
            iBedProvider.setBedOccupied(self(), level, pos, sleeper, occupied);
    }

    default Direction getBedDirection(LevelReader level, BlockPos pos) {
        if (self().getBlock() instanceof IBedProvider iBedProvider)
            return iBedProvider.getBedDirection(self(), level, pos);
        return self().getBlock() instanceof BedBlock ? self().getValue(HorizontalDirectionalBlock.FACING) : null;
    }

    default int getFlammability(BlockGetter level, BlockPos pos) {
        if (self().getBlock() instanceof IBlockExtension iBlockExtension)
            return iBlockExtension.getFlammability(self(), level, pos);
        return ((FireBlock) Blocks.FIRE).getBurnOdds(self());
    }

    default int getFireSpreadSpeed(BlockGetter level, BlockPos pos, Direction face) {
        if (self().getBlock() instanceof IBlockExtension iBlockExtension)
            return iBlockExtension.getFireSpreadSpeed(self(), level, pos, face);
        return ((FireBlock) Blocks.FIRE).getIgniteOdds(self());
    }
}
