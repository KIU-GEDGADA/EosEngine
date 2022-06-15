package core;

import graphics.Model;
import graphics.Shader;
import graphics.ShaderProgram;
import graphics.Texture;
import math.Transform;

import java.util.List;

import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;

/**
 * This class handles each individual element in the engine
 */
public class Item {

    private final List<Shader> shaders;
    private final ShaderProgram shaderProgram = new ShaderProgram();
    private String name;
    private final Model model;
    private Transform transform;

    /**
     * Class constructor, creates an item to the given parameters
     * @param name name of the item
     * @param model model object of the item
     * @param shaders shaders to be used on the item
     * @param texture texture to be used on the item
     */
    public Item(String name, Model model, List<Shader> shaders, Texture texture) {
        this.name = name;
        this.model = model;
        this.shaders = shaders;
        this.transform = new Transform();
        if (texture != null) {
            model.setTexture(texture);
        }
    }

    /**
     * Class constructor, creates an item without a texture
     * @param name name of the item
     * @param model model object of the item
     * @param shaders shaders to be used on the item
     */
    public Item(String name, Model model, List<Shader> shaders) {
        this(name, model, shaders, null);
    }

    /**
     * This function initializes the item by initializing the model and shaders
     */
    public void init() {
        model.init();
        shaderProgram.init();
        compileShaders();
        shaders.forEach(shaderProgram::attachShader);
        if (shaderProgram.getAttachedShaders().size() > 0) {
            shaderProgram.link();
            shaderProgram.addUniform("useTexture");
            if (model.getMesh().isUsingTexture()) {
                shaderProgram.addUniform("texSampler");
            }
            shaderProgram.addUniform("tMat");
            shaderProgram.addUniform("vMat");
            shaderProgram.addUniform("pMat");

        }
    }

    private void useTexture() {
        shaderProgram.setUniform("useTexture", true);
        shaderProgram.setTexture("texSampler", 0);
        glActiveTexture(GL_TEXTURE0);
    }

    private void useColor() {
        shaderProgram.setUniform("useTexture", false);
    }

    /**
     * This function binds the shaders and renders the item
     */
    public void render() {
        shaderProgram.bind();
        shaderProgram.setUniform("tMat", transform.getTransformationMatrix());
        shaderProgram.setUniform("pMat", transform.getProjectionMatrix());
        shaderProgram.setUniform("vMat", transform.getViewMatrix());
        if (model.getMesh().isUsingTexture()) {
            useTexture();
            model.getTexture().bind();
        } else {
            useColor();
        }
        model.getMesh().render();
        if (model.getMesh().isUsingTexture()) {
            model.getTexture().unbind();
        }
        shaderProgram.unbind();
    }

    /**
     * Getter, this function returns the model of the item
     * @return the model object of the item
     */
    public Model getModel() {
        return model;
    }

    /**
     * Getter, this function returns the shaderProgram of the item
     * @return the shaderProgram object of the ite,
     */
    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    /**
     * Getter, this function returns all shaders of the item
     * @return list of shaders of the item
     */
    public List<Shader> getShaders() {
        return shaders;
    }

    /**
     * Getter, this function returns the name of the item
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Setter, this function sets the name of the item
     * @param name the desired name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter, this function returns the transform object of the item
     * @return the transform object of the item
     */
    public Transform getTransform() {
        return transform;
    }

    /**
     * Setter, this function sets the transofmr object of the item
     * @param transform the desired transofmr object of the item
     */
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    private void compileShaders() {
        shaders.forEach(Shader::compile);
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
}