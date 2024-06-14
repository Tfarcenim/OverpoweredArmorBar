package tfar.overpoweredarmorbar.platform;

import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import tfar.overloadedarmorbar.platform.MLConfig;
import tfar.overloadedarmorbar.platform.services.IPlatformHelper;
import tfar.overpoweredarmorbar.Configs;

public class ForgePlatformHelper implements IPlatformHelper {

    final MLConfig config = new Configs();

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public MLConfig getConfig() {
        return config;
    }

    @Override
    public void offsetLeftHeight(Gui gui, int offset) {
        if (gui instanceof ForgeGui forgeGui) {
            forgeGui.leftHeight += offset;
        }
    }

    @Override
    public int getLeftHeight(Gui gui) {
        return gui instanceof ForgeGui forgeGui ? forgeGui.leftHeight : 0;
    }
}