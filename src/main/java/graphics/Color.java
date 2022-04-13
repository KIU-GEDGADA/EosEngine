package graphics;

import utils.MathUtils;

public class Color {
    public float red;
    public float green;
    public float blue;
    public float alpha;

    public Color(float r, float g, float b, float a) {
        this.red = MathUtils.clamp(r);
        this.green = MathUtils.clamp(g);
        this.blue = MathUtils.clamp(b);
        this.alpha = MathUtils.clamp(a);
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
}
