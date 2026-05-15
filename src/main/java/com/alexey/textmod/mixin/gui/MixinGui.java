package com.alexey.textmod.mixin.gui;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class MixinGui
{

    @Redirect( method = "extractItemHotbar",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;"
                            + "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 0 ) )
    private void rainbowHotbar(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height
                              )
    {
        final var i_graphics = (IGuiGraphicsExtractor) instance;
        i_graphics.textmod$rainbowRect(
                x,
                y,
                x + width,
                y + height - 1,
                0f,
                1f,
                .5f,
                true,
                .65678f,
                .9f,
                .333f
                                      );
        i_graphics.textmod$rainbowOutline(
                x,
                y,
                width,
                height - 1,
                0f,
                1f,
                .5f,
                true,
                .65678f,
                .9f,
                1f
                                         );
    }

    @Redirect( method = "extractItemHotbar",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;"
                            + "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 1 ) )
    private void rainbowHotbarSelectedItem(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height,
            @Local( name = "screenCenter" ) int screenCenter,
            @Local( name = "hotbarWidth" ) int hotbarWidth,
            @Local( name = "halfHotbar" ) int halfHotbar
                                          )
    {
        final var i_graphics = (IGuiGraphicsExtractor) instance;

        final int widgetX = screenCenter - halfHotbar - 1;
        final int widgetY = y + 1;

        final float u0 = (float) ( x + 1 - widgetX ) / hotbarWidth;
        final float v0 = (float) ( y + 1 - widgetY ) / ( height - 2 );
        final float u1 = (float) ( x + width - 2 - widgetX ) / hotbarWidth;
        final float v1 = (float) ( y + height - 2 - widgetY ) / ( height - 2 );

        i_graphics.textmod$rainbowRect(
                x + 1,
                y + 1,
                x + width - 2,
                y + height - 2,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                1f,
                1f,
                .333f
                                      );
        i_graphics.textmod$rainbowOutline(
                x + 1,
                y + 1,
                width - 2,
                height - 2,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                1f,
                1f,
                1f
                                         );
    }

    @Redirect( method = "extractItemHotbar",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;"
                            + "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 2 ) )
    private void rainbowHotbarOffhandLeft(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height,
            @Local( name = "screenCenter" ) int screenCenter,
            @Local( name = "hotbarWidth" ) int hotbarWidth,
            @Local( name = "halfHotbar" ) int halfHotbar
                                          )
    {
        final var i_graphics = (IGuiGraphicsExtractor) instance;

        final int offhandHeight = 22;
        final int widgetX = screenCenter - halfHotbar - 1;
        final int widgetY = y + 1;

        final float u0 = (float) ( x - widgetX ) / hotbarWidth;
        final float v0 = (float) ( y + 1 - widgetY ) / offhandHeight;
        final float u1 = (float) ( x + width - 7 - widgetX ) / hotbarWidth;
        final float v1 = (float) ( y + height - 3 - widgetY ) / offhandHeight;

        i_graphics.textmod$rainbowRect(
                x,
                y + 1,
                x + width - 7,
                y + height - 3,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                1f,
                1f,
                .333f
                                      );
        i_graphics.textmod$rainbowOutline(
                x,
                y + 1,
                width - 7,
                height - 3,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                1f,
                1f,
                1f
                                         );
    }

    @Redirect( method = "extractItemHotbar",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;"
                            + "blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 3 ) )
    private void rainbowHotbarOffhandRight(
            GuiGraphicsExtractor instance,
            RenderPipeline renderPipeline,
            Identifier location,
            int x,
            int y,
            int width,
            int height,
            @Local( name = "screenCenter" ) int screenCenter,
            @Local( name = "hotbarWidth" ) int hotbarWidth,
            @Local( name = "halfHotbar" ) int halfHotbar
                                         )
    {
        final var i_graphics = (IGuiGraphicsExtractor) instance;

        final int offhandHeight = 22;
        final int widgetX = screenCenter - halfHotbar - 1;
        final int widgetY = y + 1;

        final float u0 = (float) ( x + 7 - widgetX ) / hotbarWidth;
        final float v0 = (float) ( y + 1 - widgetY ) / offhandHeight;
        final float u1 = (float) ( x + 7 + width - 7 - widgetX ) / hotbarWidth;
        final float v1 = (float) ( y + height - 3 - widgetY ) / offhandHeight;

        i_graphics.textmod$rainbowRect(
                x + 7,
                y + 1,
                x + 7 + width - 7,
                y + height - 3,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                1f,
                1f,
                .333f
                                      );
        i_graphics.textmod$rainbowOutline(
                x + 7,
                y + 1,
                width - 7,
                height - 3,
                u0,
                v0,
                u1,
                v1,
                0f,
                1f,
                .5f,
                true,
                1f,
                1f,
                1f
                                         );
    }

    @Redirect( method = "extractSelectedItemName",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;"
                            + "textWithBackdrop(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIII)V" ) )
    private void rainbowSelectedItemName(
            GuiGraphicsExtractor instance,
            Font font,
            Component str,
            int textX,
            int textY,
            int textWidth,
            int textColor
                                        )
    {
        ( (IGuiGraphicsExtractor) instance ).textmod$rainbowRect(
                textX,
                textY + font.lineHeight,
                textX + textWidth,
                textY + font.lineHeight + 1,
                0f,
                1f,
                .5f,
                true,
                1f,
                1f,
                ( textColor >> 24 ) & 0xFF
                                                                );
        instance.text(
                font,
                str,
                textX,
                textY,
                textColor,
                true
                     );
    }

}