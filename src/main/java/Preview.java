import core.*;
import graphics.*;
import io.Input;
import math.Vector2f;
import math.Vector3f;
import utils.MathUtils;
import utils.TimeUtils;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Preview {
    public static void main(String[] args) {
        int WIDHT = 1000;
        int HEIGHT = 1000;
        Window w = new Window(1000, 1000, "Preview", false);

        Entity e = new Entity() {
            Item item1;
            Item item2;
            Texture texture1;
            Camera camera;

            @Override
            public void init() {
                Shader vs = new Shader("res/shaders/tv2vs.glsl");
                Shader fs = new Shader("res/shaders/tv2fs.glsl");
                List<Shader> shaders = List.of(new Shader[]{vs, fs});

                texture1 = new Texture("res/textures/goodTexture.png");
                camera = new Camera((float) WIDHT/HEIGHT);
                item2 = new Item("Cube", new Model(new Mesh("res/models/goodCube.obj")), camera, shaders, texture1);
                item2.getTransform().getScale().div(4f);

                Renderer.addItem(item2);
            }

            @Override
            public void update() {
                if (Input.isKeyDown(GLFW_KEY_W))
                       camera.getPosition().z -= TimeUtils.getDeltaTime();
                if(Input.isKeyDown(GLFW_KEY_S))
                    camera.getPosition().z += TimeUtils.getDeltaTime();
                if(Input.isKeyDown(GLFW_KEY_A))
                    camera.getRotation().y += TimeUtils.getDeltaTime() * 50;
                if(Input.isKeyDown(GLFW_KEY_D))
                    camera.getRotation().y -= TimeUtils.getDeltaTime() * 50;
//                        MathUtils.clamp(item2.getTransform().getRotation().x, 0, 360);
//                if (Input.isKeyDown(GLFW_KEY_R)) {
//                    if (Input.isKeyDown(GLFW_KEY_UP)) {
//                        item2.getTransform().getRotation().x += 5f;
//                        MathUtils.clamp(item2.getTransform().getRotation().x, 0, 360);
//                    } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
//                        item2.getTransform().getRotation().x -= 5f;
//                        MathUtils.clamp(item2.getTransform().getRotation().x, 0, 360);
//                    } else if (Input.isKeyDown(GLFW_KEY_LEFT)) {
//                        item2.getTransform().getRotation().y += 5f;
//                        MathUtils.clamp(item2.getTransform().getRotation().y, 0, 360);
//                    } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
//                        item2.getTransform().getRotation().y -= 5f;
//                        MathUtils.clamp(item2.getTransform().getRotation().y, 0, 360);
//                    }
//                } else if (Input.isKeyDown(GLFW_KEY_S)) {
//                    if (Input.isKeyDown(GLFW_KEY_A)) {
//                        if (Input.isKeyDown(GLFW_KEY_UP)) {
//                            item2.getTransform().getScale().y += 0.05f;
//                            item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
//                            item2.getTransform().getScale().x += 0.05f;
//                            item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
//                        } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
//                            item2.getTransform().getScale().y -= 0.05f;
//                            item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
//                            item2.getTransform().getScale().x -= 0.05f;
//                            item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
//                        }
//                    } else {
//                        if (Input.isKeyDown(GLFW_KEY_UP)) {
//                            item2.getTransform().getScale().y += 0.05f;
//                            item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
//                        } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
//                            item2.getTransform().getScale().y -= 0.05f;
//                            item2.getTransform().getScale().y = MathUtils.clamp(item2.getTransform().getScale().y, 0.0f, 1f);
//                        } else if (Input.isKeyDown(GLFW_KEY_LEFT)) {
//                            item2.getTransform().getScale().x += 0.05f;
//                            item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
//                        } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
//                            item2.getTransform().getScale().x -= 0.05f;
//                            item2.getTransform().getScale().x = MathUtils.clamp(item2.getTransform().getScale().x, 0.0f, 1f);
//                        }
//                    }
//                } else if (Input.isKeyDown(GLFW_KEY_T)) {
//                    if (Input.isKeyDown(GLFW_KEY_UP)) {
//                        item2.getTransform().getPosition().y += 0.05f;
//                        item2.getTransform().getPosition().y = MathUtils.clamp(item2.getTransform().getPosition().y, -1f, 1f);
//                    } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
//                        item2.getTransform().getPosition().y -= 0.05f;
//                        item2.getTransform().getPosition().y = MathUtils.clamp(item2.getTransform().getPosition().y, -1f, 1f);
//                    } else if (Input.isKeyDown(GLFW_KEY_LEFT)) {
//                        item2.getTransform().getPosition().x -= 0.05f;
//                        item2.getTransform().getPosition().x = MathUtils.clamp(item2.getTransform().getPosition().x, -1f, 1f);
//                    } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
//                        item2.getTransform().getPosition().x += 0.05f;
//                        item2.getTransform().getPosition().x = MathUtils.clamp(item2.getTransform().getPosition().x, -1f, 1f);
//                    }
//                } else if (Input.isKeyDown(GLFW_KEY_UP) || Input.isKeyDown(GLFW_KEY_DOWN) || Input.isKeyDown(GLFW_KEY_LEFT) || Input.isKeyDown(GLFW_KEY_RIGHT)) {
//                    System.out.println("Please press T to change the position of the object\nS to change the scale of the object (+A for absolute scale)\nR to change the rotation of the object");
//                }
            }

            @Override
            public void render() {
            }

            @Override
            public void destroy() {
                //item1.destroy();
                item2.destroy();
            }
        };

        Engine ee = new Engine(w);
        ee.getEntities().add(e);
        ee.start();
    }
}
