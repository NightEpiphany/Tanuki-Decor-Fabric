/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.block.misc;

import net.minecraft.world.phys.shapes.VoxelShape;
import com.moigferdsrte.tanukidecor.block.RotatingBlock;
import com.moigferdsrte.tanukidecor.block.RotatingMultiblock;
import com.moigferdsrte.tanukidecor.util.MultiblockHandler;

public class CabanaScreenBlock extends RotatingMultiblock {

    public static final VoxelShape SHAPE = box(0, 0, 6, 16, 16, 8);

    public CabanaScreenBlock(Properties pProperties) {
        super(MultiblockHandler.MULTIBLOCK_2X2X1, RotatingBlock.createShapeBuilder(SHAPE), pProperties);
    }
}
