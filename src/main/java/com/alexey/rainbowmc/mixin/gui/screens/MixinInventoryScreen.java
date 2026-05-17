package com.alexey.rainbowmc.mixin.gui.screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment( EnvType.CLIENT )
@Mixin( InventoryScreen.class )
public class MixinInventoryScreen extends Screen
{

    protected MixinInventoryScreen( Component title )
    {
        super( title );
    }

    @ModifyArg( method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;)"
            + "Lnet/minecraft/client/renderer/entity/state/EntityRenderState;",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/EntityRenderer;" +
                            "createRenderState(Lnet/minecraft/world/entity/Entity;F)Lnet/minecraft/client/renderer/entity/state/EntityRenderState;" ),
            index = 1 )
    private static float fix20fpsInventoryEntityRenderer( float partialTicks )
    {
        return Minecraft.getInstance()
                .getDeltaTracker()
                .getGameTimeDeltaPartialTick( false );
    }

}
