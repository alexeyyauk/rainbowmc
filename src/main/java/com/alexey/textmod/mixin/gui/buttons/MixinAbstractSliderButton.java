package com.alexey.textmod.mixin.gui.buttons;


import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(AbstractSliderButton.class)
public  class MixinAbstractSliderButton
{

    @Shadow
    protected double value;

    @Inject( method = "extractWidgetRenderState",
            at = @At( "HEAD" ),
            cancellable = true )
    public void renderSlider(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float a,
            CallbackInfo ci
                            )
    {

        AbstractSliderButton button = (AbstractSliderButton) (Object) this;
        int x = button.getX();
        int y = button.getY();
        int width = button.getWidth();
        int height = button.getHeight();
        boolean isHovered = button.isHovered();


        int fillColor = 0x862B2B2B;
        graphics.fill(
                x,
                y,
                x + width,
                y + height,
                fillColor
                     );

        float bright = isHovered ? 1.0f : 0.555f;

        IGuiGraphicsExtractor rainbow = (IGuiGraphicsExtractor) graphics;

        int handleWidth = 7;
        int handleX = x + (int) ( this.value * (double) ( width - handleWidth ) );
        int handleX2 = handleX + handleWidth;
        int y2 = y + height;

        graphics.fill(
                handleX,
                y,
                handleX2,
                y2,
                0xFF000000
                     );

        //        rainbow.textmod$rainbowRect(
        //                handleX, y, handleX2, y2,
        //                45f,
        //                0.01f,
        //                0.3f,
        //                true,
        //                1.0f,
        //                bright,
        //                1.0f
        //        );


        rainbow.textmod$rainbowOutline(
                x,
                y,
                width,
                height,
                45f,
                1.3f,
                0.3f,
                true,
                1.0f,
                bright,
                1.0f
                                      );


        Component text = button.getMessage();
        final var mc = Minecraft.getInstance();
        graphics.centeredText(
                mc.font,
                text,
                x + width / 2,
                y + ( height - 8 ) / 2,
                -1
                             );

        ci.cancel();

    }

}
