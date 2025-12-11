/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.storage;

import com.moigferdsrte.tanukidecor.TDRegistry;
import com.moigferdsrte.tanukidecor.block.RotatingTallBlock;
import com.moigferdsrte.tanukidecor.block.entity.StorageBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TallStorageBlock extends RotatingTallBlock implements EntityBlock {

    private final BlockEntityType<StorageBlockEntity> blockEntity;
    private final SoundEvent openSound;

    public TallStorageBlock(final VoxelShape upperShape, final VoxelShape lowerShape,
                            final BlockEntityType<StorageBlockEntity> blockEntity, Properties pProperties) {
        this(upperShape, lowerShape, blockEntity, SoundEvents.BARREL_OPEN, pProperties);
    }

    public TallStorageBlock(final VoxelShape upperShape, final VoxelShape lowerShape,
                            final BlockEntityType<StorageBlockEntity> blockEntity,
                            final SoundEvent openSound, Properties pProperties) {
        super(pProperties, RotatingTallBlock.createShapeBuilder(upperShape, lowerShape));
        this.blockEntity = blockEntity;
        this.openSound = openSound;
    }

    /// / CONTAINER ////

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        return StorageBlockEntity.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult, this.openSound);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            StorageBlockEntity.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    /// / BLOCK ENTITY ////

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (pPos.equals(getDelegatePos(pState, pPos))) {
            return this.blockEntity.create(pPos, pState);
        }
        return TDRegistry.BlockEntityReg.STORAGE_DELEGATE.create(pPos, pState);
    }

    /// / REDSTONE ////

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }
}
