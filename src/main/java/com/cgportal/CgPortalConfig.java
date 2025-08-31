package com.cgportal;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("cgportal")
public interface CgPortalConfig extends Config
{

//	public interface TotemFletchingConfig extends Config {
//
//		Color RED = Color.decode("#E45F5F");
//		Color GREEN = Color.decode("#9CF575");
//		Color DEFAULT_TEXT_COLOR = Color.decode("#ddc2ff");

	@ConfigItem(
			position = -1,
			keyName = "test0position",
			name = "This is a test of -1 position",
			description = "test description for position 0"
	)
	default Color testColorNegativeOne() { return Color.RED; }

	@ConfigItem(
			position = 0,
			keyName = "test0position",
			name = "This is a test of 0 position",
			description = "test description for position 0"
	)
	default Color testColorZero() { return Color.BLUE; }

	@ConfigItem(
			position = 1,
			keyName = "showWorldType",
			name = "Show the current world type",
			description = "Toggle the display of the current world type"
	)
	default boolean showWorldType() { return false; }

	@ConfigItem(
			position = 2,
			keyName = "highlightPortal",
			name = "Highlight the portal",
			description = "Toggle to keep portal highlighted even when not being hovered over"
	)
	default boolean switchHighlight() { return true; }
}