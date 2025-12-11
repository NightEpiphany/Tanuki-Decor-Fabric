/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.storage;

import com.moigferdsrte.tanukidecor.TDRegistry;
import com.moigferdsrte.tanukidecor.block.entity.StorageBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlueWardrobeBlock extends TallStorageBlock {

    public BlueWardrobeBlock(Properties pProperties) {
        super(BlueCabinetBlock.UPPER_SHAPE, BlueCabinetBlock.LOWER_SHAPE, TDRegistry.BlockEntityReg.BLUE_WARDROBE, pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new StorageBlockEntity(TDRegistry.BlockEntityReg.BLUE_WARDROBE, pPos, pState, 6);
    }
}
