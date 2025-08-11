package com.cgportal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.EnumSet;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
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

// ** use the gui java decompiler just downloaded to see source code from .jar file in plugins -> .runelite
// reference the kill count viewer plugin (that new one u downloaded)
// **

// data to get:
// boss kc (can use new kc plugin to help, but it updates slow and will need to improve it somehow)

// need to:
// find how menu entry swapper works (makes cg left clickable -> the "swap")
// display kc in real time (find some way to up-date right after kill, if not possible, track if user beats the gauntlet, then add an artificial +1 to the kc, to act as a placeholder while user either leaves and can get data, or does another kc. We will grab in the background)

@Slf4j
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

        // Add a line on the overlay for boss kc
        // need to find boss kc here -> api?
        panelComponent.getChildren().add(LineComponent.builder()
                        .left("Boss Kc:")
                        .right(Integer.toString(client.getEnergy()))
                        .build());

        // Add a line on the overlay to test getting total level by calling client.getTotalLevel()
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Total Level:")
                .right(Integer.toString(client.getTotalLevel()))
                .build());

        ObjectComposition portal = client.getObjectDefinition(ObjectID.GAUNTLET_ENTRANCE);

        // int getId();
        // string getName();

        // Add a line on the overlay to test getting the gauntlet portal's ID based off calling the object using .getId()
        panelComponent.getChildren().add(LineComponent.builder()
                .left("gauntlet portal id:")
                .right(Integer.toString(portal.getId()))
                .build());

        // Add a line on the overlay to test getting the gauntlet portal's name based off calling the object using .getName()
        panelComponent.getChildren().add(LineComponent.builder()
                .left("gauntlet portal name:")
                .right(portal.getName())
                .build());

        // log portal Id
        log.info(Integer.toString(portal.getId()));

        // test to get imposters name ? -> works -> The Gauntlet
        log.info("Portal Imposter .getName()" + portal.getImpostor().getName());

        // get gauntlet portal impostor Ids and loop through them
        int[] portalIds = portal.getImpostorIds();
        for (int Ids : portalIds) {

            log.info("Portal Id: " + Ids);

        }

        // log above printed 36083, 36084
        // public static final int GAUNTLET_ENTRANCE_HM_DISABLED = 36083;
        // public static final int GAUNTLET_ENTRANCE_HM_ENABLED = 36084;



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