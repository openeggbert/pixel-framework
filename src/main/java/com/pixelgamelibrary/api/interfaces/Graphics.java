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

import com.pixelgamelibrary.api.graphics.Cursor;
import com.pixelgamelibrary.api.graphics.Monitor;
import java.util.List;
import com.pixelgamelibrary.api.graphics.Pixmap;

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
    void setCursor (Cursor cursor);
    float getDeltaTime ();
    void setTargetFPS();
    int getTargetFPS();
    
}
