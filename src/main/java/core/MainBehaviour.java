package core;

public interface MainBehaviour {


    void init() throws Exception;

    void input(Window window);

    void update(float interval);

    void update();

    void render(Window window);


}
