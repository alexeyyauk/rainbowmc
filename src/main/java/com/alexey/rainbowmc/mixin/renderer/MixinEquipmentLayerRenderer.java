package com.alexey.rainbowmc.mixin.renderer;

import com.alexey.rainbowmc.render.RenderTypes;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( EquipmentLayerRenderer.class )
public class MixinEquipmentLayerRenderer
{

    @Redirect( method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;" +
            "Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;" +
            "Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;" +
            "Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
            at = @At( value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;" +
                            "armorEntityGlint()Lnet/minecraft/client/renderer/rendertype/RenderType;" ) )
    private RenderType redirectArmorGlintRenderer()
    {
        return RenderTypes.getArmorEntityGlint();
    }

}
