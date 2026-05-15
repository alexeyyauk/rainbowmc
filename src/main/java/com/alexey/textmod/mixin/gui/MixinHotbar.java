package com.alexey.textmod.mixin.gui;

import com.alexey.textmod.imixin.IGuiGraphicsExtractor;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class MixinHotbar {
    @Shadow protected abstract Player getCameraPlayer();
    @Shadow private int toolHighlightTimer;
    @Shadow private ItemStack lastToolHighlight;
    @Shadow protected abstract void extractSlot(GuiGraphicsExtractor g, int x, int y, DeltaTracker d, Player p, ItemStack s, int id);
    @Shadow public abstract Font getFont();

    @Unique
    private float smoothSelectedSlot = 0.0f;

    @Inject(method = "extractItemHotbar", at = @At("HEAD"), cancellable = true)
    private void textmod$renderCustomHotbar(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Player player = this.getCameraPlayer();
        if (player == null) return;
        
        
        int mid = graphics.guiWidth() / 2;
        int width = 22;
        int x = mid - 91, y = graphics.guiHeight() - width;
        int target = player.getInventory().getSelectedSlot();

        smoothSelectedSlot += (target - smoothSelectedSlot) * 0.43f;
        if (Math.abs(smoothSelectedSlot - target) < 0.001) smoothSelectedSlot = target;

        var rainbow = (IGuiGraphicsExtractor) graphics;


        rainbow.textmod$rainbowRect(x, y, x + 182, y + width, 0f, 0f, 1f, 1f, 0f, 1f, .5f, true, .555f, .6767f, .67f);
        rainbow.textmod$rainbowOutline(x, y, 182, width, 0f, 0f, 1f, 1f, 0f, 1f, .5f, true, .7f, .8f, 1f);

        int selX = (int) (x + (smoothSelectedSlot * 20));
        float u0 = (float) (selX - x) / 182f;
        float u1 = (float) (selX + width - x) / 182f;

        rainbow.textmod$rainbowRect(selX, y, selX + width, y + width, u0, 0f, u1, 1f, 0f, 1f, 1f, true, .8f, .8f, .6f);
        rainbow.textmod$rainbowOutline(selX, y, width, width, u0, 0f, u1, 1f, 0f, 1f, 1f, true, 1f, 1f, 1f);

        for (int i = 0; i < 9; ++i) {
            this.extractSlot(graphics, x + 1 + i * 20 + 2, graphics.guiHeight() - 19, deltaTracker, player, player.getInventory().getItem(i), i);
        }

        ItemStack offhand = player.getOffhandItem();
        if (!offhand.isEmpty()) {
            boolean left = player.getMainArm().getOpposite() == HumanoidArm.LEFT;
            int ox = left ? x - 29 : x + 182;

            rainbow.textmod$rainbowRect(ox, y, ox+width, y+width, u0, 0f, u1, 1f, 0f, 1f, .5f, true, .555f, .6767f, .67f);
            rainbow.textmod$rainbowOutline(ox, y, width, width, u0, 0f, u1, 1f, 0f, 1f, 1f, true, .7f, .8f, 1f);
            this.extractSlot(graphics, left ? x - 26 : x + 192, graphics.guiHeight() - 19, deltaTracker, player, offhand, 10);

        }
        ci.cancel();
    }

    @Inject(method = "extractSelectedItemName", at = @At("HEAD"), cancellable = true)
    private void textmod$rainbowItemName(GuiGraphicsExtractor graphics, CallbackInfo ci) {
        if (this.toolHighlightTimer > 0 && !this.lastToolHighlight.isEmpty()) {

            var text = Component.empty().append(this.lastToolHighlight.getHoverName()).withStyle(this.lastToolHighlight.getRarity().color());

                    int w = getFont().width(text),
                    x = (graphics.guiWidth() - w) / 2,
                    y = graphics.guiHeight() - 54;

            int alpha = Math.min(255, (int) (this.toolHighlightTimer * 25.6f));

            var rainbow = (IGuiGraphicsExtractor) graphics;
                graphics.textWithBackdrop(getFont(), text, x, y, w, ARGB.white(alpha));
                rainbow.textmod$rainbowRect(x, y + 10, x + w, y + 11, 0f, 0f, 1f, 1f, 0f, 0.67f, 0.3f, true, 1f, 1f, alpha / 255f);

        }
        ci.cancel();
    }
}