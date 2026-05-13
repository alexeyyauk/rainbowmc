package com.alexey.textmod.mixin.vertex;

import com.alexey.textmod.imixin.IVertexConsumer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin( VertexConsumer.class )
public interface MixinVertexConsumer extends IVertexConsumer
{

    @Override
    default IVertexConsumer skeet$setRainbow(
            float angle,
            float freq,
            float speed,
            boolean smooth,
            float sat,
            float bright,
            float alpha
                                            )
    {
        return this;
    }

}
