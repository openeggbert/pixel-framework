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
package com.openeggbert.pixel.framework;

import com.openeggbert.pixel.framework.interfaces.PixelBackend;
import com.openeggbert.pixel.framework.interfaces.Files;
import com.openeggbert.pixel.framework.interfaces.Audio;
import com.openeggbert.pixel.framework.interfaces.Graphics;
import com.openeggbert.pixel.framework.interfaces.Input;
import com.openeggbert.pixel.framework.interfaces.Internal;
import com.openeggbert.pixel.framework.interfaces.Net;
import com.openeggbert.pixel.framework.interfaces.Utils;
import com.openeggbert.pixel.framework.interfaces.App;
import com.openeggbert.pixel.framework.interfaces.Extension;

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
        return extension().getOrDefault(App.class, get().app());
    }

    public static Graphics graphics() {
        return extension().getOrDefault(Graphics.class, get().graphics());
    }

    public static Audio audio() {
        return extension().getOrDefault(Audio.class, get().audio());
    }

    public static Input input() {
        return extension().getOrDefault(Input.class, get().input());
    }

    public static Net net() {
        return extension().getOrDefault(Net.class, get().net());
    }

    public static Files files() {
        return extension().getOrDefault(Files.class, get().files());
    }

    public static Utils utils() {
        return extension().getOrDefault(Utils.class, get().utils());
    }

    public static Internal internal() {
        return extension().getOrDefault(Internal.class, get().internal());
    }

    public static Extension extension() {
        return get().extension();
    }

    public static <T> T get(Class<T> clazz) {
        return get().get(clazz);
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
