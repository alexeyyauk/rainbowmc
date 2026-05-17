#version 330

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

in vec3 Position;
in vec2 UV0;

out float sphericalVertexDistance;
out float cylindricalVertexDistance;
out vec2 texCoord0;
out vec2 rainbow_uv;

void main()
{
    gl_Position = ProjMat * ModelViewMat * vec4( Position, 1. );

    sphericalVertexDistance = fog_spherical_distance( Position );
    cylindricalVertexDistance = fog_cylindrical_distance( Position );
    texCoord0 = ( TextureMat * vec4( UV0, 0., 1. ) ).xy;
    rainbow_uv = texCoord0 - TextureMat[ 3 ].xy;
    rainbow_uv *= 14.88;
}
