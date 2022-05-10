package core;

import graphics.Mesh;
import graphics.Shader;
import graphics.ShaderProgram;
import math.Transform;

import java.util.ArrayList;
import java.util.List;

public class Item implements Entity {

    private List<Shader> shaders;
    private ShaderProgram shaderProgram = new ShaderProgram();
    private String name;
    private String description;
    private Mesh mesh;
    private Transform transform;

    public Item(String name, Mesh mesh, List<Shader> shaders) {
        this.name = name;
        this.description = "None.";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
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

    @Override
    public void update() {

    }

    @Override
    public void render() {
        shaderProgram.bind();
        mesh.render();
        shaderProgram.unbind();
    }

    @Override
    public void destroy() {
        mesh.destroy();
    }
}
