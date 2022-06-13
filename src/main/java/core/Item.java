package core;

import graphics.Model;
import graphics.Shader;
import graphics.ShaderProgram;
import graphics.Texture;
import math.Transform;

import java.util.List;

import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;

public class Item {

    private final List<Shader> shaders;
    private final ShaderProgram shaderProgram = new ShaderProgram();
    private String name;
    private final Model model;
    private Transform transform;

    public Item(String name, Model model, List<Shader> shaders, Texture texture) {
        this.name = name;
        this.model = model;
        this.shaders = shaders;
        this.transform = new Transform();
        if (texture != null) {
            model.setTexture(texture);
        }
    }

    public Item(String name, Model model, List<Shader> shaders) {
        this(name, model, shaders, null);
    }

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

    public Model getModel() {
        return model;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public List<Shader> getShaders() {
        return shaders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transform getTransform() {
        return transform;
    }

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


    public void destroy() {
        model.destroy();
        shaderProgram.destroy();
    }
}