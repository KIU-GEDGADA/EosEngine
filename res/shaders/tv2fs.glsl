//type fragment
#version 450


in vec2 fTexCords;
in vec3 fNormal;
in vec3 fPos;
in vec4 fColor;

out vec4 fragColor;

struct Material {
    vec4 ambient;
    vec4 diffuse;
    vec4 specular;
    int hasTexture;
    float reflectance;
};

uniform sampler2D texSampler;
uniform vec3 ambientLight;
uniform Material material;
uniform bool useTexture;


vec4 ambientC;


void main()
{

    if(material.hasTexture == 1) {
        ambientC = texture(texSampler, fTexCords);
    } else {
        ambientC = material.ambient + material.specular + material.diffuse + material.reflectance;
    }
    fragColor = ambientC * vec4(ambientLight, 1);
}