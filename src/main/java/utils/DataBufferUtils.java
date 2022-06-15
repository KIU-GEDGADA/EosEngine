package utils;

import graphics.Vertex;
import math.Matrix4x4;
import math.Vector2f;
import math.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static enums.Constants.*;

/**
 * This class handles operations on Buffers
 */
public class DataBufferUtils {

    private DataBufferUtils() {

    }

    /**
     * This function creates a flipped IntBuffer from an int array
     * @param indices the int array to be turned into a flipped buffer
     * @return the flipped IntBuffer created from an int array
     */
    public static IntBuffer flipIndices(int[] indices) {
        IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();
        return indexBuffer;
    }

    /**
     * This function creates a flipped FloatBuffer from a Vertex
     * @param vertices the color and positions vertex to be turned into a flipped buffer
     * @return the flipped FloatBuffer created from a vertex
     */
    public static FloatBuffer[] flipVertices(Vertex[] vertices) {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length * VERTEX_SIZE);
        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(vertices.length * COLOR_SIZE);
        for (Vertex vertex : vertices) {
            vertexBuffer.put(vertex.getPosition().coordinateArray());
            colorBuffer.put(vertex.getColor().toArray());
        }
        vertexBuffer.flip();
        colorBuffer.flip();
        return new FloatBuffer[]{vertexBuffer, colorBuffer};
    }

    /**
     * This function creates a flipped FloatBuffer from a 2 float vector
     * @param coordinates the 2 float vector to be turned into a flipped buffer
     * @return the flipped FloatBuffer of the 2 float vector
     */
    public static FloatBuffer flipCoordinates(Vector2f[] coordinates) {
        FloatBuffer coordinateBuffer = BufferUtils.createFloatBuffer(coordinates.length * COORDINATE_SIZE);
        for (Vector2f coordinate : coordinates) {
            coordinateBuffer.put(coordinate.coordinateArray());
        }
        coordinateBuffer.flip();
        return coordinateBuffer;
    }

    /**
     * This function creates a flipped FloatBuffer from a 3 float vector
     * @param normals the 3 float vector to be turned into a flipped buffer
     * @return the flipped FloatBuffer of the 3 float vector
     */
    public static FloatBuffer flipNormals(Vector3f[] normals) {
        FloatBuffer normalBuffer = BufferUtils.createFloatBuffer(normals.length * NORMAL_SIZE);
        for (Vector3f normal : normals) {
            normalBuffer.put(normal.coordinateArray());
        }
        normalBuffer.flip();
        return normalBuffer;
    }

    /**
     * This function creates a flipped FloatBuffer of a Matrix4x4
     * @param matrix the Matrix4x4 to be turned into a flipped buffer
     * @return the flipped FloatBuffer of a Matrix4x4
     */
    public static FloatBuffer flipMatrix(Matrix4x4 matrix){
        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        matrixBuffer.put(MathUtils.flatten2DArray(matrix));
        matrixBuffer.flip();
        return matrixBuffer;
    }
}
