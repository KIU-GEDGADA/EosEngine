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

struct DirectionalLight {
    vec3 color;
    vec3 direction;
    float intensity;
};

struct PointLight {
    vec3 color;
    vec3 position;
    float intensity;
    float constant;
    float linear;
    float exponent;
};

uniform sampler2D texSampler;
uniform Material material;
uniform bool useTexture;

// Lights
uniform DirectionalLight directionalLight;
uniform vec3 ambientLight;
uniform PointLight pointLight;
// Light Parameters
uniform float specularPower;



vec4 ambientC;
vec4 diffuseC;
vec4 specularC;

void setupColors(Material material, vec2 texCords) {
    if (material.hasTexture == 1) {
        ambientC = texture(texSampler, texCords);
        diffuseC = ambientC;
        specularC = ambientC;
    } else {
        ambientC = material.ambient;
        diffuseC = material.diffuse;
        specularC = material.specular;
    }
}

vec4 calculateLightColor(vec3 lightColor, float lightIntensity, vec3 pos, vec3 toLightDir, vec3 normal) {
    vec4 diffuseColor = vec4(0, 0, 0, 0);
    vec4 specColor = vec4(0, 0, 0, 0);

    //diffuse Light
    float diffuseFac = max(dot(normal, toLightDir), 0.0);
    diffuseColor = diffuseC * vec4(lightColor, 1.0) * lightIntensity * diffuseFac;

    //Specular Light
    vec3 cam_direction = normalize(-pos);
    vec3 fromLightDir = -toLightDir;
    vec3 reflectedLight = normalize(reflect(fromLightDir, normal));
    float specularFactor = max(dot(cam_direction, reflectedLight), 0.0);
    specularFactor = pow(specularFactor, specularPower);
    specColor = specularC * lightIntensity * specularFactor * material.reflectance * vec4(lightColor, 1.0);

    return (diffuseColor + specColor);
}

vec4 calculatePointLight(PointLight light, vec3 position, vec3 normal) {
    vec3 light_dir = light.position - position;
    vec3 to_light_dir = normalize(light_dir);
    vec4 light_color = calculateLightColor(light.color, light.intensity, position, to_light_dir, normal);

    float distance = length(light_dir);
    float attInv = light.constant + (light.linear * distance) + (light.exponent * distance * distance);
    return light_color / attInv;
}


vec4 calculateDirectionalLight(DirectionalLight light, vec3 position, vec3 normal) {
    return calculateLightColor(light.color, light.intensity, position, normalize(light.direction), normal);
}


void main()
{
    setupColors(material, fTexCords);


    vec4 diffuseSpec = calculateDirectionalLight(directionalLight, fPos, fNormal);
    diffuseSpec += calculatePointLight(pointLight, fPos, fNormal);

    fragColor = ambientC * vec4(ambientLight, 1) + diffuseSpec;
}