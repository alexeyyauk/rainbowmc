package com.alexey.rainbowmc.render;

import com.alexey.rainbowmc.RainbowMc;
import com.alexey.rainbowmc.render.features.CustomVertexData;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.CompareOp;
import com.mojang.blaze3d.shaders.UniformType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static net.minecraft.client.renderer.RenderPipelines.*;

@Environment( EnvType.CLIENT)
public class Pipelines
{

    public static final RenderPipeline.Snippet GUI_RAINBOW_SNIPPET = RenderPipeline.builder( MATRICES_PROJECTION_SNIPPET )
            .withVertexShader( RainbowMc.id( "core/gui_rainbow" ) )
            .withFragmentShader( RainbowMc.id( "core/gui_rainbow" ) )
            .withColorTargetState( new ColorTargetState( BlendFunction.TRANSLUCENT ) )
            .withVertexFormat(
                    CustomVertexData.POS_TEX_RAINBOW,
                    VertexFormat.Mode.QUADS
                             )
            .buildSnippet();

    public static final RenderPipeline GUI_RAINBOW = register( RenderPipeline.builder( GUI_RAINBOW_SNIPPET )
                                                                       .withLocation( "pipeline/rainbowmc_gui" )
                                                                       .build() );
    public static final RenderPipeline GLINT = register( RenderPipeline.builder(
                    MATRICES_PROJECTION_SNIPPET,
                    FOG_SNIPPET,
                    GLOBALS_SNIPPET
                                                                               )
                                                                 .withLocation( "pipeline/rainbow_glint" )
                                                                 .withVertexShader( RainbowMc.id( "core/glint" ) )
                                                                 .withFragmentShader( RainbowMc.id( "core/glint" ) )
                                                                 .withSampler( "Sampler0" )
                                                                 .withUniform( "RainbowConfig", UniformType.UNIFORM_BUFFER )
                                                                 .withCull( false )
                                                                 .withColorTargetState( new ColorTargetState( BlendFunction.TRANSLUCENT ) )
                                                                 .withVertexFormat(
                                                                         DefaultVertexFormat.POSITION_TEX,
                                                                         VertexFormat.Mode.QUADS
                                                                                  )
                                                                 .withDepthStencilState( new DepthStencilState(
                                                                         CompareOp.EQUAL,
                                                                         false
                                                                 ) )
                                                                 .build() );

}
