package com.example.examplemod.platform;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.Gui;
import tfar.overloadedarmorbar.platform.MLConfig;
import tfar.overloadedarmorbar.platform.services.IPlatformHelper;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public MLConfig getConfig() {
        return null;
    }

    @Override
    public void offsetLeftHeight(Gui gui, int offset) {

    }

    @Override
    public int getLeftHeight(Gui gui) {
        return 0;
    }
}
