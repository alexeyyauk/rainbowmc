package com.alexey.rainbowmc.render;

import com.alexey.rainbowmc.imixin.IVertexConsumer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.renderer.state.gui.GuiElementRenderState;
import org.joml.Matrix3x2fc;
import org.jspecify.annotations.Nullable;

@Environment( EnvType.CLIENT)
public record RainbowRect(
        RenderPipeline pipeline,
        TextureSetup textureSetup,
        Matrix3x2fc pose,
        int x0,
        int y0,
        int x1,
        int y1,
        float u0,
        float v0,
        float u1,
        float v1,
        float angle,
        float freq,
        float speed,
        boolean smooth,
        float sat,
        float bright,
        float alpha,
        @Nullable ScreenRectangle scissorArea,
        @Nullable ScreenRectangle bounds
) implements GuiElementRenderState
{
    public RainbowRect(
            final RenderPipeline pipeline,
            final TextureSetup textureSetup,
            final Matrix3x2fc pose,
            final int x0,
            final int y0,
            final int x1,
            final int y1,
            final float u0,
            final float v0,
            final float u1,
            final float v1,
            float angle,
            float freq,
            float speed,
            boolean smooth,
            float sat,
            float bright,
            float alpha,
            @Nullable final ScreenRectangle scissorArea
                      )
    {
        this(
                pipeline,
                textureSetup,
                pose,
                x0,
                y0,
                x1,
                y1,
                u0,
                v0,
                u1,
                v1,
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha,
                scissorArea,
                getBounds(
                        x0,
                        y0,
                        x1,
                        y1,
                        pose,
                        scissorArea
                         )
            );
    }

    @Override
    public void buildVertices( final VertexConsumer vertexConsumer )
    {
        ( (IVertexConsumer) vertexConsumer.addVertexWith2DPose(
                        this.pose(),
                        this.x0(),
                        this.y0()
                                                              )
                .setUv(
                        u0,
                        v0
                      ) ).rainbowmc$setRainbow(
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                              );
        ( (IVertexConsumer) vertexConsumer.addVertexWith2DPose(
                        this.pose(),
                        this.x0(),
                        this.y1()
                                                              )
                .setUv(
                        u0,
                        v1
                      ) ).rainbowmc$setRainbow(
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                              );
        ( (IVertexConsumer) vertexConsumer.addVertexWith2DPose(
                        this.pose(),
                        this.x1(),
                        this.y1()
                                                              )
                .setUv(
                        u1,
                        v1
                      ) ).rainbowmc$setRainbow(
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                              );
        ( (IVertexConsumer) vertexConsumer.addVertexWith2DPose(
                        this.pose(),
                        this.x1(),
                        this.y0()
                                                              )
                .setUv(
                        u1,
                        v0
                      ) ).rainbowmc$setRainbow(
                angle,
                freq,
                speed,
                smooth,
                sat,
                bright,
                alpha
                                              );
    }

    @Nullable
    private static ScreenRectangle getBounds(
            final int x0,
            final int y0,
            final int x1,
            final int y1,
            final Matrix3x2fc pose,
            @Nullable final ScreenRectangle scissorArea
                                            )
    {
        ScreenRectangle bounds = new ScreenRectangle(
                x0,
                y0,
                x1 - x0,
                y1 - y0
        ).transformMaxBounds( pose );
        return scissorArea != null ? scissorArea.intersection( bounds ) : bounds;
    }

}