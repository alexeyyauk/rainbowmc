package com.alexey.textmod.mixin.vertex;

import com.alexey.textmod.imixin.IVertexConsumer;
import com.alexey.textmod.render.features.CustomVertexData;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin( BufferBuilder.class )
public abstract class MixinBufferBuilder implements IVertexConsumer
{

    @Shadow
    protected abstract long beginElement( VertexFormatElement element );

    @Override
    public IVertexConsumer skeet$setRainbow(
            float angle,
            float freq,
            float speed,
            boolean smooth,
            float sat,
            float bright,
            float alpha
                                           )
    {
        final long pos_data = this.beginElement( CustomVertexData.RAINBOW_POSITION_DATA );
        if ( pos_data != -1L )
        {
            MemoryUtil.memPutFloat( pos_data, angle );
            MemoryUtil.memPutFloat( pos_data + 4L, freq );
            MemoryUtil.memPutFloat( pos_data + 8L, speed );
            MemoryUtil.memPutFloat( pos_data + 12L, (float) GLFW.glfwGetTime() );
        }
        long ptr = this.beginElement( CustomVertexData.SMOOTH );
        if ( ptr != -1L ) MemoryUtil.memPutInt( ptr, smooth ? 1 : 0 );
        final long col_data = this.beginElement( CustomVertexData.RAINBOW_COLOR_DATA );
        if ( col_data != -1L )
        {
            MemoryUtil.memPutFloat( col_data, sat );
            MemoryUtil.memPutFloat( col_data + 4L, bright );
            MemoryUtil.memPutFloat( col_data + 8L, alpha );
        }
        return this;
    }

}
