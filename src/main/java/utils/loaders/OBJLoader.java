package utils.loaders;

import graphics.Mesh;
import graphics.Vertex;
import math.Vector2f;
import math.Vector3f;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class OBJLoader {
    public static Mesh loadModel(String path) {
        List<Vertex> vertexList = new ArrayList<>();
        List<Vector2f> textureCoordinatesList = new ArrayList<>();
        List<Vector3f> normalsList = new ArrayList<>();
        List<Vector3f> facesList = new ArrayList<>();

        String file = FileUtils.readFile(path);
        String[] lines = file.split("\n");
        for (String line : lines) {
            String[] tokens = line.split(" ");
            if (!line.startsWith("#") || !line.startsWith("o") || !line.startsWith("s")) {
                switch (tokens[0]) {
                    case "v":
                        Vertex vertex = new Vertex(new Vector3f(
                                Float.parseFloat(tokens[1]),
                                Float.parseFloat(tokens[2]),
                                Float.parseFloat(tokens[3])));
                        vertexList.add(vertex);
                        break;
                    case "vt":
                        Vector2f textureCoordinate = new Vector2f(
                                Float.parseFloat(tokens[1]),
                                Float.parseFloat(tokens[2]));
                        textureCoordinatesList.add(textureCoordinate);
                        break;
                    case "vn":
                        Vector3f normal = new Vector3f(
                                Float.parseFloat(tokens[1]),
                                Float.parseFloat(tokens[2]),
                                Float.parseFloat(tokens[3]));
                        normalsList.add(normal);
                        break;
                    case "f":
                        processFace(tokens[1], facesList);
                        processFace(tokens[2], facesList);
                        processFace(tokens[3], facesList);
                        break;
                }
            }
        }
        List<Integer> indices = new ArrayList<>();
        Vertex[] vertices = vertexList.toArray(new Vertex[0]);
        Vector2f[] textureCoordinates = textureCoordinatesList.toArray(new Vector2f[vertexList.size()]);
        Vector3f[] normals = normalsList.toArray(new Vector3f[vertexList.size()]);

        for (Vector3f face : facesList) {
            processVertex((int) face.x, (int) face.y, (int) face.z, textureCoordinatesList, normalsList, indices, textureCoordinates, normals);
        }

        int[] indicesArray = indices.stream().mapToInt((Integer i) -> i).toArray();

        return new Mesh(vertices, textureCoordinates, normals, indicesArray);

    }

    private static void processVertex(int pos, int tex, int norm,
                                      List<Vector2f> textures, List<Vector3f> normals, List<Integer> indices,
                                      Vector2f[] textureCoordinates, Vector3f[] normalsArray) {
        indices.add(pos);
        if (tex >= 0) {
            textureCoordinates[pos] = textures.get(tex);
        }
        if (norm >= 0) {
            normalsArray[pos] = normals.get(norm);
        }

    }

    private static void processFace(String token, List<Vector3f> faces) {
        String[] tokens = token.split("/");
        int size = tokens.length;
        int pos = -1, tex = -1, norm = -1;
        pos = Integer.parseInt(tokens[0].strip()) - 1;
        if (size > 1) {
            tex = Integer.parseInt(tokens[1].strip()) - 1;
            if (size > 2) {
                norm = Integer.parseInt(tokens[2].strip()) - 1;
            }
        }
        faces.add(new Vector3f(pos, tex, norm));
    }
}
