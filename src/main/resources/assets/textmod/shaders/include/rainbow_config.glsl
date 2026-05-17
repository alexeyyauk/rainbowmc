#version 330

layout(std140) uniform RainbowConfig
{
    float Angle;
    float Freq;
    float Time;
    float Speed;
    int Smooth;
    float Saturation;
    float Brightness;
    float Alpha;
};
