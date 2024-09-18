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

package com.pixelgamelibrary.api.game;

import com.pixelgamelibrary.api.OnSetScreenListener;
import com.pixelgamelibrary.api.screen.Screen;

/**
 *
 * @author robertvokac
 */
public class GameAdapter implements Game {

    private Screen screen;
    private OnSetScreenListener setOnSetScreenListener;
    @Override
    public void create() {
        
    }

    @Override
    public void setScreen(Screen screen) {
        this.screen = screen;
        System.out.println("setOnSetScreenListener=" + setOnSetScreenListener);
        if(setOnSetScreenListener != null) {
        setOnSetScreenListener.onSetScreen(screen);
        }
        System.out.println("GameAdapter:setScreen");
    }

    @Override
    public Screen getScreen() {
        return screen;
    }

    @Override
    public void render() {
        
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void show() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
        
    }

    @Override
    public void setOnSetScreenListener(OnSetScreenListener setScreenListener) {
        this.setOnSetScreenListener = setScreenListener;
    }
    
}
