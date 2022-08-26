package utils.loaders;

import graphics.Face;
import graphics.Mesh;
import graphics.Vertex;
import graphics.IndexGroup;
import math.Vector2f;
import math.Vector3f;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the loading of objects into the EosEngine
 */
public class OBJLoader {
    /**
     * This function loads a mesh from a filepath
     *
     * @param path the path of the object to load
     * @return the Mesh of the desired object
     */
    public static Mesh loadModel(String path) {
        List<Vertex> vertexList = new ArrayList<>();
        List<Vector2f> textureCoordinatesList = new ArrayList<>();
        List<Vector3f> normalsList = new ArrayList<>();
        List<Face> facesList = new ArrayList<>();

        String file = FileUtils.readFile(path);
        String[] lines = file.split("\n");
        for (String line : lines) {
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case "v":
                    Vector3f vertex = new Vector3f(
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]),
                            Float.parseFloat(tokens[3]));
                    vertexList.add(new Vertex(vertex));
                    break;
                case "vt":
                    Vector2f textureCoordinates = new Vector2f(
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]));
                    textureCoordinatesList.add(textureCoordinates);
                    break;
                case "vn":
                    Vector3f normal = new Vector3f(
                            Float.parseFloat(tokens[1]),
                            Float.parseFloat(tokens[2]),
                            Float.parseFloat(tokens[3]));
                    normalsList.add(normal);
                    break;
                case "f":
                    Face face = new Face(tokens[1], tokens[2], tokens[3]);
                    facesList.add(face);
                    break;
                default:
                    break;
            }
        }
        return reorderLists(vertexList, textureCoordinatesList, normalsList, facesList);
    }

    private static Mesh reorderLists(List<Vertex> vertexList, List<Vector2f> textureCoordinatesList, List<Vector3f> normalsList, List<Face> facesList) {
        List<Vertex> vertices = new ArrayList<>();
        List<Vector2f> textureCoordinates = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (Face face : facesList) {
            for (IndexGroup idxGroup : face.getFaceVertexIndices()) {
                vertices.add(vertexList.get(idxGroup.vertexIndex));
                textureCoordinates.add(textureCoordinatesList.get(idxGroup.textureIndex));
                normals.add(normalsList.get(idxGroup.normalIndex));
                indices.add(indices.size());
            }
        }
        Vertex[] verticesArray = vertices.toArray(new Vertex[0]);
        Vector2f[] textureCoordinatesArray = textureCoordinates.toArray(new Vector2f[0]);
        Vector3f[] normalsArray = normals.toArray(new Vector3f[0]);
        int[] indicesArray = indices.stream().mapToInt(i -> i).toArray();
        return new Mesh(verticesArray, textureCoordinatesArray, normalsArray, indicesArray);
    }
}
