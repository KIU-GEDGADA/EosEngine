import core.*;

import static enums.Constants.*;


public class Preview {
    public static void main(String[] args) {

        Window w = Window.getInstance(WIDTH, HEIGHT, "Preview", false);
        DummyGame e = new DummyGame();
        Engine ee = Engine.getInstance(w);
        ee.getEntities().add(e);
        ee.start();
    }
}
