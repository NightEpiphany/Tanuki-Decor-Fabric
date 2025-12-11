/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.clock;

import com.moigferdsrte.tanukidecor.TDRegistry;
import com.moigferdsrte.tanukidecor.block.entity.ClockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class OwlClockBlock extends ClockBlock {

    public static final VoxelShape SHAPE = Shapes.or(
            box(4, 0, 12, 12, 10, 16),
            box(3, 3, 12.5D, 4, 8, 15.5D),
            box(12, 3, 12.5D, 13, 8, 15.5D),
            box(7.5D, 9, 11, 8.5D, 11, 12),
            Shapes.join(
                    box(3, 10, 12, 13, 16, 16),
                    Shapes.or(
                            box(6, 14, 12, 10, 15, 16),
                            box(5, 15, 12, 11, 16, 16)),
                    BooleanOp.ONLY_FIRST
            ));

    public OwlClockBlock(Properties pProperties) {
        super(TDRegistry.SoundReg.MEDIUM_CLOCK_TICK2, NO_SOUND,
                SHAPE, TDRegistry.BlockEntityReg.OWL_CLOCK, pProperties);
    }

    /// / PLACEMENT ////

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getStateForWallPlacement(pContext);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ClockBlockEntity(TDRegistry.BlockEntityReg.OWL_CLOCK, pPos, pState);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canSurviveOnWall(pState, pLevel, pPos);
    }
}
