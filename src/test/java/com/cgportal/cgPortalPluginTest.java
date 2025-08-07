package com.cgportal;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class cgPortalPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(cgPortalPlugin.class);
		RuneLite.main(args);
	}
}