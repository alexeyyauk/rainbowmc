package com.alexey.textmod.render.features;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static com.mojang.blaze3d.vertex.VertexFormatElement.register;
@Environment( EnvType.CLIENT)
public class CustomVertexData
{

    public static final VertexFormatElement RAINBOW_COLOR_DATA = register( 7, 0, VertexFormatElement.Type.FLOAT, false, 3 );
    public static final VertexFormatElement RAINBOW_POSITION_DATA = register( 8, 0, VertexFormatElement.Type.FLOAT, false, 4 );
    public static final VertexFormatElement SMOOTH = register( 9, 0, VertexFormatElement.Type.INT, false, 1 );

    public static final VertexFormat POS_TEX_RAINBOW = VertexFormat.builder()
            .add( "Position", VertexFormatElement.POSITION )
            .add( "UV0", VertexFormatElement.UV0 )
            .add( "Position_Data", RAINBOW_POSITION_DATA )
            .add( "Smooth", SMOOTH )
            .add( "Color_Data", RAINBOW_COLOR_DATA )
            .build();

}
