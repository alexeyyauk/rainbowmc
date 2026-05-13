package com.alexey.textmod.imixin;

import net.minecraft.client.gui.render.TextureSetup;

public interface IGuiGraphicsExtractor
{

    void textmod$rainbowRect(
            int x0,
            int y0,
            int x1,
            int y1,
            float u0,
            float v0,
            float u1,
            float v1,
            float angle,
            float freq,
            float speed,
            boolean smooth,
            float sat,
            float bright,
            float alpha
                            );
    void textmod$rainbowRect(
            int x0,
            int y0,
            int x1,
            int y1,
            float angle,
            float freq,
            float speed,
            boolean smooth,
            float sat,
            float bright,
            float alpha
                            );
    void textmod$rainbowOutline(
            int x,
            int y,
            int width,
            int height,
            float angle,
            float freq,
            float speed,
            boolean smooth,
            float sat,
            float bright,
            float alpha
                               );
    void textmod$innerRainbowRect(
            TextureSetup textureSetup,
            int x0,
            int y0,
            int x1,
            int y1,
            float u0,
            float v0,
            float u1,
            float v1,
            float angle,
            float freq,
            float speed,
            boolean smooth,
            float sat,
            float bright,
            float alpha
                                 );

}
