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

import com.pixelgamelibrary.api.ViewMode;
import java.util.List;

/**
 *
 * @author robertvokac
 */
public interface Monitor {

    String getName();
    
    int getVirtualWidth();

    int getVirtualHeight();

    MonitorMode getMonitorMode();

    List<MonitorMode> listSupportedMonitorModes();

    boolean setMonitorMode(MonitorMode monitorMode);

    boolean setToOriginalMonitorMode();

    default void setViewMode(ViewMode displayMode) {
        setViewMode(displayMode, 0, 0);
    }
    void setViewMode(ViewMode displayMode, int width, int height);

    default void setFullScreenMode() {
        setViewMode(ViewMode.FULLSCREEN);
    }

    default void setWindowedMode(int width, int height) {
        setViewMode(ViewMode.WINDOW, width, height);
    }

    default boolean isFullscreen() {
        return getViewMode() == ViewMode.FULLSCREEN;
    }

    default boolean isWindowed() {
        return getViewMode() == ViewMode.WINDOW;
    }
    
    ViewMode getViewMode();
    
    boolean isMonitorInUse();
    
    boolean isMonitorModeChangeSupported();
    int getDpi();


}
