package com.moigferdsrte.tanukidecor.events;

import net.minecraft.core.BlockPos;

public record PlayerClickPosEvent(BlockPos pos) implements IEvent {

    @Override
    public void post() {
        CALLBACK.invoker().post(this);
    }
}
