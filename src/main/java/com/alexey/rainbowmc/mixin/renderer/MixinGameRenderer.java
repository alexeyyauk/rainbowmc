package com.alexey.rainbowmc.mixin.renderer;

import com.alexey.rainbowmc.render.features.GlobalRainbowUniform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.GameRenderer;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment( EnvType.CLIENT)
@Mixin( GameRenderer.class )
public class MixinGameRenderer
{

    @Unique
    private final GlobalRainbowUniform rainbowGlintUniform = new GlobalRainbowUniform();

    @Inject( method = "close",
            at = @At( "TAIL" ) )
    private void closeGlintBuffer( CallbackInfo ci )
    {

    }

    @Inject( method = "render",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/GlobalSettingsUniform;" +
                            "update(IIDJLnet/minecraft/client/DeltaTracker;ILnet/minecraft/world/phys/Vec3;Z)V" ) )
    private void updateGlintRainbowBuffer(
            DeltaTracker deltaTracker,
            boolean advanceGameTime,
            CallbackInfo ci
                                         )
    {
        rainbowGlintUniform.update(
                45f,
                .42f,
                (float) GLFW.glfwGetTime(),
                .5f,
                true,
                1f,
                1f,
                1f
                                  );
    }

}
