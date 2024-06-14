package tfar.overpoweredarmorbar.overlay;

import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import tfar.overloadedarmorbar.overlay.OverlayRenderer;

/*
    Class which handles the render event and hides the vanilla armor bar
 */
public class OverlayEventHandler {


    /*
        Class handles the drawing of the armor bar
     */

    public static final IGuiOverlay overlay = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        if (!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {
            gui.setupOverlayRenderState(true, false);
            OverlayRenderer.renderArmorBar(gui, guiGraphics, screenWidth, screenHeight);
        }
    };

    public static void disableOverlay(RenderGuiOverlayEvent.Pre e) {
        if (e.getOverlay().id().equals(VanillaGuiOverlay.ARMOR_LEVEL.id())) {
            e.setCanceled(true);
        }
    }
}