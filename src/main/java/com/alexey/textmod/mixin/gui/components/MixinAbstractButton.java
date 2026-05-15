package com.alexey.textmod.mixin.gui.components;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin( AbstractButton.class )
public abstract class MixinAbstractButton extends AbstractWidget.WithInactiveMessage
{
    @Shadow
    private @Nullable Supplier<Boolean> overrideRenderHighlightedSprite;

    public MixinAbstractButton(
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

    @Redirect( method = "extractDefaultSprite",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;" +
                            "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V" ) )
    private void extractRainbowSprite(
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
        boolean hovered = this.overrideRenderHighlightedSprite != null ? this.overrideRenderHighlightedSprite.get() : this.isHoveredOrFocused() ;
        float bright = this.active && hovered ? 1f : ( this.active ? .555f : .35678f );
        float fillBright = this.active && hovered ? 1f : .6767f;
        float saturation = this.active ? 1f : .555f;
        final var i_graphics = ( (IGuiGraphicsExtractor) instance );

        i_graphics.textmod$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                0f,
                1f,
                .5f,
                true,
                saturation,
                fillBright,
                ( this.alpha * 125f ) / 255f
                                      );
        i_graphics.textmod$rainbowOutline(
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

}
