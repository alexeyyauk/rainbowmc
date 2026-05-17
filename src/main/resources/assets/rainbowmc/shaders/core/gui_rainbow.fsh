#version 330

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <rainbowmc:shader_effect.glsl>

in vec2 uv;
in vec4 v_position;
flat in int v_smooth;
in vec3 v_color;

out vec4 fragColor;

void main()
{

    if ( v_color.z == 0. ) discard;

    float angle = v_position.x;
    float freq = v_position.y;
    float time = v_position.w;
    float speed = v_position.z;

    vec4 color = getRainbowColor( uv, angle, freq, time, speed, v_smooth, v_color.x, v_color.y, v_color.z, 1f );

    fragColor = color * ColorModulator;
}