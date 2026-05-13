package com.alexey.textmod.mixin.gui;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import com.alexey.textmod.render.Pipelines;
import com.alexey.textmod.render.RainbowRect;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin( GuiGraphicsExtractor.class )
public abstract class MixinGuiGraphicsExtractor implements IGuiGraphicsExtractor
{
    @Shadow
    @Final
    public GuiRenderState guiRenderState;

    @Shadow
    @Final
    private Matrix3x2fStack pose;

    @Shadow
    @Final
    public GuiGraphicsExtractor.ScissorStack scissorStack;

    @Shadow
    public abstract int guiWidth();

    @Shadow
    public abstract int guiHeight();

    @Override
    public void textmod$rainbowRect(
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
                                   )
    {
        this.textmod$innerRainbowRect(
                TextureSetup.noTexture(),
                x0,
                y0,
                x1,
                y1,
                u0,
                v0,
                u1,
                v1,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                     );
    }

    @Override
    public void textmod$rainbowRect(
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
                                   )
    {
        this.textmod$innerRainbowRect(
                TextureSetup.noTexture(),
                x0,
                y0,
                x1,
                y1,
                0f,
                0f,
                1f,
                1f,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                     );
    }

    @Override
    public void textmod$rainbowOutline(
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
                                      )
    {
        float invW = 1f / this.guiWidth();
        float invH = 1f / this.guiHeight();
        this.textmod$rainbowRect(
                x,
                y,
                x + width,
                y + 1,
                x * invW,
                ( y + 1 ) * invH,
                ( x + width ) * invW,
                y * invH,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                );
        this.textmod$rainbowRect(
                x,
                y + height - 1,
                x + width,
                y + height,
                x * invW,
                ( y + height ) * invH,
                ( x + width ) * invW,
                ( y + height - 1 ) * invH,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                );
        this.textmod$rainbowRect(
                x,
                y + 1,
                x + 1,
                y + height - 1,
                x * invW,
                ( y + height - 1 ) * invH,
                ( x + 1 ) * invW,
                ( y + 1 ) * invH,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                );
        this.textmod$rainbowRect(
                x + width - 1,
                y + 1,
                x + width,
                y + height - 1,
                ( x + width - 1 ) * invW,
                ( y + height - 1 ) * invH,
                ( x + width ) * invW,
                ( y + 1 ) * invH,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                );
    }

    @Override
    public void textmod$innerRainbowRect(
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
                                        )
    {
        this.guiRenderState.addGuiElement( new RainbowRect(
                Pipelines.GUI_RAINBOW,
                textureSetup,
                new Matrix3x2f( this.pose ),
                x0,
                y0,
                x1,
                y1,
                u0,
                v0,
                u1,
                v1,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha,
                this.scissorStack.peek()
        ) );
    }

}
