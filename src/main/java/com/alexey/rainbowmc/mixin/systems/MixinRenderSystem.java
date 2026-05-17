package com.alexey.rainbowmc.mixin.systems;

import com.alexey.rainbowmc.render.features.GlobalRainbowUniform;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( RenderSystem.class )
public class MixinRenderSystem
{

    @Inject( method = "bindDefaultUniforms",
            at = @At( value = "HEAD" ) )
    private static void bindRainbowGlintBuffer(
            RenderPass renderPass,
            CallbackInfo ci
                                              )
    {
        final var glintBuffer = GlobalRainbowUniform.RAINBOW_BUFFER;
        if ( glintBuffer != null ) renderPass.setUniform(
                "RainbowConfig",
                glintBuffer
                                                        );
    }

}
