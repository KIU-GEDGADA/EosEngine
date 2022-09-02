//type vertex
#version 450
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;
layout (location=2) in vec2 aTexCords;
layout (location=3) in vec3 normal;

out vec2 fTexCords;
out vec4 fColor;
out vec3 fNormal;
out vec3 fPos;

uniform mat4 tMat;
uniform mat4 vMat;
uniform mat4 pMat;

void main(){

    vec4 worldPos = tMat * vec4(aPos, 1.0f);
    gl_Position = pMat * vMat * worldPos;


    fNormal = normalize(worldPos).xyz;
    fPos = worldPos.xyz;
    fColor = aColor;
    fTexCords = aTexCords;
}