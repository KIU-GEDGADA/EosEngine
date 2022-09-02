package finalDemo;

import core.Camera;
import core.Entity;
import core.Item;
import core.Renderer;
import graphics.*;
import graphics.lighting.DirectionalLight;
import graphics.lighting.PointLight;
import io.Input;
import math.Vector2f;
import math.Vector3f;
import utils.Generators;
import utils.TimeUtils;

import java.util.List;

import static enums.Constants.MOUSE_SENSITIVITY;
import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements Entity {
    Item item1;
    Item item2;
    Item item3;
    Item item4;

    Item terrain1;

    Camera camera;

    // Lighting
    private float lightAngle;
    // DirectionalLight directionalLight;
    Vector2f previousPos = new Vector2f(-1, -1);
    Vector2f rotationVec = new Vector2f();
    Vector3f lightColor = Vector3f.one();
    PointLight pointLight = new PointLight(lightColor, Vector3f.zero(), 100f, 0, 0, 1);

    Vector3f cameraVelocity = Vector3f.zero();

    @Override
    public void init() {
        Shader vs = new Shader("res/shaders/tv2vs.glsl");
        Shader fs = new Shader("res/shaders/tv2fs.glsl");
        List<Shader> shaders = List.of(new Shader[]{vs, fs});

        Shader vs1 = new Shader("res/shaders/tv1vs.glsl");
        Shader fs1 = new Shader("res/shaders/tv1fs.glsl");
        List<Shader> shaders1 = List.of(new Shader[]{vs1, fs1});

        Shader tvs = new Shader("res/shaders/terrain_tv2vs.glsl");
        Shader tfs = new Shader("res/shaders/terrain_tv2fs.glsl");
        List<Shader> terrainShaders = List.of(new Shader[]{tvs, tfs});

        Texture texture1 = new Texture("res/textures/grass.png");
        Texture texture2 = new Texture("res/textures/terrainTex.png");
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

        Vector3f pos1 = new Vector3f(-10, 0, -10);
        Vector3f pos2 = new Vector3f(-20, 0, -10);
        Vector3f pos3 = new Vector3f(-30, 0, -10);

        item1 = new Item(pos1, "Cube", new Model(new Mesh("res/models/grass.obj")).setTexture(texture1, 1f), shaders); //With textures and lights
        item1.setLights(directionalLight, pointLight);

        item2 = new Item(pos2, "Cube", new Model(new Mesh("res/models/grass.obj")).setTexture(texture1, 1f), shaders1); // With textures only

        Model colorModel = new Model(new Mesh("res/models/grass.obj"));
        item3 = new Item(pos3, "Cube", colorModel, shaders1); // With color

        Model model = new Model(Generators.generateTerrain()).setTexture(texture2, 0.1f);
        model.getMaterial().setAmbientColor(Color.WHITE.toVector4f());
        terrain1 = new Item(new Vector3f(-100, -1, -100), "Terrain1", model, terrainShaders);
        terrain1.setLights(directionalLight, pointLight);

        Renderer.addItem(item1);
        Renderer.addItem(item2);
        Renderer.addItem(item3);

        Renderer.addItem(terrain1);


    }

    @Override
    public void update() {

        cameraControl();
        lightSetup();


    }

    @Override
    public void render() {
    }

    @Override
    public void destroy() {
        item2.destroy();
    }

    private void cameraControl() {
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


        camera.movePosition(
                cameraVelocity.x * TimeUtils.getDeltaTime(),
                cameraVelocity.y * TimeUtils.getDeltaTime(),
                cameraVelocity.z * TimeUtils.getDeltaTime()
        );
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
