package core;

import enums.Constants;
import graphics.Model;
import graphics.Shader;
import graphics.ShaderProgram;
import graphics.lighting.DirectionalLight;
import graphics.lighting.PointLight;
import math.Transform;
import math.Vector3f;
import graphics.lighting.ILight;

import java.util.List;

/**
 * This class handles each individual element in the engine
 */
public class Item {

    private final List<Shader> shaders;
    private List<ILight> lights;
    private final ShaderProgram shaderProgram = new ShaderProgram();
    private String name;
    private final Model model;
    private Transform transform;

    /**
     * Class constructor, creates an item to the given parameters
     *
     * @param name    name of the item
     * @param model   model object of the item
     * @param shaders shaders to be used on the item
     */
    public Item(String name, Model model, List<Shader> shaders) {
        this.name = name;
        this.model = model;
        this.shaders = shaders;
        this.transform = new Transform();

    }

    public Item(Vector3f position, String name, Model model, List<Shader> shaders) {
        this.name = name;
        this.model = model;
        this.shaders = shaders;
        this.transform = new Transform().setPosition(position);
    }


    /**
     * This function initializes the item by initializing the model and shaders
     */
    public void init() {
        model.init();
        shaderProgram.init();
        shaders.forEach(Shader::compile);
        shaders.forEach(shaderProgram::attachShader);
        if (shaderProgram.getAttachedShaders().size() > 0) {
            shaderProgram.link();
            shaderProgram.addUniform("useTexture");
            shaderProgram.addUniform("texSampler");
            //General
            shaderProgram.addUniform("tMat");
            shaderProgram.addUniform("vMat");
            shaderProgram.addUniform("pMat");
            shaderProgram.addMaterialUniform("material");
            // Lights
            shaderProgram.addUniform("ambientLight");
            // Light Parameters
            shaderProgram.addUniform("specularPower");
        }
    }

    /**
     * This function binds the shaders and renders the item
     */
    public void render() {
        shaderProgram.bind();
        shaderProgram.setUniform("tMat", transform.getTransformationMatrix());
        shaderProgram.setUniform("pMat", transform.getProjectionMatrix());
        shaderProgram.setUniform("vMat", transform.getViewMatrix());
        shaderProgram.setUniform("ambientLight", Constants.AMBIENT_LIGHT);
        shaderProgram.setUniform("material", getModel().getMaterial());
        shaderProgram.setUniform("specularPower", Constants.SPECULAR_POWER);
        if (model.getMaterial().hasTexture()) {
            shaderProgram.setUniform("useTexture", true);
        } else {
            shaderProgram.setUniform("useTexture", false);
        }
        setLightUniforms();
        if (model.getMaterial().hasTexture()) model.getMaterial().getTexture().bind();
        model.getMesh().render();
        if (model.getMaterial().hasTexture()) model.getMaterial().getTexture().unbind();
        shaderProgram.unbind();
    }

    /**
     * Getter, this function returns the model of the item
     *
     * @return the model object of the item
     */
    public Model getModel() {
        return model;
    }

    /**
     * Getter, this function returns the shaderProgram of the item
     *
     * @return the shaderProgram object of the ite,
     */
    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    /**
     * Getter, this function returns all shaders of the item
     *
     * @return list of shaders of the item
     */
    public List<Shader> getShaders() {
        return shaders;
    }

    /**
     * Getter, this function returns the name of the item
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Setter, this function sets the name of the item
     *
     * @param name the desired name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter, this function returns the transform object of the item
     *
     * @return the transform object of the item
     */
    public Transform getTransform() {
        return transform;
    }

    /**
     * Setter, this function sets the transform object of the item
     *
     * @param transform the desired transform object of the item
     */
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    private void addShader(Shader shader) {
        if (!shaders.contains(shader)) {
            shader.compile();
            shaders.add(shader);
        }
    }

    private void removeShader(Shader shader) {
        shaders.remove(shader);
    }

    /**
     * This function destroys the item, its model and shaderProgram
     */
    public void destroy() {
        model.destroy();
        shaderProgram.destroy();
    }

    public void setLights(List<ILight> lights) {
        this.lights = lights;
        for (ILight light : this.lights) {
            if (light instanceof DirectionalLight) {
                shaderProgram.addDirectionalLightUniform("directionalLight");
            } else if (light instanceof PointLight) {
                shaderProgram.addPointLightUniform("pointLight");
            }
        }
    }

    public void setLightUniforms() {
        for (ILight light : lights) {
            if (light instanceof DirectionalLight) {
                shaderProgram.setUniform("directionalLight", (DirectionalLight) light);
            } else if (light instanceof PointLight) {
                shaderProgram.setUniform("pointLight", (PointLight) light);
            }
        }
    }
}