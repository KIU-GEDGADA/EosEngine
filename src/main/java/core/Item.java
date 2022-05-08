package core;

import graphics.Mesh;
import math.Transform;

public class Item implements Entity {

    private String name;
    private String description;
    private Mesh mesh;
    private Transform transform;

    public Item(String name, String description, Mesh mesh) {
        this.name = name;
        this.description = description;
        this.mesh = mesh;
        this.transform = new Transform();
    }

    public Item(String name, Mesh mesh) {
        this(name, "None.", mesh);
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
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        mesh.render();
    }

    @Override
    public void destroy() {
        mesh.destroy();
    }
}
