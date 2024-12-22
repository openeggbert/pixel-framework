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
package com.openeggbert.pixel.framework.graphics;

import com.openeggbert.pixel.framework.Disposable;

/**
 *
 * @author robertvokac
 */
public interface BitmapFont extends Disposable {
    void setScale(float f);
    void setColor(Color color);
    default void setColor(float r, float g, float b, float a) {
        setColor(new Color(0f, 0f, 1f, 1f));
    }
    void draw(SpriteBatch batch, String text, float x, float y);
}
