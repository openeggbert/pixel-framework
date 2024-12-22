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

import com.openeggbert.pixel.framework.PixelFeature;
import com.openeggbert.pixel.framework.Platform;
import com.openeggbert.pixel.framework.game.Game;
import com.openeggbert.pixel.framework.app.ClipBoard;
import com.openeggbert.pixel.framework.app.LogLevel;
import com.openeggbert.pixel.framework.app.Preferences;

/**
 *
 * @author robertvokac
 */
public interface App {
    String DEFAULT_TAG = "APP";
    Platform getPlatform();
      default boolean isPlatforms(Platform platform) {
        return getPlatform() == platform;
    }  
    default boolean isOneOfPlatforms(Platform ... platforms) {
        for(Platform p: platforms) {
            if(getPlatform() == p) {
                return true;
            }
        }
        return false;
    }
    void exit();

    void fatal(String tag, String msg);

    void error(String tag, String msg);

    void log(String tag, String msg);

    void warn(String tag, String msg);

    void debug(String tag, String msg);

    void trace(String tag, String msg);

    default void fatal(String msg) {
        fatal(DEFAULT_TAG, msg);
    }

    default void error(String msg) {
        error(DEFAULT_TAG, msg);
    }

    default void log(String msg) {
        log(DEFAULT_TAG, msg);
    }

    default void warn(String msg) {
        warn(DEFAULT_TAG, msg);
    }

    default void debug(String msg) {
        debug(DEFAULT_TAG, msg);
    }

    default void trace(String msg) {
        trace(DEFAULT_TAG, msg);
    }

    
    void setLogLevel(LogLevel logLevel);
    Preferences getPreferences(String preferencesName);
    void setAppName(String appName);
    String getAppName();
    boolean isAppNameSet();
    
    void setGame(Game game);

    Game getGame();
    default boolean isFeatureEnabled(PixelFeature feature) {
        return isFeatureEnabled(feature.name());
    }
    boolean isFeatureEnabled(String feature);
    boolean isMobileDevice();
    void runLater(Runnable runnable);
    ClipBoard getClipBoard();
}
