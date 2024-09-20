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
 *
 * @author robertvokac
 */
public interface SpriteBatch {

//https://libgdx.com/wiki/graphics/2d/spritebatch-textureregions-and-sprites
//draw (Texture texture, float x, float y)  Draws the texture using the texture's width and height.
//draw (Texture texture, float x, float y, int srcX, int srcY, int srcWidth, int srcHeight) Draws a portion of the texture.
//draw (Texture texture, float x, float y, float width, float height, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)    Draws a portion of a texture, stretched to the width and height, and optionally flipped.
//draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)  This monster method draws a portion of a texture, stretched to the width and height, scaled and rotated around an origin, and optionally flipped.
//draw (Texture texture, float x, float y, float width, float height, float u, float v, float u2, float v2)   This draws a portion of a texture, stretched to the width and height. This is a somewhat advanced method as it uses texture coordinates from 0-1 rather than pixel coordinates.
//draw (Texture texture, float[] spriteVertices, int offset, int length)  This is an advanced method for passing in the raw geometry, texture coordinates, and color information. This can be used to draw any quadrilateral, not just rectangles.

}
