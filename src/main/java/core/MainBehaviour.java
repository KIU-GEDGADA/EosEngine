package core;

public interface MainBehaviour {


    void init() throws Exception;

    void input(Window window);

    void update(double interval);

    void update();

    void render(Window window);

}
