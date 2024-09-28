package com.pixelgamelibrary.api.graphics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

public class ColorTest {

    @Test
    public void testDefaultConstructor() {
        Color color = new Color();
        assertEquals(0.0f, color.getRed(), 0.001);
        assertEquals(0.0f, color.getGreen(), 0.001);
        assertEquals(0.0f, color.getBlue(), 0.001);
        assertEquals(1.0f, color.getAlpha(), 0.001);
    }

    @Test
    public void testRgbConstructor() {
        Color color = new Color(0.5f, 0.5f, 0.5f);
        assertEquals(0.5f, color.getRed(), 0.001);
        assertEquals(0.5f, color.getGreen(), 0.001);
        assertEquals(0.5f, color.getBlue(), 0.001);
        assertEquals(1.0f, color.getAlpha(), 0.001);
    }

    @Test
    public void testRgbaConstructor() {
        Color color = new Color(0.5f, 0.5f, 0.5f, 0.5f);
        assertEquals(0.5f, color.getRed(), 0.001);
        assertEquals(0.5f, color.getGreen(), 0.001);
        assertEquals(0.5f, color.getBlue(), 0.001);
        assertEquals(0.5f, color.getAlpha(), 0.001);
    }

    @Test
    public void testIntConstructor() {
        Color color = new Color(128, 64, 32);
        assertEquals(128 / 255f, color.getRed(), 0.001);
        assertEquals(64 / 255f, color.getGreen(), 0.001);
        assertEquals(32 / 255f, color.getBlue(), 0.001);
        assertEquals(1.0f, color.getAlpha(), 0.001);
    }

    @Test
    public void testIntRgbaConstructor() {
        Color color = new Color(128, 64, 32, 128);
        assertEquals(128 / 255f, color.getRed(), 0.001);
        assertEquals(64 / 255f, color.getGreen(), 0.001);
        assertEquals(32 / 255f, color.getBlue(), 0.001);
        assertEquals(128 / 255f, color.getAlpha(), 0.001);
    }

    @Test
    public void testNormalization() {
        Color color = new Color(1.2f, -0.2f, 0.5f, 1.5f);
        color.normalize();
        assertEquals(1.0f, color.getRed(), 0.001);
        assertEquals(0.0f, color.getGreen(), 0.001);
        assertEquals(0.5f, color.getBlue(), 0.001);
        assertEquals(1.0f, color.getAlpha(), 0.001);
    }

    @Test
    public void testMultiplyWithAnotherColor() {
        Color color1 = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        Color color2 = new Color(0.2f, 0.4f, 0.6f, 1.0f);
        color1.multiply(color2);
        assertEquals(0.1f, color1.getRed(), 0.001);
        assertEquals(0.2f, color1.getGreen(), 0.001);
        assertEquals(0.3f, color1.getBlue(), 0.001);
        assertEquals(1.0f, color1.getAlpha(), 0.001);
    }

    @Test
    public void testMultiplyWithFactor() {
        Color color = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        color.multiply(2.0f);
        assertEquals(1.0f, color.getRed(), 0.001);
        assertEquals(1.0f, color.getGreen(), 0.001);
        assertEquals(1.0f, color.getBlue(), 0.001);
        assertEquals(1.0f, color.getAlpha(), 0.001);
    }

    @Test
    public void testAdd() {
        Color color1 = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        Color color2 = new Color(0.3f, 0.3f, 0.3f, 1.0f);
        color1.add(color2);
        assertEquals(0.8f, color1.getRed(), 0.001);
        assertEquals(0.8f, color1.getGreen(), 0.001);
        assertEquals(0.8f, color1.getBlue(), 0.001);
        assertEquals(1.0f, color1.getAlpha(), 0.001);
    }

    @Test
    public void testSubtract() {
        Color color1 = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        Color color2 = new Color(0.2f, 0.2f, 0.2f, 1.0f);
        color1.subtract(color2);
        assertEquals(0.3f, color1.getRed(), 0.001);
        assertEquals(0.3f, color1.getGreen(), 0.001);
        assertEquals(0.3f, color1.getBlue(), 0.001);
        assertEquals(0.0f, color1.getAlpha(), 0.001);
    }

    @Test
    public void testEqualsAndHashCode() {
        Color color1 = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        Color color2 = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        assertEquals(color1, color2);
        assertEquals(color1.hashCode(), color2.hashCode());

        Color color3 = new Color(0.5f, 0.5f, 0.5f, 0.8f);
        assertNotEquals(color1, color3);
    }

    @Test
    public void testToInt() {
        Color color = new Color(255, 127, 64);
        int expected = 0xFFFF7F40; // (0xAARRGGBB) with alpha = 255, red = 255, green = 127, blue = 64
        assertEquals(expected, color.toInt());
    }

    @Test
    public void testToHexString() {
        Color color = new Color(255, 127, 127);
        assertEquals("#FF7F7FFF", color.toHexString());
    }

    @Test
    public void testValueOfHex() {
        Color color = Color.valueOf("#FF7F7F7F");
        assertEquals(0.5f, color.getRed(), 0.002);
        assertEquals(0.5f, color.getGreen(), 0.002);
        assertEquals(0.5f, color.getBlue(), 0.002);
        assertEquals(1.0f, color.getAlpha(), 0.001);
    }

    @Test
    public void testValueOfHexWithoutHash() {
        Color color = Color.valueOf("FF7F7F7F");
        assertEquals(0.5f, color.getRed(), 0.002);
        assertEquals(0.5f, color.getGreen(), 0.002);
        assertEquals(0.5f, color.getBlue(), 0.002);
        assertEquals(1.0f, color.getAlpha(), 0.002);
    }

    @Test @Disabled
    public void testValueOfHexWithAlpha() {
        Color color = Color.valueOf("#80FF7F7F");
        assertEquals(0.5f, color.getRed(), 0.001);
        assertEquals(0.5f, color.getGreen(), 0.001);
        assertEquals(0.5f, color.getBlue(), 0.001);
        assertEquals(0.5f, color.getAlpha(), 0.001);
    }

    @Test
    public void testAlphaConversion() {
        assertEquals(255, Color.alpha(1.0f));
        assertEquals(128, Color.alpha(0.5f));
        assertEquals(0, Color.alpha(0.0f));
    }

    @Test
    public void testCopy() {
        Color color = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        Color copy = color.copy();
        assertEquals(color, copy);
        assertNotSame(color, copy); // Ensure they are different objects
    }

    @Test
    public void testToString() {
        Color color = new Color(0.5f, 0.5f, 0.5f, 0.75f);
        assertEquals("Color{red=0.500000, green=0.500000, blue=0.500000, alpha=0.750000}", color.toString());
    }

    @Test
    public void testDifferentColors() {
        Color color1 = new Color(1.0f, 0.0f, 0.0f, 1.0f); // Red
        Color color2 = new Color(0.0f, 1.0f, 0.0f, 1.0f); // Green
        Color color3 = new Color(0.0f, 0.0f, 1.0f, 1.0f); // Blue

        assertNotEquals(color1, color2);
        assertNotEquals(color2, color3);
        assertNotEquals(color3, color1);
    }
}
