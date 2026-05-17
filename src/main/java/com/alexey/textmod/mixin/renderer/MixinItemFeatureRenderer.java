package com.alexey.textmod.mixin.renderer;

import com.alexey.textmod.render.RenderTypes;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( ItemFeatureRenderer.class )
public class MixinItemFeatureRenderer
{

    @Redirect( method = "getFoilRenderType",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;glintTranslucent()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private static RenderType redirectGlintTranslucentRender()
    {
        return RenderTypes.getGlintTranslucent();
    }

    @Redirect( method = "getFoilRenderType",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;glint()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private static RenderType redirectGlintRender()
    {
        return RenderTypes.getGLINT();
    }

    @Redirect( method = "getFoilRenderType",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;entityGlint()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private static RenderType redirectEntityGlintRender()
    {
        return RenderTypes.getEntityGlint();
    }

}
