package com.alexey.textmod.mixin.gui.buttons;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import com.alexey.textmod.render.GuiRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Button.Plain.class)
public abstract class MixinButtonPlain extends Button
{

    protected MixinButtonPlain(
            int x,
            int y,
            int width,
            int height,
            Component message,
            OnPress onPress,
            CreateNarration createNarration
                              )
    {
        super(
                x,
                y,
                width,
                height,
                message,
                onPress,
                createNarration
             );
    }

    /**
     * todo переделать говно
     *  @see net.minecraft.client.gui.components.WidgetSprites#get(boolean, boolean)
     *  @see net.minecraft.client.gui.components.AbstractButton#extractDefaultSprite(GuiGraphicsExtractor)
     *  при наведении меняет текстуру кнопки, надо менять
     */
    @Inject( method = "extractContents",
            at = @At( "HEAD" ),
            cancellable = true )
    public void customButtonsRender(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float a,
            CallbackInfo ci
                                   )
    {
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();
        float bright = this.isHoveredOrFocused() ? 1.0f : 0.555f;
        final var i_graphics = ( (IGuiGraphicsExtractor) graphics );

        i_graphics.textmod$rainbowRect(
                x,
                y,
                x + width,
                y + height,
                0f,
                1f,
                .5f,
                true,
                1.0f,
                1f,
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
                1f,
                bright,
                this.alpha
                                         );
        this.extractDefaultLabel( graphics.textRendererForWidget(
                this,
                GuiGraphicsExtractor.HoveredTextEffects.NONE
                                                                ) );

        ci.cancel();
    }

}