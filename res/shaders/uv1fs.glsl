//type fragment
#version 330

out vec4 fragColor;

uniform float alpha;

void main()
{
    fragColor = vec4(0.0, 1.0, 0.5, alpha);
}