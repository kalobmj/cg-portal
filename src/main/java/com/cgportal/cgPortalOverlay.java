package com.cgportal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.EnumSet;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.WorldType;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import net.runelite.client.ui.overlay.components.LineComponent;

import net.runelite.api.gameval.ObjectID;
import net.runelite.api.ObjectComposition;

// api object composition

// portal ID: 37340
// to display in infobox:
// portal id
// portal type (which gauntlet will you enter if you left click)

// options in plugins:
// color for each gauntlet type (let players pick the colors, default red blue)
// tick box - display color outline only on hover, or highlight entire time

class cgPortalOverlay extends Overlay
{
    private final Client client;
    private final cgPortalConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private cgPortalOverlay(Client client, cgPortalConfig config)
    {
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        this.client = client;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();
        String overlayTitle = "Gaunlet Portal Infobox";

        // Build overlay title
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(overlayTitle)
                .color(Color.CYAN)
                .build());

        // Set the size of the overlay (width)
        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(overlayTitle) + 30,
                0));

        // Add a line on the overlay for world number
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Portal Id:")
                .right(Integer.toString(client.getWorld()))
                .build());

        // If showing world type, determine world type and add the extra line
        if (config.showWorldType())
        {
            EnumSet<WorldType> worldType = client.getWorldType();
            String currentWorldType;

            if (worldType.contains(WorldType.MEMBERS))
            {
                currentWorldType = "Members";
            }
            else
            {
                currentWorldType = "Free";
            }

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Type:")
                    .right(currentWorldType)
                    .build());
        }

        return panelComponent.render(graphics);
    }
}