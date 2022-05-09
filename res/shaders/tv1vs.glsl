//type vertex
#version 330
layout (location=0) in vec3 aPos;
layout (location=2) in vec2 aTexCords;

out vec2 fTexCords;

void main(){
    gl_Position = vec4(aPos,1.0);
    fTexCords = aTexCords;
}