package com.alexey.textmod.mixin.gui.components;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractScrollArea;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( AbstractScrollArea.class )
public abstract class MixinAbstractScrollArea extends AbstractWidget
{

    public MixinAbstractScrollArea(
            int x,
            int y,
            int width,
            int height,
            Component message
                                  )
    {
        super(
                x,
                y,
                width,
                height,
                message
             );
    }

    @Redirect( method = "extractScrollbar", at = @At( value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;" +
                    "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
    ordinal = 0 ) )
    private void blitScroll0(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height
                           )
    {
        ( (IGuiGraphicsExtractor) instance ).textmod$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                90f,
                1f,
                1f,
                true,
                .88f,
                .555f,
                1f
                                                                );
    }
    @Redirect( method = "extractScrollbar", at = @At( value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;" +
                    "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
            ordinal = 2 ) )
    private void blitScroll2(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height
                            )
    {
        ( (IGuiGraphicsExtractor) instance ).textmod$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                90f,
                1f,
                1f,
                true,
                .88f,
                .555f,
                1f
                                                                );
    }
    @Redirect( method = "extractScrollbar", at = @At( value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;" +
                    "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
            ordinal = 1 ) )
    private void blitScroll1(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height
                            )
    {
        final int bgY = this.getY();
        final int bgHeight = this.getHeight();

        final float v0 = (float) ( y - bgY ) / bgHeight;
        final float v1 = (float) ( y + height - bgY ) / bgHeight;
        ( (IGuiGraphicsExtractor) instance ).textmod$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                0f,
                v0,
                0f,
                v1,
                90f,
                1f,
                1f,
                true,
                1f,
                1f,
                1f
                                                                );
    }
    @Redirect( method = "extractScrollbar", at = @At( value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;" +
                    "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
            ordinal = 3 ) )
    private void blitScroll3(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height
                            )
    {
        final int bgY = this.getY();
        final int bgHeight = this.getHeight();

        final float v0 = (float) ( y - bgY ) / bgHeight;
        final float v1 = (float) ( y + height - bgY ) / bgHeight;
        ( (IGuiGraphicsExtractor) instance ).textmod$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                0f,
                v0,
                0f,
                v1,
                90f,
                1f,
                1f,
                true,
                1f,
                1f,
                1f
                                                                );
    }

}
