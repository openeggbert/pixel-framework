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

/**
 * Interface for drawable objects, providing methods for drawing shapes, 
 * filling areas, and manipulating colors.
 * 
 * @author robertvokac
 */
public interface Drawable {

    // Set the color using a Color object
    void setColor(Color color);

    // Set the color using float values for red, green, blue, and alpha
    void setColor(float red, float green, float blue, float alpha);

    // Set the color using float values for red, green, and blue
    void setColor(float red, float green, float blue);

    // Set the color using integer values for red, green, blue, and alpha
    void setColor(int red, int green, int blue, int alpha);

    // Set the color using integer values for red, green, and blue
    void setColor(int red, int green, int blue);

    // Fill the current shape with the set color
    void fill();

    // Draw a line from (x, y) to (x2, y2)
    void drawLine(int x, int y, int x2, int y2);

    // Draw a rectangle at (x, y) with specified width and height
    void drawRectangle(int x, int y, int width, int height);

    // Draw a pixmap at (x, y)
    void drawPixmap(Pixmap pixmap, int x, int y);

    // Draw a portion of a pixmap defined by source coordinates and dimensions
    void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);

    // Draw a portion of a pixmap to a destination defined by destination coordinates and dimensions
    void drawPixmap(Pixmap pixmap, int srcX, int srcY, int srcWidth, int srcHeight, int dstX, int dstY, int dstWidth, int dstHeight);

    // Fill a rectangle at (x, y) with specified width and height
    void fillRectangle(int x, int y, int width, int height);

    // Draw a circle with center at (x, y) and specified radius
    void drawCircle(int x, int y, int radius);

    // Fill a circle with center at (x, y) and specified radius
    void fillCircle(int x, int y, int radius);

    // Fill a triangle defined by three points
    void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

    // Get the color of the pixel at (x, y)
    int getPixel(int x, int y);

    // Get the width of the drawable area
    int getWidth();

    // Get the height of the drawable area
    int getHeight();

    // Dispose of resources used by the drawable
    void dispose();

    // Check if the drawable has been disposed
    boolean isDisposed();

    // Draw a pixel at (x, y) using the currently set color
    void drawPixel(int x, int y);

    // Draw a pixel at (x, y) with a specified color
    void drawPixel(int x, int y, int color);

    // Get all pixels as a byte array
    byte[] getPixels();

    // Set the pixels using a byte array
    void setPixels(byte[] pixels);
}
