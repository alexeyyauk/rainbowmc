package com.alexey.textmod.mixin.gui.buttons;

import com.alexey.textmod.render.GuiRender;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.alexey.textmod.utils.IMinecraftInstance.mc;

@Mixin(CycleButton.class)
public class MixinCycleButton {

    @Inject(method = "extractContents", at = @At("HEAD"), cancellable = true)
    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci){

        CycleButton button = (CycleButton) (Object) this;

        int x = button.getX();
        int y = button.getY();
        int width = button.getWidth();
        int height = button.getHeight();
        boolean isHovered = button.isHovered();

        GuiRender.renderButton(graphics, x, y, width, height, isHovered);

        Component text = button.getMessage();
        graphics.centeredText(mc.font, text, x + width / 2, y + (height - 8) / 2, 0xFFFFFFFF);

        ci.cancel();

    }

}
