/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.storage;

import com.moigferdsrte.tanukidecor.TDRegistry;
import com.moigferdsrte.tanukidecor.block.entity.StorageBlockEntity;
import com.moigferdsrte.tanukidecor.block.entity.StorageDelegateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CabanaBookcaseBlock extends WideStorageBlock {

    public CabanaBookcaseBlock(Properties pProperties) {
        super(TDRegistry.BlockEntityReg.CABANA_BOOKCASE, createShapeBuilder(CabanaDresserBlock.SHAPE_EAST, CabanaDresserBlock.SHAPE_WEST), pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        if (pPos.equals(getDelegatePos(pState, pPos))) {
            return new StorageBlockEntity(TDRegistry.BlockEntityReg.CABANA_BOOKCASE, pPos, pState, 3);
        }
        return new StorageDelegateBlockEntity(pPos, pState);
    }
}
