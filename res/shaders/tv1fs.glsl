//type fragment
#version 450

uniform sampler2D texSampler;
uniform bool useTexture;

in vec2 fTexCords;
in vec4 fColor;

out vec4 fragColor;

void main()
{
    fragColor = useTexture ? texture(texSampler, fTexCords) : fColor;
}