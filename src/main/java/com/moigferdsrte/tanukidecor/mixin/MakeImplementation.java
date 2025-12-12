package com.moigferdsrte.tanukidecor.mixin;

import com.moigferdsrte.tanukidecor.block.api.IBlockStateExtension;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

@Implements({
        @Interface(iface = IBlockStateExtension.class, prefix = "self$")
})
@Mixin(value = BlockState.class, priority = 500)
public class MakeImplementation {}
