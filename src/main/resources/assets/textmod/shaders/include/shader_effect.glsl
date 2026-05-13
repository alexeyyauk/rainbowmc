#version 330

const float PI = 3.1415926535;

vec3 rainbow(float level)
{
    if (level < 1.0) return vec3(1.0, 0.0, 0.0);
    if (level < 2.0) return vec3(1.0, 0.5, 0.0);
    if (level < 3.0) return vec3(1.0, 1.0, 0.0);
    if (level < 4.0) return vec3(0.0, 1.0, 0.0);
    if (level < 5.0) return vec3(0.0, 1.0, 1.0);
    if (level < 6.0) return vec3(0.0, 0.0, 1.0);
    return vec3(0.5, 0.0, 0.5);
}

vec3 smoothRainbow(float x)
{
    float r, g, b;

    if (x < 0.142857) {
        r = 1.0;
        g = mix(0.0, 0.5, x * 7.0);
        b = 0.0;
    }
    else if (x < 0.285714) {
        r = 1.0;
        g = mix(0.5, 1.0, (x - 0.142857) * 7.0);
        b = 0.0;
    }
    else if (x < 0.428571) {
        r = mix(1.0, 0.0, (x - 0.285714) * 7.0);
        g = 1.0;
        b = 0.0;
    }
    else if (x < 0.571429) {
        r = 0.0;
        g = 1.0;
        b = mix(0.0, 1.0, (x - 0.428571) * 7.0);
    }
    else if (x < 0.714286) {
        r = 0.0;
        g = mix(1.0, 0.0, (x - 0.571429) * 7.0);
        b = 1.0;
    }
    else if (x < 0.857143) {
        r = mix(0.0, 0.5, (x - 0.714286) * 7.0);
        g = 0.0;
        b = 1.0;
    }
    else {
        r = mix(0.5, 1.0, (x - 0.857143) * 7.0);
        g = 0.0;
        b = mix(1.0, 0.0, (x - 0.857143) * 7.0);
    }

    return vec3(r, g, b);
}

vec4 getRainbowColor(   vec2 uv, float angle, float frequency, float time, float speed, int useSmooth,
                        float saturation, float brightness, float alpha, float aspect
                    )
{
    float radAngle = angle * PI / 180.0;

    vec2 adjustedUV = vec2(uv.x * aspect, uv.y);

    float value = (adjustedUV.x * cos(radAngle) - adjustedUV.y * sin(radAngle)) * frequency + time * speed;
    float position = fract(value);

    vec3 rainbowColor = rainbow(floor(position * 7.0));

    if (useSmooth == 1) rainbowColor = smoothRainbow(position);

    vec3 gray = vec3(dot(rainbowColor, vec3(0.299, 0.587, 0.114)));
    rainbowColor = mix(gray, rainbowColor, saturation);
    rainbowColor *= brightness;

    return vec4(rainbowColor, alpha);
}
