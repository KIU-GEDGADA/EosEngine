package core;

import graphics.Mesh;
import graphics.Shader;
import graphics.ShaderProgram;
import math.Transform;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private final List<Shader> shaders;

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    private final ShaderProgram shaderProgram = new ShaderProgram();
    private String name;
    private Mesh mesh;
    private Transform transform;

    public Item(String name, Mesh mesh, List<Shader> shaders) {
        this.name = name;
        this.mesh = mesh;
        this.shaders = shaders;
        this.transform = new Transform();
    }

    public Item(String name, Mesh mesh) {
        this(name, mesh, new ArrayList<>());
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
        shaders.forEach(shaderProgram::attachShader);
        if (shaderProgram.getAttachedShaders().size() > 0) {
            shaderProgram.link();
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
        mesh.render();
        shaderProgram.unbind();
    }

    public void destroy() {
        mesh.destroy();
    }
}
