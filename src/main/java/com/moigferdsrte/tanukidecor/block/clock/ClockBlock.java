/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.clock;

import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import com.moigferdsrte.tanukidecor.block.RotatingBlock;
import com.moigferdsrte.tanukidecor.block.entity.ClockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class ClockBlock extends RotatingBlock implements EntityBlock, IChimeProvider {

    public static final SoundEvent NO_SOUND = null;

    protected final SoundEvent tickSound;
    protected final SoundEvent chimeSound;
    protected final BlockEntityType<ClockBlockEntity> blockEntity;

    /**
     * Simple constructor for a clock that takes up one block
     *
     * @param tickSound   the tick sound supplier, use {@link ClockBlock#NO_SOUND} to skip
     * @param chimeSound  the chime sound supplier, use {@link ClockBlock#NO_SOUND} to skip
     * @param shape       the clock shape in the default direction
     * @param blockEntity the block entity type supplier
     * @param pProperties the block properties
     */
    public ClockBlock(@Nullable SoundEvent tickSound, @Nullable SoundEvent chimeSound,
                      VoxelShape shape, @NotNull BlockEntityType<ClockBlockEntity> blockEntity,
                      Properties pProperties) {
        super(pProperties, RotatingBlock.createShapeBuilder(shape));
        this.tickSound = tickSound;
        this.chimeSound = chimeSound;
        this.blockEntity = blockEntity;
    }

    /// / CHIME PROVIDER ////

    @Nullable
    @Override
    public SoundEvent getTickSound(BlockState blockState) {
        return this.tickSound;
    }

    @Nullable
    @Override
    public SoundEvent getChimeSound(BlockState blockState) {
        return this.chimeSound;
    }

    /// / BLOCK ENTITY ////

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (blockEntity == null) {
            TanukiDecorFabric.LOGGER.warn("Attempted to create block entity for clock at {} with null block entity type", pPos);
            return null;
        }
        return blockEntity.create(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return !pLevel.isClientSide() ? (BlockEntityTicker<T>) (BlockEntityTicker<ClockBlockEntity>) (ClockBlockEntity::tick) : null;
    }
}
