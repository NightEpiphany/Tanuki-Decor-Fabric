/*
 * Copyright (c) 2023 Skyler James
 * Permission is granted to use, modify, and redistribute this software, in parts or in whole,
 * under the GNU LGPLv3 license (https://www.gnu.org/licenses/lgpl-3.0.en.html)
 */

package com.moigferdsrte.tanukidecor.client;

import com.moigferdsrte.tanukidecor.events.RenderGuiLayerEvent;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import com.moigferdsrte.tanukidecor.TDRegistry;
import com.moigferdsrte.tanukidecor.block.seat.ISeatProvider;
import com.moigferdsrte.tanukidecor.client.blockentity.clock.*;
import com.moigferdsrte.tanukidecor.client.blockentity.misc.*;
import com.moigferdsrte.tanukidecor.client.menu.DIYWorkbenchScreen;

import java.util.HashSet;
import java.util.Set;

public final class TDClientEvents {

    public static void register() {

        ClientRecipeCollections.register();
        ModHandler.onRegisterMenuScreens();
    }


        public static void onRenderOverlay(final RenderGuiLayerEvent.Pre event) {
            final Player player = Minecraft.getInstance().player;
            if (ResourceLocation.withDefaultNamespace("vehicle_health").equals(event.getName())
                    && player != null && player.isPassenger()
                    && ISeatProvider.IS_SEAT_ENTITY.test(player.getVehicle())) {
                event.setCanceled(true);
            }
        }


    public static final class ModHandler {


        public static void onRegisterMenuScreens() {
            MenuScreens.register(TDRegistry.MenuReg.DIY_WORKBENCH, DIYWorkbenchScreen::new);
            ClientRecipeCollections.registerSearchTrees();
        }
    }
}
