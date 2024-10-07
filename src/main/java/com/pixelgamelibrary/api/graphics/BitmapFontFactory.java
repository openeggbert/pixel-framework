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

import com.pixelgamelibrary.api.storage.FileHandle;

/**
 *
 * @author robertvokac
 */
public interface BitmapFontFactory {

    boolean FLIP_DEFAULT = false;

    BitmapFont create(boolean flip);

    default BitmapFont create() {
        return create(FLIP_DEFAULT);
    }

    BitmapFont create(FileHandle fontFile, TextureRegion region, boolean flip);

    default BitmapFont create(FileHandle fontFile, TextureRegion region) {
        return create(fontFile, region, FLIP_DEFAULT);
    }

    BitmapFont create(FileHandle fontFile, boolean flip);

    default BitmapFont create(FileHandle fontFile) {
        return create(fontFile, FLIP_DEFAULT);
    }
//            Pixel.files().assets("com/badlogic/gdx/utils/lsans-15.fnt"), Pixel.files().assets("com/badlogic/gdx/utils/lsans-15.png"),
//			false, true
//        );

    BitmapFont create(FileHandle fontFile, FileHandle imageFile, boolean flip);

    default BitmapFont create(FileHandle fontFile, FileHandle imageFile) {
        return create(fontFile, imageFile, FLIP_DEFAULT);
    }

}
