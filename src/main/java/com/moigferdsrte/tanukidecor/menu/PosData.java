package com.moigferdsrte.tanukidecor.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.inventory.AbstractContainerMenu;

public record PosData(BlockPos pos) {
    public static final StreamCodec<RegistryFriendlyByteBuf, PosData> STREAM_CODEC = StreamCodec.composite(
        BlockPos.STREAM_CODEC,
            PosData::pos,
            PosData::new
    );
}
