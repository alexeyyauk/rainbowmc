#version 330

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:globals.glsl>
#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <textmod:rainbow_config.glsl>
#moj_import <textmod:shader_effect.glsl>

uniform sampler2D Sampler0;

in float sphericalVertexDistance;
in float cylindricalVertexDistance;
in vec2 texCoord0;
in vec2 rainbow_uv;

out vec4 fragColor;

void main()
{
    vec4 color = texture( Sampler0, texCoord0 ) * ColorModulator;

    float fade = ( 1.0f - total_fog_value( sphericalVertexDistance, cylindricalVertexDistance, FogEnvironmentalStart,
                                           FogEnvironmentalEnd, FogRenderDistanceStart, FogRenderDistanceEnd ) ) * GlintAlpha;
    float alpha = length( color.rgb ) / sqrt( 3. );
    vec4 rainbow = getRainbowColor( rainbow_uv, Angle, Freq, Time, Speed, Smooth, Saturation, Brightness, fade * alpha, 1. );
    fragColor = rainbow;
}
