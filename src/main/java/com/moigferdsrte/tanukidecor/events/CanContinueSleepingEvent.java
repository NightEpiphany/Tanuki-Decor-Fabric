package com.moigferdsrte.tanukidecor.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CanContinueSleepingEvent implements IEvent {
    @Nullable
    protected final Player.BedSleepingProblem problem;
    protected boolean mayContinueSleeping;
    private final LivingEntity entity;

    public CanContinueSleepingEvent(LivingEntity entity, @Nullable Player.BedSleepingProblem problem) {
        this.entity = entity;
        this.problem = problem;
        this.mayContinueSleeping = (problem == null);
    }

    public LivingEntity getEntity() {
        return entity;
    }

    Optional<BlockPos> getSleepingPos() {
        return this.getEntity().getSleepingPos();
    }

    @Nullable
    public Player.BedSleepingProblem getProblem() {
        return this.problem;
    }

    public boolean mayContinueSleeping() {
        return this.mayContinueSleeping;
    }

    public void setContinueSleeping(boolean sleeping) {
        this.mayContinueSleeping = sleeping;
    }

    @Override
    public void post() {
        CALLBACK.invoker().post(this);
    }

    public static boolean canEntityContinueSleeping(LivingEntity sleeper, @Nullable Player.BedSleepingProblem problem) {
        var event = new CanContinueSleepingEvent(sleeper, problem);
        event.post();
        return event.mayContinueSleeping();
    }
}
