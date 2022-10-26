package finalDemo;

import core.Camera;
import core.Entity;
import core.Item;
import core.Renderer;
import graphics.*;
import graphics.lighting.PointLight;
import io.Input;
import math.Vector2f;
import math.Vector3f;
import utils.Generators;
import utils.TimeUtils;

import java.util.List;

import static enums.Constants.CAMERA_SPEED;
import static enums.Constants.MOUSE_SENSITIVITY;
import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements Entity {
    Item item1;
    Item item2;
    Item item3;
    Item item4;
    Item item5;
    Item item6;

    Item selectedItem;

    Item terrain1;

    Camera camera;

    // Lighting
    Vector2f previousPos = new Vector2f(-1, -1);
    Vector2f rotationVec = new Vector2f();
    Vector3f lightColor = Vector3f.one();
    PointLight pointLight = new PointLight(lightColor, new Vector3f(-10, 5, -10), 100f, 5, 0, 1);

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

        Vector3f pos1 = new Vector3f(-10, 0, -10);
        Vector3f pos2 = new Vector3f(-15, 0, -10);
        Vector3f pos3 = new Vector3f(-20, 0, -10);
        Vector3f pos4 = new Vector3f(-5, 0, -10);
        Vector3f pos5 = new Vector3f(0, 0, -10);
        Vector3f pos6 = new Vector3f(5, 0, -10);


        item3 = new Item(pos1, "Cube", new Model(new Mesh("res/models/grass.obj")).setTexture(texture1, 1f), shaders); //With textures and lights

        item2 = new Item(pos2, "Cube", new Model(new Mesh("res/models/grass.obj")).setTexture(texture1, 1f), shaders1); // With textures only

        Model colorModel = new Model(new Mesh("res/models/grass.obj"));
        item1 = new Item(pos3, "Cube", colorModel, shaders1); // With color

        Model complexModel = new Model(new Mesh("res/models/goodCube.obj"));
        item4 = new Item(pos4, "Complex Figure with Colors", complexModel, shaders1);

        item5 = new Item(pos5, "Complex Figure with texture", new Model(new Mesh("res/models/goodCube.obj")).setTexture(new Texture("res/textures/goodTexture.png")), shaders1);

        item6 = new Item(pos6, "Complex Figure with Ligthing and texture", new Model(new Mesh("res/models/goodCube.obj")).setTexture(new Texture("res/textures/goodTexture.png")), shaders);

        Model model = new Model(Generators.generateTerrain()).setTexture(texture2, 0.1f);
        model.getMaterial().setAmbientColor(Color.WHITE.toVector4f());
        terrain1 = new Item(new Vector3f(-100, -1, -100), "Terrain1", model, terrainShaders);

        Renderer.addLight(pointLight);

        Renderer.addItem(item1);
        Renderer.addItem(item2);
        Renderer.addItem(item3);
        Renderer.addItem(item4);
        Renderer.addItem(item5);
        Renderer.addItem(item6);

        selectedItem = item1;

        Renderer.addItem(terrain1);

    }

    @Override
    public void update() {
        cameraControl();
        lightControl();
        selectObject();
        objectControl();
    }

    private void objectControl() {

        if (item1 != selectedItem){
            item1.getTransform().getRotation().y += 0.5f;
        }
        if (item2 != selectedItem){
            item2.getTransform().getRotation().y += 0.5f;
        }
        if (item3 != selectedItem){
            item3.getTransform().getRotation().y += 0.5f;
        }
        if (item4 != selectedItem){
            item4.getTransform().getRotation().y += 0.5f;
        }
        if (item5 != selectedItem){
            item5.getTransform().getRotation().y += 0.5f;
        }
        if (item6 != selectedItem){
            item6.getTransform().getRotation().y += 0.5f;
        }

        if (Input.isKeyDown(GLFW_KEY_R)) {
            if (Input.isKeyDown(GLFW_KEY_W)) selectedItem.getTransform().getRotation().x += 5;
            if (Input.isKeyDown(GLFW_KEY_S)) selectedItem.getTransform().getRotation().x -= 5;
            if (Input.isKeyDown(GLFW_KEY_A)) selectedItem.getTransform().getRotation().z += 5;
            if (Input.isKeyDown(GLFW_KEY_D)) selectedItem.getTransform().getRotation().z -= 5;
        }
        if (Input.isKeyDown(GLFW_KEY_T)) {
            if (Input.isKeyDown(GLFW_KEY_W)) selectedItem.getTransform().getPosition().y += 0.05f;
            if (Input.isKeyDown(GLFW_KEY_S)) selectedItem.getTransform().getPosition().y -= 0.05f;
            if (Input.isKeyDown(GLFW_KEY_A)) selectedItem.getTransform().getPosition().x -= 0.05f;
            if (Input.isKeyDown(GLFW_KEY_D)) selectedItem.getTransform().getPosition().x += 0.05f;
            if (Input.isKeyDown(GLFW_KEY_Q)) selectedItem.getTransform().getPosition().z += 0.05f;
            if (Input.isKeyDown(GLFW_KEY_E)) selectedItem.getTransform().getPosition().z -= 0.05f;
        }
        if (Input.isKeyDown(GLFW_KEY_Y)){
            if (Input.isKeyDown(GLFW_KEY_W)) {
                selectedItem.getTransform().getScale().x += 0.05f;
                selectedItem.getTransform().getScale().y += 0.05f;
                selectedItem.getTransform().getScale().z += 0.05f;
            }
            if (Input.isKeyDown(GLFW_KEY_S)) {
                selectedItem.getTransform().getScale().x -= 0.05f;
                selectedItem.getTransform().getScale().y -= 0.05f;
                selectedItem.getTransform().getScale().z -= 0.05f;
            }
        }
    }

    private void selectObject() {
        if (Input.isKeyPressed(GLFW_KEY_1)) {
            selectedItem = item1;
        } else if (Input.isKeyPressed(GLFW_KEY_2)) {
            selectedItem = item2;
        } else if (Input.isKeyPressed(GLFW_KEY_3)) {
            selectedItem = item3;
        } else if (Input.isKeyPressed(GLFW_KEY_4)) {
            selectedItem = item4;
        } else if (Input.isKeyPressed(GLFW_KEY_5)) {
            selectedItem = item5;
        } else if (Input.isKeyPressed(GLFW_KEY_6)) {
            selectedItem = item6;
        }
    }

    @Override
    public void render() {
    }

    @Override
    public void destroy() {
        item1.destroy();
        item2.destroy();
        item3.destroy();
        item4.destroy();
        item5.destroy();
        item6.destroy();
        terrain1.destroy();
    }

    private void cameraControl() {
        mousePosGetter();

        cameraVelocity = Vector3f.zero();

        if (!(Input.isKeyDown(GLFW_KEY_R) || Input.isKeyDown(GLFW_KEY_T) || Input.isKeyDown(GLFW_KEY_Y))) {
            if (Input.isKeyDown(GLFW_KEY_W)) cameraVelocity.z = -1;
            if (Input.isKeyDown(GLFW_KEY_S)) cameraVelocity.z = 1;
            if (Input.isKeyDown(GLFW_KEY_A)) cameraVelocity.x = -1;
            if (Input.isKeyDown(GLFW_KEY_D)) cameraVelocity.x = 1;
            if (Input.isKeyDown(GLFW_KEY_Q)) cameraVelocity.y = -1;
            if (Input.isKeyDown(GLFW_KEY_E)) cameraVelocity.y = 1;
        }


        camera.movePosition(cameraVelocity.x * TimeUtils.getDeltaTime() * CAMERA_SPEED, cameraVelocity.y * TimeUtils.getDeltaTime() * CAMERA_SPEED, cameraVelocity.z * TimeUtils.getDeltaTime() * CAMERA_SPEED);
    }

    private void lightControl() {
        if (Input.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            if (Input.isKeyDown(GLFW_KEY_UP)) {
                pointLight.getPosition().y -= 0.1f;
            }
            if (Input.isKeyDown(GLFW_KEY_DOWN)) {
                pointLight.getPosition().y += 0.1f;
            }
        } else {
            if (Input.isKeyDown(GLFW_KEY_LEFT)) {
                pointLight.getPosition().x -= 0.1f;
            }
            if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
                pointLight.getPosition().x += 0.1f;
            }
            if (Input.isKeyDown(GLFW_KEY_UP)) {
                pointLight.getPosition().z -= 0.1f;
            }
            if (Input.isKeyDown(GLFW_KEY_DOWN)) {
                pointLight.getPosition().z += 0.1f;
            }
        }
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
            if (rotateX) rotationVec.y = x;
            if (rotateY) rotationVec.x = y;

        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;

        camera.moveRotation(rotationVec.x * MOUSE_SENSITIVITY, rotationVec.y * MOUSE_SENSITIVITY, 0);

    }
}
