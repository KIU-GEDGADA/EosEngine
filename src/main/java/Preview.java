import core.*;

import static enums.Constants.*;


public class Preview {
    public static void main(String[] args) {

        Window window = Window.getInstance(WIDTH, HEIGHT, "Preview", false);
        DummyGame game = new DummyGame();
        Engine engine = Engine.getInstance(window);
        engine.getEntities().add(game);
        engine.start();
    }
}
