package com.alexey.textmod.mixin.gui.inventory;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( AbstractContainerScreen.class )
public class MixinAbstractContainerScreen
{

    @Redirect( method = "extractSlot",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V" ) )
    private void rainbowQuickCraftSlot(
            GuiGraphicsExtractor instance,
            int x0,
            int y0,
            int x1,
            int y1,
            int col
                            )
    {
        ( (IGuiGraphicsExtractor) instance ).textmod$rainbowRect(
                x0,
                y0,
                x1,
                y1,
                0f,
                1f,
                1f,
                true,
                1f,
                1f,
                ( ( col >> 24 ) & 0xFF ) / 255f
                );
    }

    @Redirect( method = "extractSlotHighlightBack", at = @At( value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V" ) )
    private void rainbowSlotHighlight(
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
                x + 4,
                y + 4,
                x+ width - 4,
                y+ height - 4,
                0f,
                1f,
                1f,
                true,
                1f,
                1f,
                .5f
                                                                );
    }

}
