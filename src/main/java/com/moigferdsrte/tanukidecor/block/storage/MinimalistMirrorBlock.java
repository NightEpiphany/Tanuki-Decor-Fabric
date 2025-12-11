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

public class MinimalistMirrorBlock extends TallStorageBlock {

    public static final VoxelShape UPPER_SHAPE = box(0, 0, 13, 16, 12, 15);
    public static final VoxelShape LOWER_SHAPE = Shapes.or(
            box(1, 0, 2, 3, 10, 4),
            box(13, 0, 2, 15, 10, 4),
            box(1, 0, 12, 3, 10, 14),
            box(13, 0, 12, 15, 10, 14),
            box(0, 10, 1, 16, 16, 15));

    public MinimalistMirrorBlock(Properties pProperties) {
        super(UPPER_SHAPE, LOWER_SHAPE, TDRegistry.BlockEntityReg.MINIMALIST_MIRROR, pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (pPos.equals(getDelegatePos(pState, pPos))) {
            return new StorageBlockEntity(TDRegistry.BlockEntityReg.MINIMALIST_MIRROR, pPos, pState, 2);
        }
        return new StorageDelegateBlockEntity(pPos, pState);
    }
}
