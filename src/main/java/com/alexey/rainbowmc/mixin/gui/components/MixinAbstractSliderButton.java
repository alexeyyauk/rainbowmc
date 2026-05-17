package com.alexey.rainbowmc.mixin.gui.components;


import com.alexey.rainbowmc.imixin.IGuiGraphicsExtractor;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractSliderButton.class)
public abstract class MixinAbstractSliderButton extends AbstractWidget.WithInactiveMessage
{

    @Shadow
    protected double value;

    @Shadow
    protected boolean canChangeValue;

    public MixinAbstractSliderButton(
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

    @Redirect( method = "extractWidgetRenderState",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;"
                            + "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V",
                    ordinal = 0 ) )
    public void rainbowSliderBorder(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height,
            int color
                            )
    {
        float bright = .42f;
        float saturation = .42f;
        final var i_graphics = ( (IGuiGraphicsExtractor) instance );

        i_graphics.rainbowmc$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                0f,
                1f,
                .5f,
                true,
                saturation,
                bright,
                this.alpha
                                        );
        bright = .5678f;
        saturation = .5678f;
        i_graphics.rainbowmc$rainbowOutline(
                x,
                y,
                width,
                height,
                0f,
                1f,
                .5f,
                true,
                saturation,
                bright,
                this.alpha
                                           );
    }

    @Redirect( method = "extractWidgetRenderState",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;"
                            + "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V",
                    ordinal = 1 ) )
    public void rainbowSliderPolzynok(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height,
            int color
                            )
    {
        boolean activated = !this.isActive() || !this.isHovered && !this.canChangeValue;
        float bright = activated ? .5678f : .67f;
        float saturation = activated ? .5678f : .67f;
        final int widgetX = this.getX();
        final int widgetY = this.getY();
        final int widgetWidth = this.getWidth();
        final int widgetHeight = this.getHeight();
        final float u0 = (float) ( x - widgetX ) / widgetWidth;
        final float v0 = (float) ( y - widgetY ) / widgetHeight;
        final float u1 = (float) ( x + width - widgetX ) / widgetWidth;
        final float v1 = (float) ( y + height - widgetY ) / widgetHeight;
        final var i_graphics = ( (IGuiGraphicsExtractor) instance );

        i_graphics.rainbowmc$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                saturation,
                bright,
                this.alpha
                                        );
        bright = activated ? .6767f : 1f;
        saturation = activated ? .6767f : 1f;
        i_graphics.rainbowmc$rainbowOutline(
                x,
                y,
                width,
                height,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                saturation,
                bright,
                this.alpha
                                           );
    }

}
