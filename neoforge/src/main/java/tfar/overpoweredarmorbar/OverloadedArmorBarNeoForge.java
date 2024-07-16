package tfar.overpoweredarmorbar;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import tfar.overloadedarmorbar.OverloadedArmorBar;
import tfar.overpoweredarmorbar.overlay.OverlayEventHandler;

@Mod(value = OverloadedArmorBar.MODID,dist = Dist.CLIENT)
public class OverloadedArmorBarNeoForge {

	public OverloadedArmorBarNeoForge(IEventBus bus, ModContainer modContainer) {
		modContainer.registerConfig(ModConfig.Type.CLIENT, Configs.CLIENT_SPEC);
		bus.addListener(this::setup);
	}
	public void setup(final RegisterGuiLayersEvent event) {
		//Register Armor Renderer for events
		event.registerAbove(VanillaGuiLayers.ARMOR_LEVEL, ResourceLocation.fromNamespaceAndPath(OverloadedArmorBar.MODID,OverloadedArmorBar.MODID),
				OverlayEventHandler.overlay);
		NeoForge.EVENT_BUS.addListener(OverlayEventHandler::disableOverlay);
	}
}