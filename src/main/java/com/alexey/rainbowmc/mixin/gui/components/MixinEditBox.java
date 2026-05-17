package com.alexey.rainbowmc.mixin.gui.components;


import com.alexey.rainbowmc.imixin.IGuiGraphicsExtractor;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( EditBox.class )
public abstract class MixinEditBox extends AbstractWidget
{

    public MixinEditBox(
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
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;" +
                            "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V" ) )
    public void render(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height
                      )
    {
        float bright = this.isActive() && this.isFocused() ? 1f : ( this.isActive() ? .555f : .35678f );
        float saturation = this.isActive() ? 1f : .555f;
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
                .333f,
                .333f,
                this.alpha
                                        );
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

}
