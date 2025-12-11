/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.storage;

import com.moigferdsrte.tanukidecor.block.entity.StorageBlockEntity;
import com.moigferdsrte.tanukidecor.block.entity.StorageDelegateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.moigferdsrte.tanukidecor.TDRegistry;
import org.jetbrains.annotations.Nullable;

public class MinimalistDresserBlock extends WideStorageBlock {

    public static final VoxelShape SHAPE_EAST = Shapes.or(
            box(0, 2, 1, 16, 16, 15),
            box(13, 0, 2, 15, 2, 4),
            box(13, 0, 12, 15, 2, 14));
    public static final VoxelShape SHAPE_WEST = Shapes.or(
            box(0, 2, 1, 16, 16, 15),
            box(1, 0, 2, 3, 2, 4),
            box(1, 0, 12, 3, 2, 14));

    public MinimalistDresserBlock(Properties pProperties) {
        super(TDRegistry.BlockEntityReg.MINIMALIST_DRESSER, createShapeBuilder(SHAPE_EAST, SHAPE_WEST), pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (pPos.equals(getDelegatePos(pState, pPos))) {
            return new StorageBlockEntity(TDRegistry.BlockEntityReg.MINIMALIST_DRESSER, pPos, pState, 6);
        }
        return new StorageDelegateBlockEntity(pPos, pState);
    }
}
