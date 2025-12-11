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
import net.minecraft.world.phys.shapes.VoxelShape;
import com.moigferdsrte.tanukidecor.TDRegistry;
import org.jetbrains.annotations.Nullable;

public class WoodenBlockDrawersBlock extends WideStorageBlock {

    public static final VoxelShape SHAPE_EAST = box(0, 0, 2, 16, 16, 16);
    public static final VoxelShape SHAPE_WEST = box(0, 0, 2, 16, 16, 16);

    public WoodenBlockDrawersBlock(Properties pProperties) {
        super(TDRegistry.BlockEntityReg.WOODEN_BLOCK_DRAWERS, createShapeBuilder(SHAPE_EAST, SHAPE_WEST), pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (pPos.equals(getDelegatePos(pState, pPos))) {
            return new StorageBlockEntity(TDRegistry.BlockEntityReg.WOODEN_BLOCK_DRAWERS, pPos, pState, 6);
        }
        return new StorageDelegateBlockEntity(pPos, pState);
    }
}
