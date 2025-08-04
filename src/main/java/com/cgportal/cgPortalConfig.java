package com.cgportal;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface cgPortalConfig extends Config
{
	@ConfigItem(
		keyName = "cg portal",
		name = "highlight cg portal",
		description = "Highlight the cg portal depending on what type of gauntlet mode you are about to enter"
	)
	default String greeting()
	{
		return "Hello";
	}
}
