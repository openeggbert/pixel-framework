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

package com.openeggbert.pixel.framework.game;

import com.openeggbert.pixel.framework.OnSetScreenListener;
import com.openeggbert.pixel.framework.screen.Screen;


/**
 *
 * @author robertvokac
 */
public class GameWrapper implements Game {

    private final Game game;

    public GameWrapper(Game gameI) {
        this.game = gameI;
    }

    @Override
    public void create() {
        game.create();
    }

    @Override
    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }

    @Override
    public Screen getScreen() {
        return game.getScreen();
    }

    @Override
    public void render() {
        game.render();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void show() {
        game.show();
    }

    @Override
    public void hide() {
        game.hide();
    }

    @Override
    public void pause() {
        game.pause();
    }

    @Override
    public void resume() {
        game.resume();
    }

    @Override
    public void dispose() {
        game.dispose();
    }

    @Override
    public void setOnSetScreenListener(OnSetScreenListener setScreenListener) {
        game.setOnSetScreenListener(setScreenListener);
    }
}
