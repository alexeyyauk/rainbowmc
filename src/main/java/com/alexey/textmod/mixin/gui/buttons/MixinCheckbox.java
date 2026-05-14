package com.alexey.textmod.mixin.gui.buttons;

import com.alexey.textmod.render.GuiRender;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.Component;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Checkbox.class)
public class MixinCheckbox
{

    @Inject( method = "extractContents",
            at = @At( value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;font:Lnet/minecraft/client/gui/Font;",
                    shift = At.Shift.AFTER,
                    opcode = Opcodes.GETFIELD ),
            cancellable = true )
    public void render(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float a,
            CallbackInfo ci,
            @Local( name = "minecraft" )
            Minecraft minecraft
            )
    {

        Checkbox button = (Checkbox) (Object) this;

        int x = button.getX();
        int y = button.getY();
        int width = button.getWidth();
        int height = button.getHeight();
        boolean isHovered = button.isHovered();

        GuiRender.renderButton(
                graphics,
                x,
                y,
                width,
                height,
                isHovered
                              );

        Component text = button.getMessage();
        graphics.centeredText(
                minecraft.font,
                text,
                x + width / 2,
                y + ( height - 8 ) / 2,
                0xFFFFFFFF
                             );

        ci.cancel();

    }

}
