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
package com.pixelgamelibrary.api.interfaces;

import com.pixelgamelibrary.api.PixelException;
import com.pixelgamelibrary.api.extension.ExtensionImpl;

/**
 *
 * @author robertvokac
 */
public interface PixelBackend {

    default String name() {
        return getClass().getSimpleName();
    }
    App app();
    Graphics graphics();
    Audio audio();
    Input input();
    //
    Net net();
    Files files();
    Utils utils();
    Internal internal();
    //
    default Extension extension() {
        return ExtensionImpl.getInstance();
    }

    default <T> T get(Class<T> clazz) {
        if(clazz.equals(App.class)) {return (T) app();}
        if(clazz.equals(Graphics.class)) {return (T) graphics();}
        if(clazz.equals(Audio.class)) {return (T) audio();}
        if(clazz.equals(Input.class)) {return (T) input();}
        //
        if(clazz.equals(Net.class)) {return (T) net();}
        if(clazz.equals(Files.class)) {return (T) files();}
        if(clazz.equals(Utils.class)) {return (T) utils();}
        if(clazz.equals(Internal.class)) {return (T) internal();}
        //
        if(clazz.equals(Extension.class)) {return (T) extension();}
        throw new PixelException("Unsupported interface: " + clazz.getName());
    }

}
