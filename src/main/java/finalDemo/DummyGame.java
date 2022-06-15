package finalDemo;

import core.*;
import graphics.*;
import io.*;
import math.*;
import utils.*;
import java.util.List;

import static enums.Constants.*;
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
        camera = Camera.getInstance();
        item2 = new Item("Cube", new Model(new Mesh("res/models/goodCube.obj")), shaders, texture1);
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


    }
}
