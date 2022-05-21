//type vertex
#version 330
layout (location=0) in vec3 aPos;
layout (location=1) in vec4 aColor;

out vec4 fColor;

uniform mat4 transformationMatrix;



void main() {
    fColor = aColor;
    gl_Position = transformationMatrix * vec4(aPos,  1.0f);
}