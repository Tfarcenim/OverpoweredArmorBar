package tfar.overpoweredarmorbar.overlay;

import net.minecraft.client.gui.LayeredDraw;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import tfar.overloadedarmorbar.overlay.OverlayRenderer;

/*
    Class which handles the render event and hides the vanilla armor bar
 */
public class OverlayEventHandler {


    /*
        Class handles the drawing of the armor bar
     */

    public static final LayeredDraw.Layer overlay = (guiGraphics, partialTick) -> {
            OverlayRenderer.renderArmorBar(guiGraphics);
    };

    public static void disableOverlay(RenderGuiLayerEvent.Pre e) {
        if (e.getName().equals(VanillaGuiLayers.ARMOR_LEVEL)) {
            e.setCanceled(true);
        }
    }
}