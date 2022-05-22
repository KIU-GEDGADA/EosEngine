package utils;

import graphics.Vertex;
import math.Matrix4x4;
import math.Vector2f;
import math.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static enums.Constants.*;

public class DataBufferUtils {

    private DataBufferUtils() {

    }

    public static IntBuffer flipIndices(int[] indices) {
        IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();
        return indexBuffer;
    }

    public static FloatBuffer[] flipVertices(Vertex[] vertices) {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length * VERTEX_SIZE);
        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(vertices.length * COLOR_SIZE);
        for (Vertex vertex : vertices) {
            vertexBuffer.put(vertex.position.coordinateArray());
            colorBuffer.put(vertex.color.toArray());
        }
        vertexBuffer.flip();
        colorBuffer.flip();
        return new FloatBuffer[]{vertexBuffer, colorBuffer};
    }

    public static FloatBuffer flipCoordinates(Vector2f[] coordinates) {
        FloatBuffer coordinateBuffer = BufferUtils.createFloatBuffer(coordinates.length * COORDINATE_SIZE);
        for (Vector2f coordinate : coordinates) {
            coordinateBuffer.put(coordinate.coordinateArray());
        }
        coordinateBuffer.flip();
        return coordinateBuffer;
    }

    public static FloatBuffer flipNormals(Vector3f[] normals) {
        FloatBuffer normalBuffer = BufferUtils.createFloatBuffer(normals.length * NORMAL_SIZE);
        for (Vector3f normal : normals) {
            normalBuffer.put(normal.coordinateArray());
        }
        normalBuffer.flip();
        return normalBuffer;
    }
    public static FloatBuffer flipMatrix(Matrix4x4 matrix){
        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        matrixBuffer.put(MathUtils.flatten2DArray(matrix));
        matrixBuffer.flip();
        return matrixBuffer;
    }
}
