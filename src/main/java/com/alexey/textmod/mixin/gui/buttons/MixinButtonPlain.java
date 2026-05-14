package com.alexey.textmod.mixin.gui.buttons;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor; // Импортируйте ваш интерфейс
import com.alexey.textmod.render.GuiRender;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.alexey.textmod.utils.IMinecraftInstance.mc;

@Mixin(Button.Plain.class)
public class MixinButtonPlain {

    @Inject(method = "extractContents", at = @At("HEAD"), cancellable = true)
    public void customButtonsRender(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci){

        Button button = (Button) (Object) this;

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