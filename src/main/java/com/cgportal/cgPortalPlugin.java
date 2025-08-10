package com.cgportal;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.OverlayManager;

import net.runelite.api.gameval.ObjectID;
import net.runelite.api.TileObject;
import net.runelite.api.TileItem;
import net.runelite.api.TileFunction;

// portal ID: 37340
// to display in infobox:
// portal id
// portal type (which gauntlet will you enter if you left-click)

// options in plugins:
// color for each gauntlet type (let players pick the colors, default red blue)
// tick box - display color outline only on hover, or highlight entire time

// 2 ideas:
// make blue or red portal left click (similar to menu entry swap)
// have gauntlet icon and kill count hovering in / middle of portal (similar to new boss killcount plugin) -> have toggle for either kc, can choose to display one or the other


@PluginDescriptor(
		name = "Gauntlet Portal",
		description = "Highlights portal and swaps Corrupted Gauntlet to left click",
		tags = {"gauntlet", "portal", "corrupted", "highlight", "swap"}
)
public class cgPortalPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private cgPortalOverlay overlay;

	@Inject
	private cgPortalConfig config;

	@Provides
	cgPortalConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(cgPortalConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}
}