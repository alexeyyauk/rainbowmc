package com.alexey.textmod.render;

import com.alexey.textmod.Textmod;
import com.alexey.textmod.render.features.CustomVertexData;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static net.minecraft.client.renderer.RenderPipelines.MATRICES_PROJECTION_SNIPPET;
import static net.minecraft.client.renderer.RenderPipelines.register;

@Environment( EnvType.CLIENT)
public class Pipelines
{

    public static final RenderPipeline.Snippet GUI_RAINBOW_SNIPPET = RenderPipeline.builder( MATRICES_PROJECTION_SNIPPET )
            .withVertexShader( Textmod.id( "core/gui_rainbow" ) )
            .withFragmentShader( Textmod.id( "core/gui_rainbow" ) )
            .withColorTargetState( new ColorTargetState( BlendFunction.TRANSLUCENT ) )
            .withVertexFormat( CustomVertexData.POS_TEX_RAINBOW, VertexFormat.Mode.QUADS )
            .buildSnippet();

    public static final RenderPipeline GUI_RAINBOW = register( RenderPipeline.builder( GUI_RAINBOW_SNIPPET )
            .withLocation( "pipeline/textmod_gui" )
            .build() );

}
