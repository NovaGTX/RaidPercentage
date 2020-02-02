package com.RaidsPercentage;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.raids.RaidsPlugin;

@Slf4j
@PluginDescriptor(
	name = "Raids Percentage"
)
public class RaidsPercentagePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RaidsPercentageConfig config;

	private boolean inRaidChambers;
	int prevPoints = 0;
	@Override
	protected void startUp() throws Exception
	{
		log.info("Raids Percentage started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Raids Percentage stopped!");
	}


	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		//do previous value check to prevent spam

		if(client.getVar(Varbits.PERSONAL_POINTS) > prevPoints && client.getVar(Varbits.PERSONAL_POINTS) > 0){
			int totalPoints = client.getVar(Varbits.TOTAL_POINTS);
			int personalPoints = client.getVar(Varbits.PERSONAL_POINTS);
			prevPoints = personalPoints;
			double percentage = personalPoints / (totalPoints / 100.0);
			log.info("Your percentage of points is: "+percentage+"%");
		}

	}


	@Provides
	RaidsPercentageConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RaidsPercentageConfig.class);
	}


}
