package finalDemo;

import core.*;
import graphics.*;
import graphics.generators.Terrain;
import graphics.lighting.DirectionalLight;
import graphics.lighting.PointLight;
import io.Input;
import math.Vector2f;
import math.Vector3f;
import utils.MathUtils;
import utils.TimeUtils;

import java.util.List;

import static enums.Constants.MOUSE_SENSITIVITY;
import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements Entity {
    Item item2;

    Terrain terrain;
    Terrain terrain2;
    Texture texture1;
    Texture texture2;
    Texture texture3;

    Camera camera;

    // Lighting
    private float lightAngle;
    DirectionalLight directionalLight;
    PointLight pointLight;
    Vector2f previousPos = new Vector2f(-1, -1);
    Vector2f rotationVec = new Vector2f();

    Vector3f cameraVelocity = Vector3f.zero();

    @Override
    public void init() {
        Shader vs = new Shader("res/shaders/tv2vs.glsl");
        Shader fs = new Shader("res/shaders/tv2fs.glsl");
        List<Shader> shaders = List.of(new Shader[]{vs, fs});

        texture1 = new Texture("res/textures/grass.png");
        texture2 = new Texture("res/textures/terrain.png");
        texture3 = new Texture("res/textures/terrain_2.png");
        camera = Camera.getInstance();

        //Lighting parameters
        lightAngle = -90;

        float lightIntensity = 5.0f;

        Vector3f lightPosition = new Vector3f(0, 0, -3.2f);
        Vector3f lightColor = Vector3f.one();
        pointLight = new PointLight(lightColor, lightPosition, lightIntensity, 0, 0, 1);


        lightPosition = new Vector3f(-1, -10, 0);
        lightColor = Vector3f.one();
        directionalLight = new DirectionalLight(lightColor, lightPosition, lightIntensity);

        item2 = new Item("Cube", new Model(new Mesh("res/models/grass.obj")).setTexture(texture1, 1f), shaders);
        item2.getTransform().getScale().div(4f);

        terrain = new Terrain(new Vector3f(0, -1, -800), new Material(texture2).setReflectance(0.1f), terrainShaders);
        terrain2 = new Terrain(new Vector3f(-800, -1, -800), new Material(texture3).setReflectance(0.1f), terrainShaders);


        Renderer.addItem(item2);
        TerrainRenderer.addTerrain(terrain);
   //     TerrainRenderer.addTerrain(terrain2);
        Renderer.setupLights(directionalLight, pointLight);

    }

    @Override
    public void update() {

        mousePosGetter();

        cameraVelocity = Vector3f.zero();

        if (Input.isKeyDown(GLFW_KEY_W))
            cameraVelocity.z = -1;
        if (Input.isKeyDown(GLFW_KEY_S))
            cameraVelocity.z = 1;
        if (Input.isKeyDown(GLFW_KEY_A))
            cameraVelocity.x = -1;
        if (Input.isKeyDown(GLFW_KEY_D))
            cameraVelocity.x = 1;
        if (Input.isKeyDown(GLFW_KEY_Q))
            cameraVelocity.y = -1;
        if (Input.isKeyDown(GLFW_KEY_E))
            cameraVelocity.y = 1;
        if (Input.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            if (Input.isKeyDown(GLFW_KEY_UP)) {
                item2.getTransform().getRotation().x += 5f;
                MathUtils.clamp(item2.getTransform().getRotation().x, 0, 360);
            } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
                item2.getTransform().getRotation().x -= 5f;
                MathUtils.clamp(item2.getTransform().getRotation().x, 0, 360);
            } else if (Input.isKeyDown(GLFW_KEY_LEFT)) {
                item2.getTransform().getRotation().y += 5f;
                MathUtils.clamp(item2.getTransform().getRotation().y, 0, 360);
            } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
                item2.getTransform().getRotation().y -= 5f;
                MathUtils.clamp(item2.getTransform().getRotation().y, 0, 360);
            }
        } else if (Input.isKeyDown(GLFW_KEY_LEFT_CONTROL)) {
            if (Input.isKeyDown(GLFW_KEY_A)) {
                if (Input.isKeyDown(GLFW_KEY_UP)) {
                    item2.getTransform().getScale().y += 0.05f;
                    item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
                    item2.getTransform().getScale().x += 0.05f;
                    item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
                } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
                    item2.getTransform().getScale().y -= 0.05f;
                    item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
                    item2.getTransform().getScale().x -= 0.05f;
                    item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
                }
            } else {
                if (Input.isKeyDown(GLFW_KEY_UP)) {
                    item2.getTransform().getScale().y += 0.05f;
                    item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
                } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
                    item2.getTransform().getScale().y -= 0.05f;
                    item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
                } else if (Input.isKeyDown(GLFW_KEY_LEFT)) {
                    item2.getTransform().getScale().x += 0.05f;
                    item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
                } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
                    item2.getTransform().getScale().x -= 0.05f;
                    item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
                }
            }
        } else if (Input.isKeyDown(GLFW_KEY_T)) {
            if (Input.isKeyDown(GLFW_KEY_UP)) {
                item2.getTransform().getPosition().y += 0.05f;
                item2.getTransform().getPosition().y = MathUtils.clamp(item2.getTransform().getPosition().y, -1f, 1f);
            } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
                item2.getTransform().getPosition().y -= 0.05f;
                item2.getTransform().getPosition().y = MathUtils.clamp(item2.getTransform().getPosition().y, -1f, 1f);
            } else if (Input.isKeyDown(GLFW_KEY_LEFT)) {
                item2.getTransform().getPosition().x -= 0.05f;
                item2.getTransform().getPosition().x = MathUtils.clamp(item2.getTransform().getPosition().x, -1f, 1f);
            } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
                item2.getTransform().getPosition().x += 0.05f;
                item2.getTransform().getPosition().x = MathUtils.clamp(item2.getTransform().getPosition().x, -1f, 1f);
            }
        }

        camera.movePosition(
                cameraVelocity.x * TimeUtils.getDeltaTime(),
                cameraVelocity.y * TimeUtils.getDeltaTime(),
                cameraVelocity.z * TimeUtils.getDeltaTime()
        );

        lightSetup();
    }

    @Override
    public void render() {
    }

    @Override
    public void destroy() {
        //item1.destroy();
        item2.destroy();
    }

    private void lightSetup() {
        if (Input.isKeyDown(GLFW_KEY_LEFT)) {
            pointLight.getPosition().x += 0.1f;
        }
        if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
            pointLight.getPosition().x -= 0.1f;

        }


        lightAngle += 0.5f;
        if (lightAngle > 90f) {
            directionalLight.setIntensity(0);
            if (lightAngle >= 360) lightAngle = -90;
        } else if (lightAngle <= -80 || lightAngle >= 80) {
            float factor = 1 - (Math.abs(lightAngle) - 80) / 10.0f;
            directionalLight.setIntensity(factor);
            directionalLight.getColor().y = Math.max(factor, 0.9f);
            directionalLight.getColor().z = Math.max(factor, 0.5f);
        } else {
            directionalLight.setIntensity(1f);
            directionalLight.setColor(Vector3f.one());
        }

        double angRad = Math.toRadians(lightAngle);
        directionalLight.getDirection().x = (float) Math.sin(angRad);
        directionalLight.getDirection().y = (float) Math.cos(angRad);

    }

    private void mousePosGetter() {
        //MOUSE POSITIONS

        rotationVec = Vector2f.zero();
        Vector2f currentPos = Input.getMousePos();

        if (previousPos.x > 0 && previousPos.y > 0) {
            float x = currentPos.x - previousPos.x;
            float y = currentPos.y - previousPos.y;
            boolean rotateX = x != 0;
            boolean rotateY = y != 0;
            if (rotateX)
                rotationVec.y = x;
            if (rotateY)
                rotationVec.x = y;

        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;

        if (Input.isMouseDown(GLFW_MOUSE_BUTTON_2)) {
            camera.moveRotation(rotationVec.x * MOUSE_SENSITIVITY, rotationVec.y * MOUSE_SENSITIVITY, 0);
        }


    }
}
