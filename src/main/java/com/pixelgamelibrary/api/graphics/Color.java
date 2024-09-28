///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: Game library.
// Copyright (C) 2024 the original author or authors.
//
// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation, either version 3
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see
// <https://www.gnu.org/licenses/> or write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////
package com.pixelgamelibrary.api.graphics;

import lombok.Data;

/**
 * Represents a color in the RGBA format where each component is a float value
 * between 0 and 1. This class provides methods to manipulate color values,
 * including normalization, blending, and conversion to various formats (e.g.,
 * hexadecimal, integer). The color components are represented as:
 * <ul>
 * <li><b>red</b> - The intensity of the red component, ranging from 0 to
 * 1.</li>
 * <li><b>green</b> - The intensity of the green component, ranging from 0 to
 * 1.</li>
 * <li><b>blue</b> - The intensity of the blue component, ranging from 0 to
 * 1.</li>
 * <li><b>alpha</b> - The transparency level, where 0 is fully transparent and 1
 * is fully opaque.</li>
 * </ul>
 *
 * <p>
 * This class is immutable. Each method that modifies a color returns a new
 * instance instead of modifying the original.
 * </p>
 *
 * <p>
 * <b>Author:</b> robertvokac
 * </p>
 */
@Data
public final class Color {

    private static final int BITS_IN_ONE_BYTE = 255;

    private float red, green, blue, alpha;

    /**
     * Ensures that the color components are confined within the valid range [0,
     * 1]. If any component exceeds this range, it will be clamped to fit within
     * the specified boundaries. This method supports method chaining by
     * returning the current instance.
     *
     * @return the current instance of this Color for chaining
     */
    public Color normalize() {
        red = Math.max(0, Math.min(red, 1));
        green = Math.max(0, Math.min(green, 1));
        blue = Math.max(0, Math.min(blue, 1));
        alpha = Math.max(0, Math.min(alpha, 1));
        return this;
    }

    private static int floatElementToInt(float f) {

        return (int) (f * BITS_IN_ONE_BYTE);
    }

    private static float intElementToFloat(int i) {

        float iFloat = i;
        return iFloat / BITS_IN_ONE_BYTE;
    }
    // Default alpha value for colors
    public static final float DEFAULT_ALPHA = 1f;

    // Default alpha int value for colors
    public static final int DEFAULT_ALPHA_INT = 256;

