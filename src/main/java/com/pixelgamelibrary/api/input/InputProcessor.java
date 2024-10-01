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
package com.pixelgamelibrary.api.input;

/**
 *
 * @author robertvokac
 */
public interface InputProcessor {

	public boolean keyDown (int keycode);

	public boolean keyUp (int keycode);

	public boolean keyTyped (char character);

	public boolean touchDown (int screenX, int screenY, int pointer, int button);

	public boolean touchUp (int screenX, int screenY, int pointer, int button);

	public boolean touchCancelled (int screenX, int screenY, int pointer, int button);

	public boolean touchDragged (int screenX, int screenY, int pointer);

	public boolean mouseMoved (int screenX, int screenY);

	public boolean scrolled (float amountX, float amountY);
    
}
