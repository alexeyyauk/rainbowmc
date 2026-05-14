package com.alexey.textmod.render;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.awt.*;

public class GuiRender {

    public static void renderButton(GuiGraphicsExtractor g, int x, int y, int width, int height, boolean isHovered){

        int fillColor = 0x862B2B2B;
        g.fill(x, y, x + width, y + height, fillColor);

        float bright = isHovered ? 1.0f : 0.555f;

        IGuiGraphicsExtractor rainbow = (IGuiGraphicsExtractor) g;
        rainbow.textmod$rainbowOutline(
                x, y, width, height,
                45f,
                1.3f,
                0.3f,
                true,
                1.0f,
                bright,
                1.0f
        );

    }

}
