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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author robertvokac
 */
public abstract class PixelApplication {
    abstract public GameI createGameViaMap(Map<String, Object> objects);
    
    public GameI createGame() {
        return createGame(new HashMap<>());
    }
    public GameI createGame(Object... objects) {
        Map<String, Object> map = new HashMap<>();
        int i = 0;
        int maxI = objects.length - 1;
        while(i<objects.length) {
            
        String key = (String)objects[i];
        i++;
        Object value = i > maxI ? null : objects[i];
        map.put(key, value);
        }
        
        return createGameViaMap(map);
    }
    
}
