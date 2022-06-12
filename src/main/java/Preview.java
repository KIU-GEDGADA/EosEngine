import core.*;

import static enums.Constants.*;


public class Preview {
    public static void main(String[] args) {

        Window w = new Window(WIDTH, HEIGHT, "Preview", false);
        DummyGame e = new DummyGame();
        Engine ee = new Engine(w);
        ee.getEntities().add(e);
        ee.start();
    }
}
