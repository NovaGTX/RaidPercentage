package com.RaidsPercentage;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RaidsPercentage
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RaidsPercentagePlugin.class);
		RuneLite.main(args);
	}
}