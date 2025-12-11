package com.moigferdsrte.tanukidecor;

import com.moigferdsrte.tanukidecor.block.misc.RocketLampBlock;
import com.moigferdsrte.tanukidecor.block.misc.SciencePodBlock;
import com.moigferdsrte.tanukidecor.client.TDClientEvents;
import com.moigferdsrte.tanukidecor.client.blockentity.clock.*;
import com.moigferdsrte.tanukidecor.client.blockentity.misc.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class TanukiDecorFabricClient implements ClientModInitializer {

    public static ModelResourceLocation standalone(ResourceLocation id) {
        return new ModelResourceLocation(id, "standalone");
    }
    @Override
    public void onInitializeClient() {
        TDClientEvents.register();
        // CLOCK //
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.ALARM_CLOCK, AlarmClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.ANNIVERSARY_CLOCK, AnniversaryClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.ANTIQUE_CLOCK, AntiqueClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.BANJO_CLOCK, BanjoClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.BLUE_CLOCK, BlueClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.CARRIAGE_CLOCK, CarriageClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.CRYSTAL_CLOCK, CrystalClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.CUCKOO_CLOCK, CuckooClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.DISPLAY_WATCH, DisplayWatchBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.EMBLEM_CLOCK, EmblemClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.FOLIOT_CLOCK, FoliotClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.GINGERBREAD_CLOCK, GingerbreadClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.GRANDFATHER_CLOCK, GrandfatherClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.GORGEOUS_CLOCK, GorgeousClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.GREEN_CLOCK, GreenClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.LANTERN_CLOCK, LanternClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.LARGE_CLOCK_TOWER_DIAL, LargeClockTowerDialBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.LIBRARY_CLOCK, LibraryClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.MANTLE_CLOCK, MantleClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.MINIMALIST_CLOCK, MinimalistClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.OWL_CLOCK, OwlClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.RECOGNIZABLE_CLOCK, RecognizableClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.RED_CLOCK, RedClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.REED_CLOCK, ReedClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.REGAL_CLOCK, RegalClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.ROCOCO_CLOCK, RococoClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.SMALL_CLOCK_TOWER_DIAL, SmallClockTowerDialBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.SLATE_CLOCK, SlateClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.STATION_CLOCK, StationClockBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.WOODEN_BLOCK_CLOCK, WoodenBlockClockBER::new);
        // MISC //
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.DISPLAY_CASE, DisplayCaseBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.GLOBE, GlobeBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.HANDCART, HandcartBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.HOURGLASS, HourglassBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.METRONOME, MetronomeBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.NEWTONS_CRADLE, NewtonsCradleBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.PHONOGRAPH, PhonographBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.ROCKET_LAMP, RocketLampBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.SLOT_MACHINE, SlotMachineBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.TRAIN_SET, TrainSetBER::new);
        BlockEntityRenderers.register(TDRegistry.BlockEntityReg.PLASMA_BALL, PlasmaBallBER::new);
        TDRegistry.BlockReg.ALL_BLOCKS.forEach(block -> {
            if (block instanceof RocketLampBlock || block instanceof SciencePodBlock) {
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.translucent());
                return;
            }
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        } );
    }
}
