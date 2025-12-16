package com.moigferdsrte.tanukidecor;

import com.moigferdsrte.tanukidecor.config.TDConfig;
import com.moigferdsrte.tanukidecor.events.IEvent;
import com.moigferdsrte.tanukidecor.events.PlayerClickPosEvent;
import com.moigferdsrte.tanukidecor.network.ServerBoundSelectDIYRecipePacket;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TanukiDecorFabric implements ModInitializer {
	public static final String MOD_ID = "tanukidecor";

	public static ConfigHolder<TDConfig> configHolder;
	public static TDConfig CONFIG;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AutoConfig.register(TDConfig.class, GsonConfigSerializer::new);
		configHolder = AutoConfig.getConfigHolder(TDConfig.class);
		CONFIG = configHolder.getConfig();
		TDRegistry.register();
		PayloadTypeRegistry.playC2S().register(ServerBoundSelectDIYRecipePacket.TYPE, ServerBoundSelectDIYRecipePacket.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(ServerBoundSelectDIYRecipePacket.TYPE, ServerBoundSelectDIYRecipePacket.STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(ServerBoundSelectDIYRecipePacket.TYPE, ServerBoundSelectDIYRecipePacket::handle);
	}
}