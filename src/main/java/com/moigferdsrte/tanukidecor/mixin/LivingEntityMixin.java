package com.moigferdsrte.tanukidecor.mixin;

import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import com.moigferdsrte.tanukidecor.block.api.IBlockStateExtension;
import com.moigferdsrte.tanukidecor.events.CanContinueSleepingEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    public abstract Optional<BlockPos> getSleepingPos();

    @Shadow
    public abstract void clearSleepingPos();

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "startSleeping", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", shift = At.Shift.AFTER))
    private void startSleeping(BlockPos blockPos, CallbackInfo ci) {
        BlockState state = this.level().getBlockState(blockPos);
        IBlockStateExtension extension = (IBlockStateExtension) state;
        if (extension.isBed(level(), blockPos, (LivingEntity) (Object) this)) {
            extension.setBedOccupied(level(), blockPos, (LivingEntity) (Object) this, true);
        }
    }

    @Inject(method = "checkBedExists", at = @At("HEAD"), cancellable = true)
    private void checkBedExists(CallbackInfoReturnable<Boolean> cir) {
        this.getSleepingPos().ifPresent(pos -> {
            BlockState blockState = this.level().getBlockState(pos);
            if (BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getNamespace().equals(TanukiDecorFabric.MOD_ID)) {
                IBlockStateExtension extension = (IBlockStateExtension) blockState;
                boolean b = extension.isBed(this.level(), pos, (LivingEntity) (Object) this);
                cir.setReturnValue(CanContinueSleepingEvent.canEntityContinueSleeping((LivingEntity) (Object) this, b ? null : Player.BedSleepingProblem.NOT_POSSIBLE_HERE));
            }
        });
    }

    @Inject(method = "getBedOrientation", at = @At("RETURN"), cancellable = true)
    private void getBedOrientation(CallbackInfoReturnable<Direction> cir) {
        BlockPos blockPos = this.getSleepingPos().orElse(null);
        if (blockPos == null) return;
        BlockState blockState = this.level().getBlockState(blockPos);
        if (BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getNamespace().equals(TanukiDecorFabric.MOD_ID)) {
            IBlockStateExtension extension = (IBlockStateExtension) blockState;
            cir.setReturnValue(!extension.isBed(level(), blockPos, (LivingEntity) (Object) this) ? Direction.UP : extension.getBedDirection(level(), blockPos));
        }
    }

    @Inject(method = "stopSleeping", at = @At("HEAD"))
    private void stopSleeping(CallbackInfo ci) {
        AtomicBoolean flag = new AtomicBoolean(true);
            this.getSleepingPos().filter(this.level()::hasChunkAt).ifPresent(blockPos -> {
                BlockState blockState = this.level().getBlockState(blockPos);
                IBlockStateExtension extension = (IBlockStateExtension) blockState;
                if (!BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getNamespace().equals(TanukiDecorFabric.MOD_ID)) {
                    flag.set(false);
                    return;
                }
                if (extension.isBed(level(), blockPos, (LivingEntity) (Object) this)) {
                    Direction direction = blockState.getValue(BedBlock.FACING);
                    extension.setBedOccupied(level(), blockPos, (LivingEntity) (Object) this, false);
                    Vec3 vec31 = BedBlock.findStandUpPosition(this.getType(), this.level(), blockPos, direction, this.getYRot()).orElseGet(() -> {
                        BlockPos blockpos = blockPos.above();
                        return new Vec3((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.1, (double)blockpos.getZ() + 0.5);
                    });
                    Vec3 vec32 = Vec3.atBottomCenterOf(blockPos).subtract(vec31).normalize();
                    float f = (float)Mth.wrapDegrees(Mth.atan2(vec32.z, vec32.x) * 180.0F / (float)Math.PI - 90.0);
                    this.setPos(vec31.x, vec31.y, vec31.z);
                    this.setYRot(f);
                    this.setXRot(0.0F);
                }
            });
            if (!flag.get()) return;
            Vec3 vec3 = this.position();
            this.setPose(Pose.STANDING);
            this.setPos(vec3.x, vec3.y, vec3.z);
            this.clearSleepingPos();
        }
    }
