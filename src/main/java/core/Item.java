package core;

import graphics.Mesh;
import graphics.Shader;
import graphics.ShaderProgram;
import graphics.Texture;
import math.Transform;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;

public class Item {

    private final List<Shader> shaders;
    private final ShaderProgram shaderProgram = new ShaderProgram();
    private String name;
    private Mesh mesh;
    private Texture texture;
    private Transform transform;

    public Item(String name, Mesh mesh, List<Shader> shaders, Texture texture) {
        this.name = name;
        this.mesh = mesh;
        this.shaders = shaders;
        this.transform = new Transform();
        this.texture = texture;
    }

    public Item(String name, Mesh mesh, Texture texture) {
        this(name, mesh, new ArrayList<>(), texture);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void init() {
        mesh.init();
        shaderProgram.init();
        compileShaders();
        texture.init();
        shaders.forEach(shaderProgram::attachShader);
        if (shaderProgram.getAttachedShaders().size() > 0) {
            shaderProgram.link();
            shaderProgram.addUniform("tex");
            shaderProgram.setTexture("tex", 0);
            glActiveTexture(GL_TEXTURE0);
        }
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

    public void render() {
        shaderProgram.bind();
        texture.bind();
        mesh.render();
        texture.unbind();
        shaderProgram.unbind();
    }

    public void destroy() {
        mesh.destroy();
    }
}
