package com.alexey.rainbowmc.mixin.renderer;

import com.alexey.rainbowmc.render.RenderTypes;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( ShieldSpecialRenderer.class )
public class MixinShieldSpecialRenderer
{

    @Redirect( method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lcom/mojang/blaze3d/vertex/PoseStack;" +
            "Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;" +
                            "entityGlint()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private RenderType redirectEntityGlintRenderer0()
    {
        return RenderTypes.getEntityGlint();
    }

}
