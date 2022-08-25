//type vertex
#version 450
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;
layout (location=2) in vec2 aTexCords;

out vec2 fTexCords;
out vec4 fColor;

uniform mat4 tMat;
uniform mat4 vMat;
uniform mat4 pMat;

void main(){
    fColor = aColor;
    fTexCords = aTexCords;
    gl_Position = pMat * vMat * tMat * vec4(aPos, 1.0f);
}