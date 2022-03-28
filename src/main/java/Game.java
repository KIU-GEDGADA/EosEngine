import core.MainBehaviour;
import core.Window;

public class Game implements MainBehaviour {
    private double k;

    @Override
    public void init() throws Exception {
        k = 0;
    }

    @Override
    public void update(double interval) {
        k += interval;
        System.out.println(k);
    }

    @Override
    public void render(Window window) {

    }
}
