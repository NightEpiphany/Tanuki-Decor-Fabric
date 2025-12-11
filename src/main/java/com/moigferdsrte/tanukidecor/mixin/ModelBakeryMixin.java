package com.moigferdsrte.tanukidecor.mixin;

import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import com.moigferdsrte.tanukidecor.client.blockentity.clock.*;
import com.moigferdsrte.tanukidecor.client.blockentity.misc.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {

    @Shadow
    protected abstract void registerModelAndLoadDependencies(ModelResourceLocation modelResourceLocation, UnbakedModel unbakedModel);

    @Shadow
    abstract UnbakedModel getModel(ResourceLocation resourceLocation);

    @Inject(method = "<init>",
            at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/resources/model/ModelBakery;loadSpecialItemModelAndDependencies(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V", shift = At.Shift.AFTER, ordinal = 1))
    private void loadSpecialItemModelAndDependencies(BlockColors blockColors, ProfilerFiller profilerFiller, Map<ResourceLocation, BlockModel> modelResources, Map<ResourceLocation, List<BlockStateModelLoader.LoadedJson>> blockStateResources, CallbackInfo ci) {
        // gather special models
        final Set<ResourceLocation> set = new HashSet<>();
        // CLOCK //
        AlarmClockBER.addSpecialModels(set);
        AnniversaryClockBER.addSpecialModels(set);
        AntiqueClockBER.addSpecialModels(set);
        BanjoClockBER.addSpecialModels(set);
        BlueClockBER.addSpecialModels(set);
        CarriageClockBER.addSpecialModels(set);
        CrystalClockBER.addSpecialModels(set);
        CuckooClockBER.addSpecialModels(set);
        DisplayWatchBER.addSpecialModels(set);
        EmblemClockBER.addSpecialModels(set);
        FoliotClockBER.addSpecialModels(set);
        GingerbreadClockBER.addSpecialModels(set);
        GorgeousClockBER.addSpecialModels(set);
        GrandfatherClockBER.addSpecialModels(set);
        GreenClockBER.addSpecialModels(set);
        LanternClockBER.addSpecialModels(set);
        LargeClockTowerDialBER.addSpecialModels(set);
        LibraryClockBER.addSpecialModels(set);
        MantleClockBER.addSpecialModels(set);
        MinimalistClockBER.addSpecialModels(set);
        OwlClockBER.addSpecialModels(set);
        RecognizableClockBER.addSpecialModels(set);
        RedClockBER.addSpecialModels(set);
        ReedClockBER.addSpecialModels(set);
        RegalClockBER.addSpecialModels(set);
        RococoClockBER.addSpecialModels(set);
        SmallClockTowerDialBER.addSpecialModels(set);
        SlateClockBER.addSpecialModels(set);
        StationClockBER.addSpecialModels(set);
        WoodenBlockClockBER.addSpecialModels(set);
        // MISC //
        GlobeBER.addSpecialModels(set);
        HourglassBER.addSpecialModels(set);
        MetronomeBER.addSpecialModels(set);
        NewtonsCradleBER.addSpecialModels(set);
        PhonographBER.addSpecialModels(set);
        RocketLampBER.addSpecialModels(set);
        SlotMachineBER.addSpecialModels(set);
        TrainSetBER.addSpecialModels(set);
        // register special models
        Set<ModelResourceLocation> rl = set.stream().map(resourceLocation -> new ModelResourceLocation(resourceLocation, "standalone")).collect(Collectors.toSet());
        rl.forEach(res -> {
            UnbakedModel unbakedmodel = this.getModel(res.id());
            this.registerModelAndLoadDependencies(res, unbakedmodel);
        });
    }
}
