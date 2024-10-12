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

import com.pixelgamelibrary.api.Disposable;
import com.pixelgamelibrary.api.math.Rectangle;
import com.pixelgamelibrary.api.math.Angle;

/**
 *
 * @author robertvokac
 */
public interface ShapeRenderer extends Disposable {

    void setColor(Color color);

    default void setColor(float r, float g, float b) {
        setColor(new Color(r, g, b));
    }

    default void setColor(float r, float g, float b, float a) {
        setColor(new Color(r, g, b, a));
    }

    Color getColor();

    void filledRectangle(float x, float y, float width, float height, Angle rotation, Color color);
    
    default void filledRectangle(float x, float y, float width, float height, float rotation, Color color) {
        filledRectangle(x, y, width, height, Angle.ofDegrees(rotation), color);
    }

    default void filledRectangle(float x, float y, float width, float height, Color color) {
        filledRectangle(x, y, width, height, Angle.DEFAULT_ROTATION_IN_ANGLES, color);
    }

    default void filledRectangle(float x, float y, float width, float height, float rotation) {
        filledRectangle(x, y, width, height, rotation, getColor());
    }

    default void filledRectangle(float x, float y, float width, float height) {
        filledRectangle(x, y, width, height, Angle.DEFAULT_ROTATION_IN_ANGLES, getColor());
    }

    default void filledRectangle(Rectangle rectangle, Color color) {
        filledRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), color);
    }

    default void filledRectangle(Rectangle rectangle, float rotation) {
        filledRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), rotation);
    }

    default void filledRectangle(Rectangle rectangle) {
        filledRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }
    void setTextureRegion(TextureRegion textureRegion);
    TextureRegion getTextureRegion();
    
    void setTexture(Texture texture);

}
