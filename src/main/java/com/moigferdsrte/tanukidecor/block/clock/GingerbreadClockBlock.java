/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.clock;

import com.moigferdsrte.tanukidecor.block.entity.ClockBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.moigferdsrte.tanukidecor.TDRegistry;
import org.jetbrains.annotations.Nullable;

public class GingerbreadClockBlock extends TallClockBlock {

    public static final VoxelShape UPPER_SHAPE = box(4, 0, 7, 12, 3, 10);
    public static final VoxelShape LOWER_SHAPE = Shapes.or(
            box(2, 0, 6, 14, 3, 11),
            box(4, 3, 7, 12, 16, 10));

    public GingerbreadClockBlock(Properties pProperties) {
        super(TDRegistry.SoundReg.MEDIUM_CLOCK_TICK, TDRegistry.SoundReg.MEDIUM_CLOCK_CHIME2,
                UPPER_SHAPE, LOWER_SHAPE, TDRegistry.BlockEntityReg.GINGERBREAD_CLOCK,
                pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (pState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return new ClockBlockEntity(TDRegistry.BlockEntityReg.GINGERBREAD_CLOCK, pPos, pState);
        }
        return null;
    }
}