    // Default constructor initializes color to black with full opacity
    public Color() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
        this.alpha = DEFAULT_ALPHA;
    }

    // Constructor initializes color with specified RGB values, defaulting alpha to full opacity
    public Color(float red, float green, float blue) {
        this(red, green, blue, DEFAULT_ALPHA);
    }

    // Constructor initializes color with specified RGBA values and normalizes them
    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        normalize();
    }

    // Constructor initializes color with specified RGB values, defaulting alpha to full opacity
    public Color(int red, int green, int blue) {
        this(red, green, blue, DEFAULT_ALPHA_INT);
    }

    // Constructor initializes color with specified RGBA values, converting from integers to floats
    public Color(int red, int green, int blue, int alpha) {
        this(intElementToFloat(red), intElementToFloat(green), intElementToFloat(blue), intElementToFloat(alpha));
    }

    // Copy constructor creates a new Color instance from an existing one
    public Color(Color color) {
        set(color);
    }

    /**
     * Sets the color's components to match the provided color.
     *
     * @param color the Color instance to copy from
     * @return this color instance for method chaining
     */
    public Color set(Color color) {
        return set(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    /**
     * Sets the color's components using the provided RGB values, defaulting
     * alpha to full opacity.
     *
     * @param red the red component
     * @param green the green component
     * @param blue the blue component
     * @return this color instance for method chaining
     */
    public Color set(float red, float green, float blue) {
        return set(red, green, blue, DEFAULT_ALPHA);
    }

    /**
     * Sets the color's components using the provided RGBA values.
     *
     * @param red the red component
     * @param green the green component
     * @param blue the blue component
     * @param alpha the alpha component
     * @return this color instance for method chaining
     */
    public Color set(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        return normalize();
    }

    /**
     * Sets the color's components using the provided RGB values, defaulting
     * alpha to full opacity.
     *
     * @param red the red component
     * @param green the green component
     * @param blue the blue component
     * @return this color instance for method chaining
     */
    public Color set(int red, int green, int blue) {
        return set(red, green, blue, DEFAULT_ALPHA_INT);
    }

    /**
     * Sets the color's components using the provided RGBA values.
     *
     * @param red the red component
     * @param green the green component
     * @param blue the blue component
     * @param alpha the alpha component
     * @return this color instance for method chaining
     */
    public Color set(int red, int green, int blue, int alpha) {
        return set(
                intElementToFloat(red),
                intElementToFloat(green),
                intElementToFloat(blue),
                intElementToFloat(alpha));
    }

    /**
     * Blends this color with the specified color by multiplying their
     * respective components.
     *
     * @param other the color to blend with
     * @return the updated color instance for method chaining
     */
    public Color multiply(Color other) {
        this.red *= other.red;
        this.green *= other.green;
        this.blue *= other.blue;
        this.alpha *= other.alpha;
        return normalize();
    }

    /**
     * Scales the intensity of this color by multiplying each component by the
     * specified factor.
     *
     * @param factor the scalar value to multiply with
     * @return the updated color instance for method chaining
     */
    public Color multiply(float factor) {
        this.red *= factor;
        this.green *= factor;
        this.blue *= factor;
        this.alpha *= factor;
        return normalize();
    }

    /**
     * Increases the intensity of this color by adding the corresponding values
     * from another color.
     *
     * @param other the color whose components will be added
     * @return the updated color instance for method chaining
     */
    public Color add(Color other) {
        this.red += other.red;
        this.green += other.green;
        this.blue += other.blue;
        this.alpha += other.alpha;
        return normalize();
    }

    /**
     * Decreases the intensity of this color by subtracting the corresponding
     * values from another color.
     *
     * @param other the color whose components will be subtracted
     * @return the updated color instance for method chaining
     */
    public Color subtract(Color other) {
        this.red -= other.red;
        this.green -= other.green;
        this.blue -= other.blue;
        this.alpha -= other.alpha;
        return normalize();
    }

    public Color multiply(float r, float g, float b, float a) {
        this.red *= r;
        this.green *= g;
        this.blue *= b;
        this.alpha *= a;
        return normalize();
    }

    public Color add(float r, float g, float b, float a) {
        this.red += r;
        this.green += g;
        this.blue += b;
        this.alpha += a;
        return normalize();
    }

    public Color subtract(float r, float g, float b, float a) {
        this.red -= r;
        this.green -= g;
        this.blue -= b;
        this.alpha -= a;
        return normalize();
    }

    @Override
    public boolean equals(Object obj) {
        // Check for reference equality
        if (this == obj) {
            return true;
        }

        // Ensure the other object is non-null and of the same class
        if (obj == null || !(obj instanceof Color)) {
            return false;
        }

        // Cast and compare the packed integer representations of the two colors
        Color otherColor = (Color) obj;
        return this.toInt() == otherColor.toInt();
    }

    @Override
    public int hashCode() {
        // Calculate a hash code based on the color's components
        int hash = (red != 0.0f ? Float.floatToIntBits(red) : 0);
        hash = 31 * hash + (green != 0.0f ? Float.floatToIntBits(green) : 0);
        hash = 31 * hash + (blue != 0.0f ? Float.floatToIntBits(blue) : 0);
        hash = 31 * hash + (alpha != 0.0f ? Float.floatToIntBits(alpha) : 0);
        return hash;
    }

    /**
     * Converts the color to an integer representation (0xAARRGGBB).
     *
     * @return the integer representation of this color
     */
    public int toInt() {
        int a = Math.round(alpha * 255);
        int r = Math.round(red * 255);
        int g = Math.round(green * 255);
        int b = Math.round(blue * 255);
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    /**
     * Converts the color to an integer representation (0xAARRGGBB).
     *
     * @return the integer representation of this color
     */
    public int toIntRGBA() {
        int a = Math.round(alpha * 255);
        int r = Math.round(red * 255);
        int g = Math.round(green * 255);
        int b = Math.round(blue * 255);
        return (r << 24) | (b << 16) | (b << 8) | a;
    }

    public static int alpha(float alpha) {
        return Math.round(alpha * 255);
    }

    /**
     * Converts the color to a hexadecimal string representation (e.g.,
     * "#AARRGGBB").
     *
     * @return the hexadecimal string representation of this color
     */
    public String toHexString() {
        return String.format("#%08X", toIntRGBA());
    }

    public static Color valueOf(String hexString) {
        // Remove '#' if present
        if (hexString.startsWith("#")) {
            hexString = hexString.substring(1);
        }

        // Ensure the hex string is the correct length
        if (hexString.length() != 8 && hexString.length() != 6) {
            throw new IllegalArgumentException("Hex string must be in the format #AARRGGBB or RRGGBB");
        }

        int r, g, b, a = 255; // Default alpha is fully opaque

        // Parse the hex values
        if (hexString.length() == 8) { // Format: AARRGGBB
            a = Integer.parseInt(hexString.substring(0, 2), 16);
            r = Integer.parseInt(hexString.substring(2, 4), 16);
            g = Integer.parseInt(hexString.substring(4, 6), 16);
            b = Integer.parseInt(hexString.substring(6, 8), 16);
        } else { // Format: RRGGBB
            r = Integer.parseInt(hexString.substring(0, 2), 16);
            g = Integer.parseInt(hexString.substring(2, 4), 16);
            b = Integer.parseInt(hexString.substring(4, 6), 16);
        }

        return new Color(r, g, b, a);
    }

    public Color copy() {
        return new Color(this);
    }

    public float getRedFloat() {
        return red;
    }

    public float getGreenFloat() {
        return green;
    }

    public float getBlueFloat() {
        return blue;
    }

    public float getAlphaFloat() {
        return alpha;
    }

    public int getRedInt() {
        return floatElementToInt(red);
    }

    public int getGreenInt() {
        return floatElementToInt(green);
    }

    public int getBlueInt() {
        return floatElementToInt(blue);
    }

    public int getAlphaInt() {
        return floatElementToInt(alpha);
    }

    public void set16Bit() {
        this.red = floatElementToInt(this.red) >> 3; // 5 bitů
        this.green = floatElementToInt(this.green) >> 2; // 6 bitů
        this.blue = floatElementToInt(this.blue) >> 3; // 5 bitů
    }

    public void set8Bit() {
this.red = floatElementToInt(this.red) >> 5; // 3 bity (max 7)
this.green = floatElementToInt(this.green) >> 5; // 3 bity (max 7)
this.blue = floatElementToInt(this.blue) >> 6; // 2 bity (max 3)
    }

    public void set4Bit() {
        this.red = floatElementToInt(this.red) >> 1; // 2 bitů
        this.green = floatElementToInt(this.green) >> 1; // 2 bitů
        this.blue = floatElementToInt(this.blue) >> 1; // 2 bitů
    }

    public void setWhiteBlack8Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 255);
        this.red = this.green = this.blue = grayscale / 255f; // Černobílý
    }

    public void setWhiteBlack4Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 15);
        this.red = this.green = this.blue = grayscale / 15f; // Černobílý
    }

    public void setWhiteBlack2Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 3);
        this.red = this.green = this.blue = grayscale / 3f; // Černobílý
    }

    public void setWhiteBlack1Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 1);
        this.red = this.green = this.blue = grayscale / 1f; // Černobílý
    }

    public int getByteRepresentation16Bit() {
        return (floatElementToInt(this.red) << 11) | (floatElementToInt(this.green) << 5) | floatElementToInt(this.blue);
    }

    public int getByteRepresentation8Bit() {
        return (floatElementToInt(this.red) << 4) | (floatElementToInt(this.green) << 2) | floatElementToInt(this.blue);
    }

    public int getByteRepresentation4Bit() {
        return (floatElementToInt(this.red) << 2) | (floatElementToInt(this.green) << 1) | floatElementToInt(this.blue);
    }

    public int getByteRepresentationWhiteBlack8Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 255);
        return grayscale;
    }

    public int getByteRepresentationWhiteBlack4Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 15);
        return grayscale;
    }

    public int getByteRepresentationWhiteBlack2Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 3);
        return grayscale;
    }

    public int getByteRepresentationWhiteBlack1Bit() {
        int grayscale = (int) ((red + green + blue) / 3 * 1);
        return grayscale;
    }

    @Override
    public String toString() {
        // Provide a string representation of the color in RGBA format
        return String.format("Color{red=%f, green=%f, blue=%f, alpha=%f}", red, green, blue, alpha);
    }

}
