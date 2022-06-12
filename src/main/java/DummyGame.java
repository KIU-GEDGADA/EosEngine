import core.Camera;
import core.Entity;
import core.Item;
import core.Renderer;
import graphics.Mesh;
import graphics.Model;
import graphics.Shader;
import graphics.Texture;
import io.Input;
import math.Vector2f;
import math.Vector3f;
import utils.TimeUtils;

import java.util.List;

import static enums.Constants.*;
import static enums.Constants.MOUSE_SENSITIVITY;
import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements Entity {
    Item item2;
    Texture texture1;
    Camera camera;

    Vector2f previousPos = new Vector2f(-1, -1);
    Vector2f rotationVec = new Vector2f();

    Vector3f cameraVelocity = Vector3f.zero();

    @Override
    public void init() {
        Shader vs = new Shader("res/shaders/tv2vs.glsl");
        Shader fs = new Shader("res/shaders/tv2fs.glsl");
        List<Shader> shaders = List.of(new Shader[]{vs, fs});

        texture1 = new Texture("res/textures/goodTexture.png");
        camera = new Camera((float) WIDTH / HEIGHT);
        item2 = new Item("Cube", new Model(new Mesh("res/models/goodCube.obj")), camera, shaders, texture1);
        item2.getTransform().getScale().div(4f);

        Renderer.addItem(item2);
    }

    @Override
    public void update() {

        mousePosGetter();

        item2.getTransform().getRotation().y += 2;

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

    @Override
    public void render() {
    }

    @Override
    public void destroy() {
        //item1.destroy();
        item2.destroy();
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


        System.out.println(currentPos);
    }
}
