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
package com.pixelgamelibrary.api;

import com.pixelgamelibrary.api.interfaces.PixelBackend;
import com.pixelgamelibrary.api.interfaces.Files;
import com.pixelgamelibrary.api.interfaces.Audio;
import com.pixelgamelibrary.api.interfaces.Graphics;
import com.pixelgamelibrary.api.interfaces.Input;
import com.pixelgamelibrary.api.interfaces.Internal;
import com.pixelgamelibrary.api.interfaces.Net;
import com.pixelgamelibrary.api.interfaces.Utils;
import com.pixelgamelibrary.api.interfaces.App;

/**
 *
 * @author robertvokac
 */
public class Pixel {

    private static PixelBackend INSTANCE = null;

    private Pixel() {
        //Not meant to be instantiated.
    }

    private static PixelBackend get() {
        return getBackend();
    }

    ////
    public static App app() {
        return get().app();
    }

    public static Graphics graphics() {
        return get().graphics();
    }

    public static Audio audio() {
        return get().audio();
    }

    public static Input input() {
        return get().input();
    }

    public static Net net() {
        return get().net();
    }

    public static Files files() {
        return get().files();
    }

    public static Utils utils() {
        return get().utils();
    }

    public static Internal internal() {
        return get().internal();
    }
    ////

    public static void initBackend(PixelBackend pixelBackend) {
        if (isBackendSet()) {
            throw new PixelException("Pixel Backend was already set");
        }
        INSTANCE = pixelBackend;
    }

    private static PixelBackend getBackend() {
        if (!isBackendSet()) {
            throw new PixelException("Pixel Backend was not yet set");
        }
        return INSTANCE;
    }

    public static boolean isBackendSet() {
        return INSTANCE != null;
    }
}
