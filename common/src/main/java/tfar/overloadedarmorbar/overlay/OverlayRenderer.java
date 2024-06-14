package tfar.overloadedarmorbar.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import tfar.overloadedarmorbar.platform.Services;

public class OverlayRenderer {

    private final static int UNKNOWN_ARMOR_VALUE = -1;
    private static int previousArmorValue = UNKNOWN_ARMOR_VALUE;
    private final static int ARMOR_ICON_SIZE = 9;
    //private final static int ARMOR_FIRST_HALF_ICON_SIZE = 5;
    private final static int ARMOR_SECOND_HALF_ICON_SIZE = 4;

    private static ArmorIcon[] armorIcons;

    protected static final ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation("textures/gui/icons.png");

    public static void drawTexturedModalRect(GuiGraphics stack, int x, int y, int textureX, int textureY, int width, int height) {
        stack.blit(GUI_ICONS_LOCATION,x, y, textureX, textureY, width, height);
    }


    public static void renderArmorBar(Gui gui, GuiGraphics graphics, int screenWidth, int screenHeight) {
        int currentArmorValue = Minecraft.getInstance().player.getArmorValue();
        int xStart = screenWidth / 2 - 91;
        int yPosition = screenHeight - Services.PLATFORM.getLeftHeight(gui);

        //Save some CPU cycles by only recalculating armor when it changes
        if (currentArmorValue != previousArmorValue) {
            //Calculate here
            armorIcons = ArmorBar.calculateArmorIcons(currentArmorValue);

            //Save value for next cycle
            previousArmorValue = currentArmorValue;
        }

        int armorIconCounter = 0;
        for (ArmorIcon icon : armorIcons) {
            int xPosition = xStart + armorIconCounter * 8;
            switch (icon.armorIconType) {
                case NONE:
                    ArmorIconColor color = icon.primaryArmorIconColor;
                    color4f(color.Red, color.Green, color.Blue, color.Alpha);
                    if (currentArmorValue > 20) {
                        //Draw the full icon as we have wrapped
                        drawTexturedModalRect(graphics,xPosition, yPosition, 34, 9, ARMOR_ICON_SIZE, ARMOR_ICON_SIZE);
                    } else {
                        if (Services.PLATFORM.getConfig().showEmptyArmorIcons() && (Services.PLATFORM.getConfig().alwaysShowArmorBar() || currentArmorValue > 0)) {
                            //Draw the empty armor icon
                            drawTexturedModalRect(graphics,xPosition, yPosition, 16, 9, ARMOR_ICON_SIZE, ARMOR_ICON_SIZE);
                        }
                    }
                    break;
                case HALF:
                    ArmorIconColor firstHalfColor = icon.primaryArmorIconColor;
                    ArmorIconColor secondHalfColor = icon.secondaryArmorIconColor;

                    color4f(firstHalfColor.Red, firstHalfColor.Green, firstHalfColor.Blue, firstHalfColor.Alpha);
                    drawTexturedModalRect(graphics,xPosition, yPosition, 25, 9, 5, ARMOR_ICON_SIZE);

                    color4f(secondHalfColor.Red, secondHalfColor.Green, secondHalfColor.Blue, secondHalfColor.Alpha);
                    if (currentArmorValue > 20) {
                        //Draw the second half as full as we have wrapped
                        drawTexturedModalRect(graphics,xPosition + 5, yPosition, 39, 9, ARMOR_SECOND_HALF_ICON_SIZE, ARMOR_ICON_SIZE);
                    } else {
                        //Draw the second half as empty
                        drawTexturedModalRect(graphics,xPosition + 5, yPosition, 30, 9, ARMOR_SECOND_HALF_ICON_SIZE, ARMOR_ICON_SIZE);
                    }
                    break;
                case FULL:
                    ArmorIconColor fullColor = icon.primaryArmorIconColor;
                    color4f(fullColor.Red, fullColor.Green, fullColor.Blue, fullColor.Alpha);
                    drawTexturedModalRect(graphics,xPosition, yPosition, 34, 9, ARMOR_ICON_SIZE, ARMOR_ICON_SIZE);
                    break;
                default:
                    break;
            }
            armorIconCounter++;
        }
        color4f(1, 1, 1, 1);
        Services.PLATFORM.offsetLeftHeight(gui,10);
    }

    private static void color4f(float r, float g, float b, float a){
        RenderSystem.setShaderColor(r,g, b, a);
    }



}
