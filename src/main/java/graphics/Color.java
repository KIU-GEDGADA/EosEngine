package graphics;

import utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class Color {
    public float red;
    public float green;
    public float blue;
    public float alpha;

    public Color(float r, float g, float b, float a) {
        this.red = MathUtils.clamp(r, 0.0f, 1.0f);
        this.green = MathUtils.clamp(g, 0.0f, 1.0f);
        this.blue = MathUtils.clamp(b, 0.0f, 1.0f);
        this.alpha = MathUtils.clamp(a, 0.0f, 1.0f);
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1.0f);
    }

    public Color(float grey){
        this(grey, grey, grey);
    }

    public String toString() {
        return "Color(" + red + ", " + green + ", " + blue + ", " + alpha + ")";
    }

    public boolean equals(Color other) {
        return red == other.red && green == other.green && blue == other.blue && alpha == other.alpha;
    }

    public float[] toArray() {
        return new float[]{red, green, blue, alpha};
    }

    public static Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    public static Color BLACK = new Color(0.0f, 0.0f, 0.0f);
    public static Color GREY = new Color(0.5f, 0.5f, 0.5f);
    public static Color RED = new Color(1.0f, 0.0f, 0.0f);
    public static Color GREEN = new Color(0.0f, 1.0f, 0.0f);
    public static Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    public static Color CYAN = new Color(0.0f, 1.0f, 1.0f);
    public static Color YELLOW = new Color(1.0f, 1.0f, 0.0f);
    public static Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
    public static Color TRANSPARENT = new Color(0.0f, 0.0f, 0.0f, 0.0f);

    public static Color random(){
        Color[] colors = new Color[]{RED, GREEN, BLUE, CYAN, YELLOW, MAGENTA};
        return colors[(int)(Math.random() * colors.length)];
    }
}
