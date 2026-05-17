package com.alexey.rainbowmc.mixin.renderer;

import com.alexey.rainbowmc.render.RenderTypes;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( RenderBuffers.class )
public class MixinRenderBuffers
{

    @Redirect( method = "lambda$new$0",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;" +
                            "armorEntityGlint()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private RenderType redirectArmorGlintBuffer()
    {
        return RenderTypes.getArmorEntityGlint();
    }

    @Redirect( method = "lambda$new$0",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;" +
                            "glint()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private RenderType redirectGlintBuffer()
    {
        return RenderTypes.getGLINT();
    }

    @Redirect( method = "lambda$new$0",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;" +
                            "glintTranslucent()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private RenderType redirectGlintTranslucentBuffer()
    {
        return RenderTypes.getGlintTranslucent();
    }

    @Redirect( method = "lambda$new$0",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;" +
                            "entityGlint()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private RenderType redirectEntityGlintBuffer()
    {
        return RenderTypes.getEntityGlint();
    }

}
