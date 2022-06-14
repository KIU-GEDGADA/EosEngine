package graphics;

import utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This function implements RGBA colors and operations on them within the EosEngine
 */
public class Color {
    /**
     * This variable is the red value of the color
     */
    public float red;
    /**
     * This variable is the green value of the color
     */
    public float green;
    /**
     * This variable is the blue value of the color
     */
    public float blue;
    /**
     * This variable is the alpha of the color
     */
    public float alpha;

    /**
     * Class creator, this function creates a Color from 4 float values
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     * @param a the alpha value
     */
    public Color(float r, float g, float b, float a) {
        this.red = MathUtils.clamp(r, 0.0f, 1.0f);
        this.green = MathUtils.clamp(g, 0.0f, 1.0f);
        this.blue = MathUtils.clamp(b, 0.0f, 1.0f);
        this.alpha = MathUtils.clamp(a, 0.0f, 1.0f);
    }

    /**
     * Class creator, this function creates a Color from 3 float values with alpha being initialized to 1
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     */
    public Color(float r, float g, float b) {
        this(r, g, b, 1.0f);
    }

    /**
     * Class creator, this function creates a color from 1 float value, with rgb being set to it and alpha to 1
     * @param grey
     */
    public Color(float grey){
        this(grey, grey, grey);
    }

    /**
     * This function returns a string representation of the color
     * @return a string representation of the color of format "Color(red,green,blue,alpha)"
     */
    public String toString() {
        return "Color(" + red + ", " + green + ", " + blue + ", " + alpha + ")";
    }

    /**
     * This function checks if the current color equals another, returns true if it does, false otherwise
     * @param other the color to compare to
     * @return true if the colors are equal, false otherwise
     */
    public boolean equals(Color other) {
        return red == other.red && green == other.green && blue == other.blue && alpha == other.alpha;
    }

    /**
     * This function returns an array representation of the color
     * @return an array representation of the color of the format [red,green,blue,alpha]
     */
    public float[] toArray() {
        return new float[]{red, green, blue, alpha};
    }

    /**
     * This function creates a white color object
     */
    public static Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    /**
     * This function creates a black color object
     */
    public static Color BLACK = new Color(0.0f, 0.0f, 0.0f);
    /**
     * This function creates a grey color object
     */
    public static Color GREY = new Color(0.5f, 0.5f, 0.5f);
    /**
     * This function creates a red color object
     */
    public static Color RED = new Color(1.0f, 0.0f, 0.0f);
    /**
     * This function creates a green color object
     */
    public static Color GREEN = new Color(0.0f, 1.0f, 0.0f);
    /**
     * This function creates a blue color object
     */
    public static Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    /**
     * This function creates a cyan color object
     */
    public static Color CYAN = new Color(0.0f, 1.0f, 1.0f);
    /**
     * This function creates a yellow color object
     */
    public static Color YELLOW = new Color(1.0f, 1.0f, 0.0f);
    /**
     * This function creates a magenta color object
     */
    public static Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
    /**
     * This function creates a transparent, colorless color object
     */
    public static Color TRANSPARENT = new Color(0.0f, 0.0f, 0.0f, 0.0f);

    /**
     * This function returns a random rgba color from the following colors: red, green, blue, cyan, yellow,magenta
     * @return a random color from the following:  red, green, blue, cyan, yellow,magenta
     */
    public static Color random(){
        Color[] colors = new Color[]{RED, GREEN, BLUE, CYAN, YELLOW, MAGENTA};
        return colors[(int)(Math.random() * colors.length)];
    }
}
