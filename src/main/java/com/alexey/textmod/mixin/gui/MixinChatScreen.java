package com.alexey.textmod.mixin.gui;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( ChatScreen.class )
public class MixinChatScreen extends Screen
{

    protected MixinChatScreen( Component title )
    {
        super( title );
    }

    @Redirect( method = "extractRenderState", at = @At( value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V" ) )
    private void chatRainbowRect(
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
                90f,
                1f,
                1f,
                true,
                1f,
                1f,
                ( ( this.minecraft.options.getBackgroundColor( Integer.MIN_VALUE ) >> 24 ) & 0xFF ) / 255f
                                                                );
    }

}
