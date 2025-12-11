package com.moigferdsrte.tanukidecor.config;

import com.moigferdsrte.tanukidecor.TanukiDecorFabric;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = TanukiDecorFabric.MOD_ID)
public class TDConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean isDIYWorkbenchEnabled = true;
    @ConfigEntry.Gui.Tooltip
    public double slotMachineJackboxChance = 15.0D;
}
