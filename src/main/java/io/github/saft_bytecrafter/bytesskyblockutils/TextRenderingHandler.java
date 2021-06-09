package io.github.saft_bytecrafter.bytesskyblockutils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class TextRenderingHandler extends Gui {

    public  static void renderText(Minecraft minecraft, String content, int color, double scale, int x, int y){

        GL11.glScaled(scale, scale, scale);
        y -= minecraft.fontRendererObj.FONT_HEIGHT;
        y += minecraft.fontRendererObj.FONT_HEIGHT*scale;
        minecraft.fontRendererObj.drawString(content, x, y, color, true);
        GL11.glScaled(Math.pow(scale, -1), Math.pow(scale, -1), Math.pow(scale, -1)); //idk what this does :(
        GlStateManager.color(1, 1, 1, 1); //idk what this does :(
    }
}
