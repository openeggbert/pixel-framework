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
package com.openeggbert.pixel.framework.interfaces;

import com.openeggbert.pixel.framework.graphics.BitmapFont;
import com.openeggbert.pixel.framework.graphics.BitmapFontFactory;
import com.openeggbert.pixel.framework.graphics.Cursor;
import com.openeggbert.pixel.framework.graphics.Monitor;
import java.util.List;
import com.openeggbert.pixel.framework.graphics.Pixmap;
import com.openeggbert.pixel.framework.graphics.SpriteBatch;
import com.openeggbert.pixel.framework.graphics.SpriteBatchFactory;
import com.openeggbert.pixel.framework.graphics.Texture;
import com.openeggbert.pixel.framework.graphics.TextureFactory;
import com.openeggbert.pixel.framework.graphics.TextureRegion;
import com.openeggbert.pixel.framework.files.File;

/**
 *
 * @author robertvokac
 */
public interface Graphics {

    List<Monitor> getMonitors();

    Monitor getMonitor();

    Monitor getPrimaryMonitor();

    String getTitle();

    void setTitle(String title);

    Cursor newCursor(Pixmap pixMap, int x, int y);

    default Cursor newCursor(Pixmap pixMap) {
        Monitor monitor = getPrimaryMonitor();
        return newCursor(pixMap, monitor.getVirtualWidth() / 2, monitor.getVirtualWidth() / 2);
    }

    void setCursor(Cursor cursor);

    float getDeltaTime();

    void setTargetFPS();

    int getTargetFPS();

    TextureFactory getTextureFactory();

    default Texture newTexture(String assetPath) {
        return getTextureFactory().create(assetPath);
    }

    default Texture newTexture(File fileHandle) {
        return getTextureFactory().create(fileHandle);
    }

    default Texture newTexture(Pixmap pixmap) {
        return getTextureFactory().create(pixmap);
    }

    default Texture newTexture(int width, int height) {
        return getTextureFactory().create(width, height);
    }

    SpriteBatchFactory getSpriteBatchFactory();

    default SpriteBatch newSpriteBatch() {
        return getSpriteBatchFactory().create();
    }

    BitmapFontFactory getBitmapFontFactory();

    default BitmapFont newBitmapFont() {
        return getBitmapFontFactory().create();
    }

    default BitmapFont newBitmapFont(boolean flip) {
        return getBitmapFontFactory().create(flip);
    }

    default BitmapFont newBitmapFont(File fontFile, TextureRegion region) {
        return getBitmapFontFactory().create(fontFile, region);
    }

    default BitmapFont newBitmapFont(File fontFile, TextureRegion region, boolean flip) {
        return getBitmapFontFactory().create(fontFile, region, flip);
    }

    default BitmapFont newBitmapFont(File fontFile) {
        return getBitmapFontFactory().create(fontFile);
    }

    default BitmapFont newBitmapFont(File fontFile, boolean flip) {
        return getBitmapFontFactory().create(fontFile, flip);
    }

    default BitmapFont newBitmapFont(File fontFile, File imageFile, boolean flip) {
        return getBitmapFontFactory().create(fontFile, imageFile, flip);
    }

}
