package graphics.generators;

import core.TerrainRenderer;
import graphics.*;
import math.Transform;
import math.Vector2f;
import math.Vector3f;

import java.util.List;

import static enums.Constants.TERRAIN_SIZE;
import static enums.Constants.TERRAIN_VERTEX_COUNT;


public class Terrain {
    private Transform transform;
    private Model model;

    private final List<Shader> shaders;

    public Terrain(Vector3f position, Material material, List<Shader> shaders) {
        this.transform = new Transform().setPosition(position);
        this.model = new Model(generateTerrain());
        this.model.setMaterial(material);
        this.shaders = shaders;
    }

    public void init(){
        shaders.forEach(Shader::compile);
        for (Shader shader : shaders){
            TerrainRenderer.getShaderProgram().attachShader(shader);
        }
    }

    private Mesh generateTerrain() {
        int count = TERRAIN_VERTEX_COUNT * TERRAIN_VERTEX_COUNT;
        int[] indices = new int[6 * (TERRAIN_VERTEX_COUNT - 1) * (TERRAIN_VERTEX_COUNT - 1)];
        Vertex[] vertices = new Vertex[count];
        Vector3f[] normals = new Vector3f[count];
        Vector2f[] textureCoords = new Vector2f[count];
        int vp = 0;

        for (int i = 0; i < TERRAIN_VERTEX_COUNT; i++) {
            for (int j = 0; j < TERRAIN_VERTEX_COUNT; j++) {
                vertices[vp] = new Vertex(new Vector3f(j / (TERRAIN_VERTEX_COUNT - 1.0f) * TERRAIN_SIZE,
                        0.0f,
                        i / (TERRAIN_VERTEX_COUNT - 1.0f) * TERRAIN_SIZE));
                normals[vp] = new Vector3f(0, 1, 0);
                textureCoords[vp] = new Vector2f(j / (TERRAIN_VERTEX_COUNT - 1.0f) * TERRAIN_SIZE,
                        i / (TERRAIN_VERTEX_COUNT - 1.0f) * TERRAIN_SIZE);
                vp++;
            }
        }

        int pointer = 0;
        for (int z = 0; z < TERRAIN_VERTEX_COUNT - 1.0f; z++) {
            for (int x = 0; x < TERRAIN_VERTEX_COUNT - 1.0f; x++) {
                int topLeft = (z * TERRAIN_VERTEX_COUNT) + x;
                int topRight = topLeft + 1;
                int bottomLeft = ((z + 1) * TERRAIN_VERTEX_COUNT) + x;
                int bottomRight = bottomLeft + 1;

                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }
        return new Mesh(vertices, textureCoords, normals, indices);
    }


    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
