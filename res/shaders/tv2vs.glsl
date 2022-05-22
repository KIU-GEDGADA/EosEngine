//type vertex
#version 330
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;
layout (location=2) in vec2 aTexCords;

out vec2 fTexCords;

uniform mat4 transformationMatrix;

void main(){
    fTexCords = aTexCords;
    gl_Position = transformationMatrix * vec4(aPos, 1.0f);
}