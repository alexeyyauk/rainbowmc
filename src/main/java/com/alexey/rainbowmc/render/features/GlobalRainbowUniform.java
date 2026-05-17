package com.alexey.rainbowmc.render.features;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.buffers.Std140SizeCalculator;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;

@Environment( EnvType.CLIENT)
public class GlobalRainbowUniform implements AutoCloseable
{

    public static final int UBO_SIZE = new Std140SizeCalculator().putFloat()
            .putFloat()
            .putFloat()
            .putFloat()
            .putInt()
            .putFloat()
            .putFloat()
            .putFloat()
            .get();
    public static final GpuBuffer RAINBOW_BUFFER = RenderSystem.getDevice()
            .createBuffer(
                    () -> "Rainbow Settings UBO",
                    136,
                    UBO_SIZE
                         );

    public void update(
            float angle,
            float freq,
            float time,
            float speed,
            boolean smooth,
            float saturation,
            float brightness,
            float alpha
                      )
    {
        try ( MemoryStack stack = MemoryStack.stackPush() )
        {
            ByteBuffer data = Std140Builder.onStack(
                            stack,
                            UBO_SIZE
                                                   )
                    .putFloat( angle )
                    .putFloat( freq )
                    .putFloat( time )
                    .putFloat( speed )
                    .putInt( smooth ? 1 : 0 )
                    .putFloat( saturation )
                    .putFloat( brightness )
                    .putFloat( alpha )
                    .get();
            RenderSystem.getDevice()
                    .createCommandEncoder()
                    .writeToBuffer(
                            RAINBOW_BUFFER.slice(),
                            data
                                  );
        }
    }

    public void close()
    {
        RAINBOW_BUFFER.close();
    }

}
