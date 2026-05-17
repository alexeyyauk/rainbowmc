#version 330

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

in vec3 Position;
in vec2 UV0;
in vec4 Position_Data;
in int Smooth;
in vec3 Color_Data;

out vec2 uv;
out vec4 v_position;
flat out int v_smooth;
out vec3 v_color;

void main()
{

    gl_Position = ProjMat * ModelViewMat * vec4( Position, 1. );

    uv          = UV0;
    v_position  = Position_Data;
    v_smooth    = Smooth;
    v_color     = Color_Data;

}