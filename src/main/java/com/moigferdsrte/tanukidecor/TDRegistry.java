/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor;

import com.moigferdsrte.tanukidecor.block.RotatingBlock;
import com.moigferdsrte.tanukidecor.block.TallBlock;
import com.moigferdsrte.tanukidecor.block.bed.*;
import com.moigferdsrte.tanukidecor.block.clock.*;
import com.moigferdsrte.tanukidecor.block.entity.*;
import com.moigferdsrte.tanukidecor.block.light.*;
import com.moigferdsrte.tanukidecor.block.misc.*;
import com.moigferdsrte.tanukidecor.block.seat.*;
import com.moigferdsrte.tanukidecor.block.storage.*;
import com.moigferdsrte.tanukidecor.item.MultiblockItem;
import com.moigferdsrte.tanukidecor.item.WallMultiblockItem;
import com.moigferdsrte.tanukidecor.menu.DIYWorkbenchMenu;
import com.moigferdsrte.tanukidecor.menu.PosData;
import com.moigferdsrte.tanukidecor.recipe.DIYRecipe;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class TDRegistry {

    private static final String MODID = TanukiDecorFabric.MOD_ID;


    /// / TAG KEYS ////
    public static final TagKey<Item> DIY_BLACKLIST_TAG_KEY = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "diy_blacklist"));

    public static void register() {
        // Force initialization of all registry holder classes
        // This ensures all static fields are initialized during the registration phase
        SoundReg.init();
        BlockEntityReg.init();
        BlockReg.init();
        ItemReg.init();
        CreativeTabReg.init();
        RecipeReg.init();
        MenuReg.init();
    }

    public static final class BlockReg {

        /**
         * Forces initialization of this class and all its static fields.
         * Called during mod initialization to ensure all blocks are registered.
         */
        public static void init() {
            // Method intentionally empty - the act of calling it triggers class initialization
        }

        public static final List<Block> ALL_BLOCKS = new ArrayList<>();

        // CLOCKS //

        public static final Block ALARM_CLOCK = registerWithItem("alarm_clock", () ->
                new AlarmClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block ANNIVERSARY_CLOCK = registerWithItem("anniversary_clock", () ->
                new AnniversaryClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block ANTIQUE_CLOCK = registerWithItem("antique_clock", () ->
                new AntiqueClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BANJO_CLOCK = registerWithItem("banjo_clock", () ->
                new BanjoClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_CLOCK = registerWithItem("blue_clock", () ->
                new BlueClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CARRIAGE_CLOCK = registerWithItem("carriage_clock", () ->
                new CarriageClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 8.0F)));
        public static final Block CRYSTAL_CLOCK = registerWithItem("crystal_clock", () ->
                new CrystalClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block DISPLAY_WATCH = registerWithItem("display_watch", () ->
                new DisplayWatchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block CUCKOO_CLOCK = registerWithItem("cuckoo_clock", () ->
                new CuckooClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block EMBLEM_CLOCK = registerWithMultiblockItem("emblem_clock", () ->
                new EmblemClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(3.5F, 80.0F)));
        public static final Block FOLIOT_CLOCK = registerWithItem("foliot_clock", () ->
                new FoliotClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GINGERBREAD_CLOCK = registerWithItem("gingerbread_clock", () ->
                new GingerbreadClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GORGEOUS_CLOCK = registerWithItem("gorgeous_clock", () ->
                new GorgeousClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GRANDFATHER_CLOCK = registerWithMultiblockItem("grandfather_clock", () ->
                new GrandfatherClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 60.0F)));
        public static final Block GREEN_CLOCK = registerWithItem("green_clock", () ->
                new GreenClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block LANTERN_CLOCK = registerWithItem("lantern_clock", () ->
                new LanternClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block LARGE_CLOCK_TOWER_DIAL = registerWithMultiblockItem("large_clock_tower_dial", () ->
                new LargeClockTowerDialBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(3.5F, 80.0F)));
        public static final Block LIBRARY_CLOCK = registerWithMultiblockItem("library_clock", () ->
                new LibraryClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 60.0F)));
        public static final Block MANTLE_CLOCK = registerWithItem("mantle_clock", () ->
                new MantleClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block MINIMALIST_CLOCK = registerWithItem("minimalist_clock", () ->
                new MinimalistClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block OWL_CLOCK = registerWithItem("owl_clock", () ->
                new OwlClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block RECOGNIZABLE_CLOCK = registerWithItem("recognizable_clock", () ->
                new RecognizableClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block RED_CLOCK = registerWithItem("red_clock", () ->
                new RedClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block REED_CLOCK = registerWithItem("reed_clock", () ->
                new ReedClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block REGAL_CLOCK = registerWithItem("regal_clock", () ->
                new RegalClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 12.0F)));
        public static final Block ROCOCO_CLOCK = registerWithItem("rococo_clock", () ->
                new RococoClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 6.0F)));
        public static final Block SLATE_CLOCK = registerWithItem("slate_clock", () ->
                new SlateClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 6.0F)));
        public static final Block SMALL_CLOCK_TOWER_DIAL = registerWithMultiblockItem("small_clock_tower_dial", () ->
                new SmallClockTowerDialBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(3.5F, 60.0F)));
        public static final Block STATION_CLOCK = registerWithWallMultiblockItem("station_clock", () ->
                new StationClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(3.5F, 60.0F)));
        public static final Block WOODEN_BLOCK_CLOCK = registerWithItem("wooden_block_clock", () ->
                new WoodenBlockClockBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 4.0F)));

        // STORAGE //
        public static final Block ANTIQUE_BOOKCASE = registerWithItem("antique_bookcase", () ->
                new AntiqueBookcaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block ANTIQUE_BUREAU = registerWithItem("antique_bureau", () ->
                new AntiqueBureauBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block ANTIQUE_CABINET = registerWithMultiblockItem("antique_cabinet", () ->
                new AntiqueCabinetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block ANTIQUE_DESK = registerWithItem("antique_desk", () ->
                new AntiqueDeskBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block ANTIQUE_MIRROR = registerWithMultiblockItem("antique_mirror", () ->
                new AntiqueMirrorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block ANTIQUE_WALL_SHELF = registerWithItem("antique_wall_shelf", () ->
                new AntiqueWallShelfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block ANTIQUE_WARDROBE = registerWithMultiblockItem("antique_wardrobe", () ->
                new AntiqueWardrobeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block BLUE_BOOKSHELF = registerWithItem("blue_bookshelf", () ->
                new BlueBookshelfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_BUREAU = registerWithItem("blue_bureau", () ->
                new BlueBureauBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_CABINET = registerWithItem("blue_cabinet", () ->
                new BlueCabinetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_DRESSER = registerWithItem("blue_dresser", () ->
                new BlueDresserBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_WARDROBE = registerWithItem("blue_wardrobe", () ->
                new BlueWardrobeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CABANA_BOOKCASE = registerWithItem("cabana_bookcase", () ->
                new CabanaBookcaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CABANA_DRESSER = registerWithItem("cabana_dresser", () ->
                new CabanaDresserBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CABANA_VANITY = registerWithItem("cabana_vanity", () ->
                new CabanaVanityBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CABANA_WARDROBE = registerWithMultiblockItem("cabana_wardrobe", () ->
                new CabanaWardrobeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block GORGEOUS_CHEST = registerWithItem("gorgeous_chest", () ->
                new GorgeousChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GORGEOUS_CLOSET = registerWithMultiblockItem("gorgeous_closet", () ->
                new GorgeousClosetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block GORGEOUS_DESK = registerWithItem("gorgeous_desk", () ->
                new GorgeousDeskBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GORGEOUS_MINI_DRAWER = registerWithItem("gorgeous_mini_drawer", () ->
                new GorgeousMiniDrawerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GREEN_DESK = registerWithItem("green_desk", () ->
                new GreenDeskBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GREEN_DRESSER = registerWithItem("green_dresser", () ->
                new GreenDresserBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GREEN_MINI_DRAWER = registerWithItem("green_mini_drawer", () ->
                new GreenMiniDrawerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GREEN_PANTRY = registerWithItem("green_pantry", () ->
                new GreenPantryBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GREEN_WARDROBE = registerWithItem("green_wardrobe", () ->
                new GreenWardrobeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block MINIMALIST_DRESSER = registerWithItem("minimalist_dresser", () ->
                new MinimalistDresserBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block MINIMALIST_MIRROR = registerWithItem("minimalist_mirror", () ->
                new MinimalistMirrorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block MINIMALIST_WARDROBE = registerWithItem("minimalist_wardrobe", () ->
                new MinimalistWardrobeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block REGAL_ARMOIRE = registerWithItem("regal_armoire", () ->
                new RegalArmoireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block REGAL_BOOKSHELF = registerWithMultiblockItem("regal_bookshelf", () ->
                new RegalBookshelfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block REGAL_DRESSER = registerWithItem("regal_dresser", () ->
                new RegalDresserBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block REGAL_VANITY = registerWithItem("regal_vanity", () ->
                new RegalVanityBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block SWEETS_CLOSET = registerWithMultiblockItem("sweets_closet", () ->
                new SweetsClosetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block SWEETS_DRESSER = registerWithMultiblockItem("sweets_dresser", () ->
                new SweetsDresserBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block WOODEN_BLOCK_DRAWERS = registerWithItem("wooden_block_drawers", () ->
                new WoodenBlockDrawersBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));

        // SEAT //
        public static final Block ANTIQUE_CHAIR = registerWithItem("antique_chair", () ->
                new AntiqueChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block ANTIQUE_SOFA = registerWithItem("antique_sofa", () ->
                new AntiqueSofaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_BENCH = registerWithItem("blue_bench", () ->
                new BlueBenchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_CHAIR = registerWithItem("blue_chair", () ->
                new BlueChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CABANA_ARMCHAIR = registerWithItem("cabana_armchair", () ->
                new CabanaArmchairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block CABANA_CHAIR = registerWithItem("cabana_chair", () ->
                new CabanaChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block EGYPTIAN_CHAIR = registerWithItem("egyptian_chair", () ->
                new EgyptianChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GORGEOUS_SEAT = registerWithItem("gorgeous_seat", () ->
                new GorgeousSeatBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GORGEOUS_SOFA = registerWithItem("gorgeous_sofa", () ->
                new GorgeousSofaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GORGEOUS_STOOL = registerWithItem("gorgeous_stool", () ->
                new GorgeousStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GREEN_BENCH = registerWithItem("green_bench", () ->
                new GreenBenchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GREEN_CHAIR = registerWithItem("green_chair", () ->
                new GreenChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block LOG_BENCH = registerWithItem("log_bench", () ->
                new LogBenchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block LOG_STOOL = registerWithItem("log_stool", () ->
                new LogStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block MINIMALIST_CHAIR = registerWithItem("minimalist_chair", () ->
                new MinimalistChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block MINIMALIST_COUCH = registerWithItem("minimalist_couch", () ->
                new MinimalistCouchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block MINIMALIST_STOOL = registerWithItem("minimalist_stool", () ->
                new MinimalistStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block BROWN_MUSHROOM_LOG_STOOL = registerWithItem("brown_mushroom_log_stool", () ->
                new LogStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block RED_MUSHROOM_LOG_STOOL = registerWithItem("red_mushroom_log_stool", () ->
                new LogStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block BROWN_MUSHROOM_STOOL = registerWithItem("brown_mushroom_stool", () ->
                new MushroomStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.FUNGUS).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block RED_MUSHROOM_STOOL = registerWithItem("red_mushroom_stool", () ->
                new MushroomStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.FUNGUS).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block REGAL_CHAIR = registerWithItem("regal_chair", () ->
                new RegalChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block REGAL_SOFA = registerWithItem("regal_sofa", () ->
                new RegalSofaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block ROUGH_LOG_BENCH = registerWithItem("rough_log_bench", () ->
                new RoughLogBenchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block SWEETS_CHAIR = registerWithItem("sweets_chair", () ->
                new SweetsChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block SWEETS_SOFA = registerWithItem("sweets_sofa", () ->
                new SweetsSofaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block WOODEN_BLOCK_BENCH = registerWithItem("wooden_block_bench", () ->
                new WoodenBlockBenchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(2.0F, 10.0F)));
        public static final Block WOODEN_BLOCK_CHAIR = registerWithItem("wooden_block_chair", () ->
                new WoodenBlockChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block WOODEN_BLOCK_STOOL = registerWithItem("wooden_block_stool", () ->
                new WoodenBlockStoolBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().noOcclusion().strength(1.5F, 6.0F)));

        // BED //
        public static final Block ANTIQUE_BED = registerWithMultiblockItem("antique_bed", () ->
                new AntiqueBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.0F, 30.0F)));
        public static final Block BLUE_BED = registerWithMultiblockItem("blue_bed", () ->
                new BlueBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CABANA_BED = registerWithMultiblockItem("cabana_bed", () ->
                new CabanaBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.0F, 30.0F)));
        public static final Block EGYPTIAN_BED = registerWithMultiblockItem("egyptian_bed", () ->
                new EgyptianBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GREEN_BED = registerWithMultiblockItem("green_bed", () ->
                new GreenBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GORGEOUS_BED = registerWithMultiblockItem("gorgeous_bed", () ->
                new GorgeousBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.0F, 30.0F)));
        public static final Block MINIMALIST_BED = registerWithMultiblockItem("minimalist_bed", () ->
                new MinimalistBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BROWN_MUSHROOM_BED = registerWithMultiblockItem("brown_mushroom_bed", () ->
                new MushroomBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.FUNGUS).sound(SoundType.FUNGUS).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block RED_MUSHROOM_BED = registerWithMultiblockItem("red_mushroom_bed", () ->
                new MushroomBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.FUNGUS).sound(SoundType.FUNGUS).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block REGAL_BED = registerWithMultiblockItem("regal_bed", () ->
                new RegalBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.0F, 30.0F)));
        public static final Block SWEETS_BED = registerWithMultiblockItem("sweets_bed", () ->
                new SweetsBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.0F, 30.0F)));
        public static final Block WOODEN_BLOCK_BED = registerWithMultiblockItem("wooden_block_bed", () ->
                new WoodenBlockBedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));

        // LIGHT //
        public static final Block ANTIQUE_WALL_OIL_LAMP = registerWithItem("antique_wall_oil_lamp", () ->
                new AntiqueWallOilLamp(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).lightLevel(b -> b.getValue(RotatingBlock.WATERLOGGED) ? 0 : 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block BLUE_LAMP = registerWithItem("blue_lamp", () ->
                new BlueLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block CABANA_LAMP = registerWithItem("cabana_lamp", () ->
                new CabanaLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> b.getValue(TallBlock.HALF) == DoubleBlockHalf.UPPER ? 14 : 0).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block EGYPTIAN_LAMP = registerWithItem("egyptian_lamp", () ->
                new EgyptianLampBlock(2, BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).lightLevel(b -> !b.getValue(TallBlock.WATERLOGGED) ? 14 : 0).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GLOWING_MOSS_JAR = registerWithItem("glowing_moss_jar", () ->
                new GlowingMossJarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GLOW_LICHEN).sound(SoundType.GLASS).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GORGEOUS_LAMP = registerWithItem("gorgeous_lamp", () ->
                new GorgeousLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GREEN_LAMP = registerWithItem("green_lamp", () ->
                new GreenLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block LARGE_FIREPLACE = registerWithMultiblockItem("large_fireplace", () ->
                new LargeFireplaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.STONE).lightLevel(b -> b.getValue(RotatingBlock.WATERLOGGED) ? 0 : 14).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block MINIMALIST_LAMP = registerWithItem("minimalist_lamp", () ->
                new MinimalistLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> b.getValue(TallBlock.HALF) == DoubleBlockHalf.UPPER ? 14 : 0).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BROWN_MUSHROOM_LAMP = registerWithItem("brown_mushroom_lamp", () ->
                new MushroomLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.FUNGUS).lightLevel(b -> b.getValue(TallBlock.HALF) == DoubleBlockHalf.UPPER ? 14 : 0).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block RED_MUSHROOM_LAMP = registerWithItem("red_mushroom_lamp", () ->
                new MushroomLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.FUNGUS).lightLevel(b -> b.getValue(TallBlock.HALF) == DoubleBlockHalf.UPPER ? 14 : 0).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block NEON_CLUB_SIGN = registerWithItem("neon_club_sign", () ->
                new NeonSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block NEON_DIAMOND_SIGN = registerWithItem("neon_diamond_sign", () ->
                new NeonSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block NEON_HEART_SIGN = registerWithItem("neon_heart_sign", () ->
                new NeonSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block NEON_SPADE_SIGN = registerWithItem("neon_spade_sign", () ->
                new NeonSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block SWEETS_MINI_LAMP = registerWithItem("sweets_mini_lamp", () ->
                new SweetsMiniLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block SWEETS_WALL_LAMP = registerWithItem("sweets_wall_lamp", () ->
                new SweetsWallLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block REGAL_LAMP = registerWithItem("regal_lamp", () ->
                new RegalLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block REGAL_WALL_LAMP = registerWithItem("regal_wall_lamp", () ->
                new RegalWallLampBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block BLUE_ROCKET_LAMP = registerWithItem("blue_rocket_lamp", () ->
                new RocketLampBlock("blue", BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block GREEN_ROCKET_LAMP = registerWithItem("green_rocket_lamp", () ->
                new RocketLampBlock("green", BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block PINK_ROCKET_LAMP = registerWithItem("pink_rocket_lamp", () ->
                new RocketLampBlock("pink", BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block PURPLE_ROCKET_LAMP = registerWithItem("purple_rocket_lamp", () ->
                new RocketLampBlock("purple", BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block RED_ROCKET_LAMP = registerWithItem("red_rocket_lamp", () ->
                new RocketLampBlock("red", BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block TURQUOISE_ROCKET_LAMP = registerWithItem("turquoise_rocket_lamp", () ->
                new RocketLampBlock("turquoise", BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block YELLOW_ROCKET_LAMP = registerWithItem("yellow_rocket_lamp", () ->
                new RocketLampBlock("yellow", BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block SMALL_FIREPLACE = registerWithItem("small_fireplace", () ->
                new SmallFireplaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.STONE).lightLevel(b -> b.getValue(RotatingBlock.WATERLOGGED) ? 0 : 14).noOcclusion().strength(2.0F, 10.0F)));

        // MISC //
        public static final Block ANTIQUE_PHONE = registerWithItem("antique_phone", () ->
                new AntiquePhoneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block ANTIQUE_RADIO = registerWithItem("antique_radio", () ->
                new AntiqueRadioBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block ANTIQUE_SMALL_TABLE = registerWithItem("antique_small_table", () ->
                new AntiqueSmallTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block ANTIQUE_SMALL_TABLE_WITH_DOILY = registerWithItem("antique_small_table_with_doily", () ->
                new AntiqueSmallTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block ANTIQUE_TABLE = registerWithMultiblockItem("antique_table", () ->
                new AntiqueTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block BIRDCAGE = registerWithItem("birdcage", () ->
                new BirdcageBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).randomTicks().isSuffocating(BlockReg::never).isViewBlocking(BlockReg::never).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block ELEGANT_BIRDCAGE = registerWithItem("elegant_birdcage", () ->
                new BirdcageBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).randomTicks().isSuffocating(BlockReg::never).isViewBlocking(BlockReg::never).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_TABLE = registerWithMultiblockItem("blue_table", () ->
                new BlueTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block CABANA_SCREEN = registerWithMultiblockItem("cabana_screen", () ->
                new CabanaScreenBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block CABANA_TABLE = registerWithItem("cabana_table", () ->
                new CabanaTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CASH_REGISTER = registerWithItem("cash_register", () ->
                new CashRegisterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block DESSERT_CASE = registerWithMultiblockItem("dessert_case", () ->
                new DessertCaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block DISPLAY_CASE = registerWithItem("display_case", () ->
                new DisplayCaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.GLASS).noOcclusion().strength(1.5F, 30.0F)));
        public static final Block LONG_DISPLAY_CASE = registerWithMultiblockItem("long_display_case", () ->
                new LongDisplayCaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.GLASS).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block DIY_WORKBENCH = registerWithItem("diy_workbench", () ->
                new DIYWorkbenchBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block EGYPTIAN_CREST = registerWithItem("egyptian_crest", () ->
                new EgyptianCrestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block EGYPTIAN_TABLE = registerWithItem("egyptian_table", () ->
                new EgyptianTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block FIREWOOD = registerWithItem("firewood", () ->
                new FirewoodBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 1.5F).ignitedByLava()));
        public static final Block GREEN_COUNTER = registerWithItem("green_counter", () ->
                new GreenCounterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GREEN_TABLE = registerWithMultiblockItem("green_table", () ->
                new GreenTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block GORGEOUS_COUNTER = registerWithItem("gorgeous_counter", () ->
                new GorgeousCounterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GORGEOUS_TABLE = registerWithItem("gorgeous_table", () ->
                new GorgeousTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GUMBALL_MACHINE = registerWithItem("gumball_machine", () ->
                new GumballMachineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GLOBE = registerWithItem("globe", () ->
                new GlobeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block HANDCART = registerWithItem("handcart", () ->
                new HandcartBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block HOLIDAY_TREE = registerWithItem("holiday_tree", () ->
                new HolidayTreeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block HOURGLASS = registerWithItem("hourglass", () ->
                new HourglassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block METRONOME = registerWithItem("metronome", () ->
                new MetronomeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block MINIMALIST_SMALL_TABLE = registerWithItem("minimalist_small_table", () ->
                new MinimalistSmallTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block MINIMALIST_TABLE = registerWithItem("minimalist_table", () ->
                new MinimalistTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block DEVELOPER_MINI_FIGURE = registerWithItem("developer_mini_figure", () ->
                new MiniFigureBlock(SoundEvents.CAT_AMBIENT, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WOOL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block MAYORAL_MINI_FIGURE = registerWithItem("mayoral_mini_figure", () ->
                new MiniFigureBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block BROWN_MUSHROOM_TABLE = registerWithMultiblockItem("brown_mushroom_table", () ->
                new MushroomTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.FUNGUS).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block RED_MUSHROOM_TABLE = registerWithMultiblockItem("red_mushroom_table", () ->
                new MushroomTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.FUNGUS).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block NEWTONS_CRADLE = registerWithItem("newtons_cradle", () ->
                new NewtonsCradleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block NARROW_BLUE_STREAMER = registerWithItem("narrow_blue_streamer", () ->
                new NarrowStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 6.0F)));
        public static final Block NARROW_GREEN_STREAMER = registerWithItem("narrow_green_streamer", () ->
                new NarrowStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 6.0F)));
        public static final Block NARROW_RED_STREAMER = registerWithItem("narrow_red_streamer", () ->
                new NarrowStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 6.0F)));
        public static final Block NARROW_YELLOW_STREAMER = registerWithItem("narrow_yellow_streamer", () ->
                new NarrowStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 6.0F)));
        public static final Block NARROW_STRING_LIGHTS = registerWithItem("narrow_string_lights", () ->
                new NarrowStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.GLASS).lightLevel(b -> 14).noCollission().noOcclusion().strength(0.5F, 6.0F)));
        public static final Block PHONOGRAPH = registerWithItem("phonograph", () ->
                new PhonographBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block PIANO = registerWithMultiblockItem("piano", () ->
                new PianoBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block PLASMA_BALL = registerWithItem("plasma_ball", () ->
                new PlasmaBallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> b.getValue(TallBlock.HALF) == DoubleBlockHalf.UPPER && b.getValue(PlasmaBallBlock.ENABLED) ? 14 : 0).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block REGAL_SMALL_TABLE = registerWithItem("regal_small_table", () ->
                new RegalSmallTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block REGAL_TABLE = registerWithItem("regal_table", () ->
                new RegalTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block BLUE_SCIENCE_POD = registerWithMultiblockItem("blue_science_pod", () ->
                new SciencePodBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block GREEN_SCIENCE_POD = registerWithMultiblockItem("green_science_pod", () ->
                new SciencePodBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block ORANGE_SCIENCE_POD = registerWithMultiblockItem("orange_science_pod", () ->
                new SciencePodBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block RED_SCIENCE_POD = registerWithMultiblockItem("red_science_pod", () ->
                new SciencePodBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.GLASS).lightLevel(b -> 14).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block SHIP_IN_A_BOTTLE = registerWithItem("ship_in_a_bottle", () ->
                new ShipInABottleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.GLASS).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block SLOT_MACHINE = registerWithItem("slot_machine", () ->
                new SlotMachineBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block SNOWGLOBE = registerWithItem("snowglobe", () ->
                new SnowglobeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.GLASS).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block BLUE_STALL_TARP = registerWithWallMultiblockItem("blue_stall_tarp", () ->
                new StallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GREEN_STALL_TARP = registerWithWallMultiblockItem("green_stall_tarp", () ->
                new StallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block RED_STALL_TARP = registerWithWallMultiblockItem("red_stall_tarp", () ->
                new StallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block YELLOW_STALL_TARP = registerWithWallMultiblockItem("yellow_stall_tarp", () ->
                new StallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block SWEETS_BOOKCASE = registerWithItem("sweets_bookcase", () ->
                new SweetsBookcaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block SWEETS_MINI_TABLE = registerWithItem("sweets_mini_table", () ->
                new SweetsMiniTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.GLASS).noOcclusion().strength(1.5F, 6.0F)));
        public static final Block SWEETS_TABLE = registerWithItem("sweets_table", () ->
                new SweetsTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block TRAIN_SET = registerWithItem("train_set", () ->
                new TrainSetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().noCollission().strength(2.0F, 30.0F)));
        public static final Block WOODEN_BLOCK_BOOKSHELF = registerWithItem("wooden_block_bookshelf", () ->
                new WoodenBlockBookshelfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block WOODEN_BLOCK_TABLE = registerWithMultiblockItem("wooden_block_table", () ->
                new WoodenBlockTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).noOcclusion().strength(3.5F, 30.0F)));
        public static final Block WOODEN_BLOCK_TOYS = registerWithItem("wooden_block_toys", () ->
                new WoodenBlockToysBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).noCollission().noOcclusion().strength(1.5F, 6.0F)));
        public static final Block BLUE_WALL_TARP = registerWithWallMultiblockItem("blue_wall_tarp", () ->
                new WallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block GREEN_WALL_TARP = registerWithWallMultiblockItem("green_wall_tarp", () ->
                new WallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block RED_WALL_TARP = registerWithWallMultiblockItem("red_wall_tarp", () ->
                new WallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block YELLOW_WALL_TARP = registerWithWallMultiblockItem("yellow_wall_tarp", () ->
                new WallTarpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.WOOL).noOcclusion().strength(2.0F, 10.0F)));
        public static final Block CLASSIC_VASE = registerWithItem("classic_vase", () ->
                new VaseBlock(VaseBlock.CLASSIC_SHAPE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.DECORATED_POT).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block FANCY_VASE = registerWithItem("fancy_vase", () ->
                new VaseBlock(VaseBlock.FANCY_SHAPE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.DECORATED_POT).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block LARGE_FANCY_VASE = registerWithItem("large_fancy_vase", () ->
                new LargeVaseBlock(LargeVaseBlock.LARGE_FANCY_SHAPE_UPPER, LargeVaseBlock.LARGE_FANCY_SHAPE_LOWER, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.DECORATED_POT).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block LARGE_STRIPED_VASE = registerWithItem("large_striped_vase", () ->
                new LargeVaseBlock(LargeVaseBlock.LARGE_STRIPED_SHAPE_UPPER, LargeVaseBlock.LARGE_STRIPED_SHAPE_LOWER, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.DECORATED_POT).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block SMALL_FANCY_VASE = registerWithItem("small_fancy_vase", () ->
                new VaseBlock(VaseBlock.SMALL_FANCY_SHAPE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.DECORATED_POT).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block SMALL_STRIPED_VASE = registerWithItem("small_striped_vase", () ->
                new VaseBlock(VaseBlock.SMALL_STRIPED_SHAPE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.DECORATED_POT).noOcclusion().strength(2.0F, 30.0F)));
        public static final Block WIDE_BLUE_STREAMER = registerWithItem("wide_blue_streamer", () ->
                new WideStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 10.0F)));
        public static final Block WIDE_GREEN_STREAMER = registerWithItem("wide_green_streamer", () ->
                new WideStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 10.0F)));
        public static final Block WIDE_RED_STREAMER = registerWithItem("wide_red_streamer", () ->
                new WideStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 10.0F)));
        public static final Block WIDE_YELLOW_STREAMER = registerWithItem("wide_yellow_streamer", () ->
                new WideStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.WOOL).noCollission().noOcclusion().strength(0.5F, 10.0F)));
        public static final Block WIDE_STRING_LIGHTS = registerWithItem("wide_string_lights", () ->
                new WideStreamerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).sound(SoundType.GLASS).lightLevel(b -> 14).noCollission().noOcclusion().strength(0.5F, 10.0F)));

        // HELPER METHODS //

        private static Block registerWithItem(final String name, final Supplier<Block> supplier) {
            Block block = supplier.get();

            Block register = Registry.register(BuiltInRegistries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MODID, name), block);
            ALL_BLOCKS.add(register);

            BlockItem blockItem = Registry.register(BuiltInRegistries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(MODID, name),
                    new BlockItem(block, new Item.Properties()));
            ItemReg.ALL_ITEMS.add(blockItem);
            return block;
        }

        public static Block register(String string, Block block) {
            return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, string), block);
        }

        private static Block registerWithMultiblockItem(final String name, final Supplier<Block> supplier) {
            Block block = supplier.get();
            Block register = Registry.register(BuiltInRegistries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MODID, name), block);
            ALL_BLOCKS.add(register);
            MultiblockItem item = Registry.register(BuiltInRegistries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(MODID, name),
                    new MultiblockItem(block, new Item.Properties().stacksTo(1)));
            ItemReg.ALL_ITEMS.add(item);
            return block;
        }

        private static Block registerWithWallMultiblockItem(final String name, final Supplier<Block> supplier) {
            Block block = supplier.get();
            Block register = Registry.register(BuiltInRegistries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(MODID, name), block);
            ALL_BLOCKS.add(register);
            WallMultiblockItem item = Registry.register(BuiltInRegistries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(MODID, name),
                    new WallMultiblockItem(block, new Item.Properties().stacksTo(1)));
            ItemReg.ALL_ITEMS.add(item);
            return block;
        }

        private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
            return false;
        }
    }

    public static final class ItemReg {

        /**
         * Forces initialization of this class and all its static fields.
         * Called during mod initialization to ensure all items are registered.
         */
        public static void init() {
            // Method intentionally empty - the act of calling it triggers class initialization
        }

        private static final List<Item> ALL_ITEMS = new ArrayList<>();

        /**
         * Creates a registry object for a block item and adds it to the mod creative tab
         *
         * @param block the block
         * @return the registry object
         */
        private static Item registerBlockItem(final Block block) {
            return register(BuiltInRegistries.BLOCK.getKey(block).getPath(), () -> new BlockItem(block, new Item.Properties()));
        }

        /**
         * Creates a registry object for the given item and adds it to the mod creative tab
         *
         * @param name     the registry name
         * @param supplier the item supplier
         * @return the item registry object
         */
        private static Item register(final String name, final Supplier<Item> supplier) {
            final Item item = Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, name), supplier.get());
            ALL_ITEMS.add(item);
            return item;
        }
    }

    public static final class CreativeTabReg {

        /**
         * Forces initialization of this class and all its static fields.
         * Called during mod initialization to ensure the creative tab is registered.
         */


           static final ResourceKey<CreativeModeTab> MAIN_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                    ResourceLocation.fromNamespaceAndPath(MODID, "tab"));
           static final ResourceLocation ICON_ID = ResourceLocation.fromNamespaceAndPath(MODID, "blue_bench");

        public static void init() {
            // Method intentionally empty - the act of calling it triggers class initialization
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN_TAB, FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.tanukidecor.tab"))
                    .icon(() -> BuiltInRegistries.ITEM.get(ICON_ID).getDefaultInstance())
                    .displayItems((par, output) -> output.acceptAll(ItemReg.ALL_ITEMS
                            .stream()
                            .map(ItemStack::new)
                            .toList())).build());
        }
    }

    public static final class BlockEntityReg {

        /**
         * Forces initialization of this class and all its static fields.
         * Called during mod initialization to ensure all block entity types are registered.
         */
        public static void init() {
            // Method intentionally empty - the act of calling it triggers class initialization
        }

        // CLOCKS //

        public static final BlockEntityType<ClockBlockEntity> ALARM_CLOCK = registerClock(
                BlockEntityReg.ALARM_CLOCK, BlockReg.ALARM_CLOCK, "alarm_clock");
        public static final BlockEntityType<ClockBlockEntity> ANNIVERSARY_CLOCK = registerClock(
                BlockEntityReg.ANNIVERSARY_CLOCK, BlockReg.ANNIVERSARY_CLOCK, "alarm_clock");
        public static final BlockEntityType<ClockBlockEntity> ANTIQUE_CLOCK = registerClock(
                BlockEntityReg.ANTIQUE_CLOCK, BlockReg.ANTIQUE_CLOCK, "antique_clock");
        public static final BlockEntityType<ClockBlockEntity> BANJO_CLOCK = registerClock(
                BlockEntityReg.BANJO_CLOCK, BlockReg.BANJO_CLOCK, "banjo_clock");
        public static final BlockEntityType<ClockBlockEntity> BLUE_CLOCK = registerClock(
                BlockEntityReg.BLUE_CLOCK, BlockReg.BLUE_CLOCK, "blue_clock");
        public static final BlockEntityType<ClockBlockEntity> CARRIAGE_CLOCK = registerClock(
                BlockEntityReg.CARRIAGE_CLOCK, BlockReg.CARRIAGE_CLOCK, "carriage_clock");
        public static final BlockEntityType<ClockBlockEntity> CRYSTAL_CLOCK = registerClock(
                BlockEntityReg.CRYSTAL_CLOCK, BlockReg.CRYSTAL_CLOCK, "crystal_clock");
        public static final BlockEntityType<ClockBlockEntity> CUCKOO_CLOCK = registerClock(
                BlockEntityReg.CUCKOO_CLOCK, BlockReg.CUCKOO_CLOCK, "cuckoo_clock");
        public static final BlockEntityType<ClockBlockEntity> DISPLAY_WATCH = registerClock(
                BlockEntityReg.DISPLAY_WATCH, BlockReg.DISPLAY_WATCH, "display_watch");
        public static final BlockEntityType<ClockBlockEntity> EMBLEM_CLOCK = registerClock(
                BlockEntityReg.EMBLEM_CLOCK, BlockReg.EMBLEM_CLOCK, "emblem_clock");
        public static final BlockEntityType<ClockBlockEntity> FOLIOT_CLOCK = registerClock(
                BlockEntityReg.FOLIOT_CLOCK, BlockReg.FOLIOT_CLOCK, "foliot_clock");
        public static final BlockEntityType<ClockBlockEntity> GINGERBREAD_CLOCK = registerClock(
                BlockEntityReg.GINGERBREAD_CLOCK, BlockReg.GINGERBREAD_CLOCK, "gingerbread_clock");
        public static final BlockEntityType<ClockBlockEntity> GORGEOUS_CLOCK = registerClock(
                BlockEntityReg.GORGEOUS_CLOCK, BlockReg.GORGEOUS_CLOCK, "gorgeous_clock");
        public static final  BlockEntityType<ClockBlockEntity> GRANDFATHER_CLOCK = registerClock(
                BlockEntityReg.GRANDFATHER_CLOCK, BlockReg.GRANDFATHER_CLOCK, "grandfather_clock");
        public static final  BlockEntityType<ClockBlockEntity> GREEN_CLOCK = registerClock(
                BlockEntityReg.GREEN_CLOCK, BlockReg.GREEN_CLOCK, "green_clock");
        public static final  BlockEntityType<ClockBlockEntity> LANTERN_CLOCK = registerClock(
                BlockEntityReg.LANTERN_CLOCK, BlockReg.LANTERN_CLOCK, "lantern_clock");
        public static final  BlockEntityType<ClockBlockEntity> LARGE_CLOCK_TOWER_DIAL = registerClock(
                BlockEntityReg.LARGE_CLOCK_TOWER_DIAL, BlockReg.LARGE_CLOCK_TOWER_DIAL, "large_clock_tower_dial");
        public static final  BlockEntityType<ClockBlockEntity> LIBRARY_CLOCK = registerClock(
                BlockEntityReg.LIBRARY_CLOCK, BlockReg.LIBRARY_CLOCK, "library_clock");
        public static final  BlockEntityType<ClockBlockEntity> MANTLE_CLOCK = registerClock(
                BlockEntityReg.MANTLE_CLOCK, BlockReg.MANTLE_CLOCK, "mantle_clock");
        public static final  BlockEntityType<ClockBlockEntity> MINIMALIST_CLOCK = registerClock(
                BlockEntityReg.MINIMALIST_CLOCK, BlockReg.MINIMALIST_CLOCK, "minimalist_clock");
        public static final  BlockEntityType<ClockBlockEntity> OWL_CLOCK = registerClock(
                BlockEntityReg.OWL_CLOCK, BlockReg.OWL_CLOCK, "owl_clock");
        public static final  BlockEntityType<ClockBlockEntity> RECOGNIZABLE_CLOCK = registerClock(
                BlockEntityReg.RECOGNIZABLE_CLOCK, BlockReg.RECOGNIZABLE_CLOCK, "recognizable_clock");
        public static final  BlockEntityType<ClockBlockEntity> RED_CLOCK = registerClock(
                BlockEntityReg.RED_CLOCK, BlockReg.RED_CLOCK, "red_clock");
        public static final  BlockEntityType<ClockBlockEntity> REED_CLOCK = registerClock(
                BlockEntityReg.REED_CLOCK, BlockReg.REED_CLOCK, "reed_clock");
        public static final  BlockEntityType<ClockBlockEntity> REGAL_CLOCK = registerClock(
                BlockEntityReg.REGAL_CLOCK, BlockReg.REGAL_CLOCK, "regal_clock");
        public static final  BlockEntityType<ClockBlockEntity> ROCOCO_CLOCK = registerClock(
                BlockEntityReg.ROCOCO_CLOCK, BlockReg.ROCOCO_CLOCK, "rocco_clock");
        public static final  BlockEntityType<ClockBlockEntity> SLATE_CLOCK = registerClock(
                BlockEntityReg.SLATE_CLOCK, BlockReg.SLATE_CLOCK, "slate_clock");
        public static final  BlockEntityType<ClockBlockEntity> SMALL_CLOCK_TOWER_DIAL = registerClock(
                BlockEntityReg.SMALL_CLOCK_TOWER_DIAL, BlockReg.SMALL_CLOCK_TOWER_DIAL, "small_clock_tower_dial");
        public static final  BlockEntityType<ClockBlockEntity> STATION_CLOCK = registerClock(
                BlockEntityReg.STATION_CLOCK, BlockReg.STATION_CLOCK, "station_clock");
        public static final  BlockEntityType<ClockBlockEntity> WOODEN_BLOCK_CLOCK = registerClock(
                BlockEntityReg.WOODEN_BLOCK_CLOCK, BlockReg.WOODEN_BLOCK_CLOCK, "wooden_block_clock");

        // STORAGE //
        public static final  BlockEntityType<StorageDelegateBlockEntity> STORAGE_DELEGATE = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "storage_delegate"), BlockEntityType.Builder
                .of((pos, state) -> new StorageDelegateBlockEntity(BlockEntityReg.STORAGE_DELEGATE, pos, state),
                        BlockReg.ANTIQUE_BOOKCASE, BlockReg.ANTIQUE_BUREAU, BlockReg.ANTIQUE_CABINET, BlockReg.ANTIQUE_DESK, BlockReg.ANTIQUE_MIRROR, BlockReg.ANTIQUE_WARDROBE,
                        BlockReg.BLUE_BOOKSHELF, BlockReg.BLUE_BUREAU, BlockReg.BLUE_CABINET, BlockReg.BLUE_WARDROBE,
                        BlockReg.CABANA_BOOKCASE, BlockReg.CABANA_DRESSER, BlockReg.CABANA_VANITY, BlockReg.CABANA_WARDROBE,
                        BlockReg.GORGEOUS_CHEST, BlockReg.GORGEOUS_CLOSET,
                        BlockReg.GREEN_DRESSER, BlockReg.GREEN_WARDROBE,
                        BlockReg.MINIMALIST_DRESSER, BlockReg.MINIMALIST_MIRROR, BlockReg.MINIMALIST_WARDROBE,
                        BlockReg.REGAL_ARMOIRE, BlockReg.REGAL_BOOKSHELF, BlockReg.REGAL_DRESSER, BlockReg.REGAL_VANITY,
                        BlockReg.SWEETS_CLOSET, BlockReg.SWEETS_DRESSER,
                        BlockReg.WOODEN_BLOCK_DRAWERS,
                        BlockReg.DIY_WORKBENCH, BlockReg.PHONOGRAPH, BlockReg.LARGE_FANCY_VASE, BlockReg.LARGE_STRIPED_VASE,
                        BlockReg.DISPLAY_CASE, BlockReg.LONG_DISPLAY_CASE, BlockReg.HANDCART,
                        BlockReg.BLUE_SCIENCE_POD, BlockReg.GREEN_SCIENCE_POD, BlockReg.ORANGE_SCIENCE_POD, BlockReg.RED_SCIENCE_POD)
                .build(null));

        public static final  BlockEntityType<StorageBlockEntity> ANTIQUE_BOOKCASE = registerStorage(
                BlockEntityReg.ANTIQUE_BOOKCASE, 3, BlockReg.ANTIQUE_BOOKCASE);
        public static final  BlockEntityType<StorageBlockEntity> ANTIQUE_BUREAU = registerStorage(
                BlockEntityReg.ANTIQUE_BUREAU, 3, BlockReg.ANTIQUE_BUREAU);
        public static final  BlockEntityType<StorageBlockEntity> ANTIQUE_CABINET = registerStorage(
                BlockEntityReg.ANTIQUE_CABINET, 6, BlockReg.ANTIQUE_CABINET);
        public static final  BlockEntityType<StorageBlockEntity> ANTIQUE_DESK = registerStorage(
                BlockEntityReg.ANTIQUE_DESK, 3, BlockReg.ANTIQUE_DESK);
        public static final  BlockEntityType<StorageBlockEntity> ANTIQUE_MIRROR = registerStorage(
                BlockEntityReg.ANTIQUE_MIRROR, 6, BlockReg.ANTIQUE_MIRROR);
        public static final  BlockEntityType<StorageBlockEntity> ANTIQUE_WARDROBE = registerStorage(
                BlockEntityReg.ANTIQUE_WARDROBE, 6, BlockReg.ANTIQUE_WARDROBE);
        public static final  BlockEntityType<StorageBlockEntity> ANTIQUE_WALL_SHELF = registerStorage(
                BlockEntityReg.ANTIQUE_WALL_SHELF, 3, BlockReg.ANTIQUE_WALL_SHELF);
        public static final  BlockEntityType<StorageBlockEntity> BLUE_BOOKSHELF = registerStorage(
                BlockEntityReg.BLUE_BOOKSHELF, 3, BlockReg.BLUE_BOOKSHELF);
        public static final  BlockEntityType<StorageBlockEntity> BLUE_BUREAU = registerStorage(
                BlockEntityReg.BLUE_BUREAU, 6, BlockReg.BLUE_BUREAU);
        public static final  BlockEntityType<StorageBlockEntity> BLUE_CABINET = registerStorage(
                BlockEntityReg.BLUE_CABINET, 6, BlockReg.BLUE_CABINET);
        public static final  BlockEntityType<StorageBlockEntity> BLUE_DRESSER = registerStorage(
                BlockEntityReg.BLUE_DRESSER, 3, BlockReg.BLUE_DRESSER);
        public static final  BlockEntityType<StorageBlockEntity> BLUE_WARDROBE = registerStorage(
                BlockEntityReg.BLUE_WARDROBE, 6, BlockReg.BLUE_WARDROBE);
        public static final  BlockEntityType<StorageBlockEntity> CABANA_BOOKCASE = registerStorage(
                BlockEntityReg.CABANA_BOOKCASE, 3, BlockReg.CABANA_BOOKCASE);
        public static final  BlockEntityType<StorageBlockEntity> CABANA_DRESSER = registerStorage(
                BlockEntityReg.CABANA_DRESSER, 6, BlockReg.CABANA_DRESSER);
        public static final  BlockEntityType<StorageBlockEntity> CABANA_VANITY = registerStorage(
                BlockEntityReg.CABANA_VANITY, 3, BlockReg.CABANA_VANITY);
        public static final  BlockEntityType<StorageBlockEntity> CABANA_WARDROBE = registerStorage(
                BlockEntityReg.CABANA_WARDROBE, 6, BlockReg.CABANA_WARDROBE);
        public static final  BlockEntityType<StorageBlockEntity> GORGEOUS_CHEST = registerStorage(
                BlockEntityReg.GORGEOUS_CHEST, 6, BlockReg.GORGEOUS_CHEST);
        public static final  BlockEntityType<StorageBlockEntity> GORGEOUS_CLOSET = registerStorage(
                BlockEntityReg.GORGEOUS_CLOSET, 6, BlockReg.GORGEOUS_CLOSET);
        public static final  BlockEntityType<StorageBlockEntity> GORGEOUS_DESK = registerStorage(
                BlockEntityReg.GORGEOUS_DESK, 3, BlockReg.GORGEOUS_DESK);
        public static final  BlockEntityType<StorageBlockEntity> GORGEOUS_MINI_DRAWER = registerStorage(
                BlockEntityReg.GORGEOUS_MINI_DRAWER, 3, BlockReg.GORGEOUS_MINI_DRAWER);
        public static final  BlockEntityType<StorageBlockEntity> GREEN_DESK = registerStorage(
                BlockEntityReg.GREEN_DESK, 3, BlockReg.GREEN_DESK);
        public static final  BlockEntityType<StorageBlockEntity> GREEN_DRESSER = registerStorage(
                BlockEntityReg.GREEN_DRESSER, 6, BlockReg.GREEN_DRESSER);
        public static final  BlockEntityType<StorageBlockEntity> GREEN_MINI_DRAWER = registerStorage(
                BlockEntityReg.GREEN_MINI_DRAWER, 3, BlockReg.GREEN_MINI_DRAWER);
        public static final  BlockEntityType<StorageBlockEntity> GREEN_PANTRY = registerStorage(
                BlockEntityReg.GREEN_PANTRY, 3, BlockReg.GREEN_PANTRY);
        public static final  BlockEntityType<StorageBlockEntity> GREEN_WARDROBE = registerStorage(
                BlockEntityReg.GREEN_WARDROBE, 6, BlockReg.GREEN_WARDROBE);
        public static final  BlockEntityType<StorageBlockEntity> MINIMALIST_DRESSER = registerStorage(
                BlockEntityReg.MINIMALIST_DRESSER, 6, BlockReg.MINIMALIST_DRESSER);
        public static final  BlockEntityType<StorageBlockEntity> MINIMALIST_MIRROR = registerStorage(
                BlockEntityReg.MINIMALIST_MIRROR, 2, BlockReg.MINIMALIST_MIRROR);
        public static final  BlockEntityType<StorageBlockEntity> MINIMALIST_WARDROBE = registerStorage(
                BlockEntityReg.MINIMALIST_WARDROBE, 6, BlockReg.MINIMALIST_WARDROBE);
        public static final  BlockEntityType<StorageBlockEntity> REGAL_ARMOIRE = registerStorage(
                BlockEntityReg.REGAL_ARMOIRE, 6, BlockReg.REGAL_ARMOIRE);
        public static final  BlockEntityType<StorageBlockEntity> REGAL_BOOKSHELF = registerStorage(
                BlockEntityReg.REGAL_BOOKSHELF, 6, BlockReg.REGAL_BOOKSHELF);
        public static final  BlockEntityType<StorageBlockEntity> REGAL_DRESSER = registerStorage(
                BlockEntityReg.REGAL_DRESSER, 6, BlockReg.REGAL_DRESSER);
        public static final  BlockEntityType<StorageBlockEntity> REGAL_VANITY = registerStorage(
                BlockEntityReg.REGAL_VANITY, 3, BlockReg.REGAL_VANITY);
        public static final  BlockEntityType<StorageBlockEntity> SWEETS_CLOSET = registerStorage(
                BlockEntityReg.SWEETS_CLOSET, 6, BlockReg.SWEETS_CLOSET);
        public static final  BlockEntityType<StorageBlockEntity> SWEETS_DRESSER = registerStorage(
                BlockEntityReg.SWEETS_DRESSER, 6, BlockReg.SWEETS_DRESSER);
        public static final  BlockEntityType<StorageBlockEntity> WOODEN_BLOCK_DRAWERS = registerStorage(
                BlockEntityReg.WOODEN_BLOCK_DRAWERS, 6, BlockReg.WOODEN_BLOCK_DRAWERS);

        // MISC //
        public static final  BlockEntityType<DisplayBlockEntity> DISPLAY_CASE = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "display_case"), BlockEntityType.Builder
                .of((pos, state) -> new DisplayBlockEntity(BlockEntityReg.DISPLAY_CASE, pos, state),
                        BlockReg.DISPLAY_CASE, BlockReg.LONG_DISPLAY_CASE,
                        BlockReg.BLUE_SCIENCE_POD, BlockReg.GREEN_SCIENCE_POD, BlockReg.ORANGE_SCIENCE_POD, BlockReg.RED_SCIENCE_POD)
                .build(null));
        public static final  BlockEntityType<DIYWorkbenchBlockEntity> DIY_WORKBENCH = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "diy_workbench"), BlockEntityType.Builder
                .of((pos, state) -> new DIYWorkbenchBlockEntity(BlockEntityReg.DIY_WORKBENCH, pos, state), BlockReg.DIY_WORKBENCH)
                .build(null));
        public static final  BlockEntityType<GlobeBlockEntity> GLOBE = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "globe"), BlockEntityType.Builder
                .of((pos, state) -> new GlobeBlockEntity(BlockEntityReg.GLOBE, pos, state), BlockReg.GLOBE)
                .build(null));

        public static final  BlockEntityType<DisplayBlockEntity> HANDCART = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "handcart"), BlockEntityType.Builder
                .of((pos, state) -> new DisplayBlockEntity(BlockEntityReg.HANDCART, pos, state), BlockReg.HANDCART)
                .build(null));
        public static final  BlockEntityType<HourglassBlockEntity> HOURGLASS = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "hourglass"), BlockEntityType.Builder
                .of((pos, state) -> new HourglassBlockEntity(BlockEntityReg.HOURGLASS, pos, state), BlockReg.HOURGLASS)
                .build(null));
        public static final  BlockEntityType<MetronomeBlockEntity> METRONOME = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "metronome"), BlockEntityType.Builder
                .of((pos, state) -> new MetronomeBlockEntity(BlockEntityReg.METRONOME, pos, state), BlockReg.METRONOME)
                .build(null));
        public static final  BlockEntityType<NewtonsCradleBlockEntity> NEWTONS_CRADLE = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "newtons_cradle"), BlockEntityType.Builder
                .of((pos, state) -> new NewtonsCradleBlockEntity(BlockEntityReg.NEWTONS_CRADLE, pos, state), BlockReg.NEWTONS_CRADLE)
                .build(null));
        public static final  BlockEntityType<PhonographBlockEntity> PHONOGRAPH = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "phonograph"), BlockEntityType.Builder
                .of((pos, state) -> new PhonographBlockEntity(BlockEntityReg.PHONOGRAPH, pos, state), BlockReg.PHONOGRAPH)
                .build(null));
        public static final  BlockEntityType<PlasmaBallBlockEntity> PLASMA_BALL = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "plasma_ball"), BlockEntityType.Builder
                .of((pos, state) -> new PlasmaBallBlockEntity(BlockEntityReg.PLASMA_BALL, pos, state), BlockReg.PLASMA_BALL)
                .build(null));
        public static final  BlockEntityType<RocketLampBlockEntity> ROCKET_LAMP = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "rocket_lamp"), BlockEntityType.Builder
                .of((pos, state) -> new RocketLampBlockEntity(BlockEntityReg.ROCKET_LAMP, pos, state),
                        BlockReg.BLUE_ROCKET_LAMP, BlockReg.GREEN_ROCKET_LAMP, BlockReg.PINK_ROCKET_LAMP,
                        BlockReg.PURPLE_ROCKET_LAMP, BlockReg.RED_ROCKET_LAMP, BlockReg.TURQUOISE_ROCKET_LAMP,
                        BlockReg.YELLOW_ROCKET_LAMP)
                .build(null));
        public static final  BlockEntityType<SlotMachineBlockEntity> SLOT_MACHINE = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "slot_machine"), BlockEntityType.Builder
                .of((pos, state) -> new SlotMachineBlockEntity(BlockEntityReg.SLOT_MACHINE, pos, state), BlockReg.SLOT_MACHINE)
                .build(null));
        public static final  BlockEntityType<TrainSetBlockEntity> TRAIN_SET = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "train_set"), BlockEntityType.Builder
                .of((pos, state) -> new TrainSetBlockEntity(BlockEntityReg.TRAIN_SET, pos, state), BlockReg.TRAIN_SET)
                .build(null));
        public static final  BlockEntityType<SingleSlotBlockEntity> VASE = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "vase"), BlockEntityType.Builder
                .of((pos, state) -> new SingleSlotBlockEntity(BlockEntityReg.VASE, pos, state),
                        BlockReg.CLASSIC_VASE, BlockReg.FANCY_VASE, BlockReg.LARGE_FANCY_VASE,
                        BlockReg.LARGE_STRIPED_VASE, BlockReg.SMALL_FANCY_VASE, BlockReg.SMALL_STRIPED_VASE)
                .build(null));

        /**
         * @param type  the supplier for the block entity type registry object
         * @param block the block registry object for the block entity type
         * @return the registered block entity type for the ClockBlockEntity
         */
        private static BlockEntityType<ClockBlockEntity> registerClock(BlockEntityType<ClockBlockEntity> type, final Block block, String name) {
            return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    ResourceLocation.fromNamespaceAndPath(MODID, name),
                    BlockEntityType.Builder.of(
                            (pos, state) ->
                                    new ClockBlockEntity(type, pos, state), block
                            ).build(null)
            );
        }

        /**
         * @param type  the supplier for the block entity type registry object
         * @param rows  the number of inventory rows from 1 to 6
         * @param block the block registry object for the block entity type
         * @return the registered block entity type for the StorageBlockEntity
         */
        private static  BlockEntityType<StorageBlockEntity> registerStorage(BlockEntityType<StorageBlockEntity> type, final int rows, final Block block) {
            if (rows < 1 || rows > 6) {
                throw new IllegalArgumentException("[TDRegistry.BlockEntityReg.registerStorage] rows=" + rows + " is out of bounds for range [1,6]");
            }
            return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    ResourceLocation.fromNamespaceAndPath(MODID, BuiltInRegistries.BLOCK.getKey(block).getPath()),
                    BlockEntityType.Builder.of(
                            (pos, state) ->
                                    new StorageBlockEntity(type, pos, state, rows), block
                    ).build(null)
            );
        }

        // PREDICATES //

        private static boolean always(BlockState blockState, BlockGetter level, BlockPos pos) {
            return true;
        }

        private static boolean never(BlockState blockState, BlockGetter level, BlockPos pos) {
            return false;
        }
    }


    public static final class SoundReg {

        /**
         * Forces initialization of this class and all its static fields.
         * Called during mod initialization to ensure all sounds are registered.
         */
        public static void init() {
            // Method intentionally empty - the act of calling it triggers class initialization
        }

        public static final SoundEvent ALARM_CLOCK_TICK = register("block.alarm_clock.tick");
        public static final SoundEvent ALARM_CLOCK_CHIME = register("block.alarm_clock.chime");
        public static final SoundEvent CASH_REGISTER_RING = register("block.cash_register.ring");
        public static final SoundEvent CLOCK_TOWER_TICK = register("block.clock_tower.tick");
        public static final SoundEvent CLOCK_TOWER_CHIME = register("block.clock_tower.chime");
        public static final SoundEvent CUCKOO_CLOCK_TICK = register("block.cuckoo_clock.tick");
        public static final SoundEvent CUCKOO_CLOCK_CHIME = register("block.cuckoo_clock.chime");
        public static final SoundEvent FOLIOT_CLOCK_TICK = register("block.foliot_clock.tick");
        public static final SoundEvent GRANDFATHER_CLOCK_TICK = register("block.grandfather_clock.tick");
        public static final SoundEvent GRANDFATHER_CLOCK_CHIME = register("block.grandfather_clock.chime");
        public static final SoundEvent LANTERN_CLOCK_CHIME = register("block.lantern_clock.chime");
        public static final SoundEvent MANTLE_CLOCK_TICK = register("block.mantle_clock.tick");
        public static final SoundEvent MANTLE_CLOCK_CHIME = register("block.mantle_clock.chime");
        public static final SoundEvent MEDIUM_CLOCK_TICK = register("block.medium_clock.tick");
        public static final SoundEvent MEDIUM_CLOCK_TICK2 = register("block.medium_clock.tick2");
        public static final SoundEvent MEDIUM_CLOCK_CHIME = register("block.medium_clock.chime");
        public static final SoundEvent MEDIUM_CLOCK_CHIME2 = register("block.medium_clock.chime2");
        public static final SoundEvent METRONOME_TICK = register("block.metronome.tick");
        public static final SoundEvent MINI_FIGURE_SQUEAK = register("block.mini_figure.squeak");
        public static final SoundEvent POCKET_WATCH_TICK = register("block.pocket_watch.tick");
        public static final SoundEvent RECOGNIZABLE_CLOCK_CHIME = register("block.recognizable_clock.chime");
        public static final SoundEvent SLATE_CLOCK_CHIME = register("block.slate_clock.chime");

        /**
         * @param name the sound name as specified in the sounds.json file
         * @return a registered sound event for the TanukiDecor namespace and the given sound name
         */

        private static SoundEvent register(final String name) {
            return Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(MODID, name), SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, name)));
        }
    }

    public static final class RecipeReg {

        /**
         * Forces initialization of this class and all its static fields.
         * Called during mod initialization to ensure all recipes are registered.
         */
        public static void init() {
            // Method intentionally empty - the act of calling it triggers class initialization
        }

        public static final RecipeSerializer<DIYRecipe> DIY_SERIALIZER = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, DIYRecipe.Serializer.CATEGORY, new DIYRecipe.Serializer());

        public static final RecipeType<DIYRecipe> DIY = Registry.register(BuiltInRegistries.RECIPE_TYPE, DIYRecipe.Serializer.CATEGORY, new RecipeType<>() {
            @Override
            public String toString() {
                return "diy";
            }
        });
    }

    public static final class MenuReg {

        public static final ExtendedScreenHandlerType<DIYWorkbenchMenu, PosData> DIY_WORKBENCH = new ExtendedScreenHandlerType<>((i, inv, data) ->
                new DIYWorkbenchMenu(i, inv, inv.player.blockPosition(), (Container) inv.player.level().getBlockEntity(data.pos())), PosData.STREAM_CODEC);

        /**
         * Forces initialization of this class and all its static fields.
         * Called during mod initialization to ensure all menu types are registered.
         */
        public static void init() {
            // Method intentionally empty - the act of calling it triggers class initialization
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(MODID, "diy_workbench"), DIY_WORKBENCH);
        }
    }
}
