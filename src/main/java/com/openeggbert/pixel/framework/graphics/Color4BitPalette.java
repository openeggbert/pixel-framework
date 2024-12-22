package com.openeggbert.pixel.framework.graphics;

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
/**
 *
 * @author robertvokac
 */
public class Color4BitPalette {

    private Color4BitPalette() {
        //Not meant to be instantiated.
    }
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color CYAN = new Color(0, 255, 255);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color MAGENTA = new Color(255, 0, 255);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color GRAY = new Color(128, 128, 128);
    public static final Color DARK_BLUE = new Color(0, 0, 128);
    public static final Color DARK_GREEN = new Color(0, 128, 0);
    public static final Color DARK_CYAN = new Color(0, 128, 128);
    public static final Color DARK_RED = new Color(128, 0, 0);
    public static final Color DARK_MAGENTA = new Color(128, 0, 128);
    public static final Color DARK_YELLOW = new Color(128, 128, 0);
    public static final Color LIGHT_GRAY = new Color(192, 192, 192);

    // Define a color palette for 4-bit graphics (16 colors)
    private static final Color[] COLORS_4BIT_PALETTE = {
        Color4BitPalette.BLACK,
        Color4BitPalette.BLUE,
        Color4BitPalette.GREEN,
        Color4BitPalette.CYAN,
        Color4BitPalette.RED,
        Color4BitPalette.MAGENTA,
        Color4BitPalette.YELLOW,
        Color4BitPalette.WHITE,
        Color4BitPalette.GRAY,
        Color4BitPalette.DARK_BLUE,
        Color4BitPalette.DARK_GREEN,
        Color4BitPalette.DARK_CYAN,
        Color4BitPalette.DARK_RED,
        Color4BitPalette.DARK_MAGENTA,
        Color4BitPalette.DARK_YELLOW,
        Color4BitPalette.LIGHT_GRAY
    };

    public static Color findClosestColor(Color color) {
        Color closestColor = null;
        double minDistance = Double.MAX_VALUE;

        for (Color paletteColor : COLORS_4BIT_PALETTE) {
            double distance = paletteColor.distanceTo(color);
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = paletteColor;
            }
        }

        return closestColor;
    }

}
