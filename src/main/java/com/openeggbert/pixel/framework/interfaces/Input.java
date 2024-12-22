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

import com.openeggbert.pixel.framework.input.DeviceOrientation;
import com.openeggbert.pixel.framework.input.InputProcessor;
import com.openeggbert.pixel.framework.input.KeyboardKey;
import com.openeggbert.pixel.framework.input.MouseButton;
import com.openeggbert.pixel.framework.input.OnScreenKeyboardType;
import com.openeggbert.pixel.framework.input.Peripheral;
import com.openeggbert.pixel.framework.input.VibrationType;
import com.openeggbert.pixel.framework.math.Vector2;
import com.openeggbert.pixel.framework.math.Vector3;

/**
 *
 * @author robertvokac
 */
public interface Input {

    default void unlockCursor() {
        lockCursor(false);
    }

    default void lockCursor() {
        lockCursor(true);
    }

    default void lockCursor(boolean value) {
        setCursorLocked(value);
    }

    void setCursorLocked(boolean value);

    boolean isCursorLocked();

    void setCursorPosition(int x, int y);

    Vector3 getAccelerometerPosition();

    default float getAccelerometerX() {
        return getAccelerometerPosition().getX();
    }

    default float getAccelerometerY() {
        return getAccelerometerPosition().getY();
    }

    default float getAccelerometerZ() {
        return getAccelerometerPosition().getZ();
    }

    Vector3 getGyroscopPosition();

    default float getGyroscopX() {
        return getGyroscopPosition().getX();
    }

    default float getGyroscopY() {
        return getGyroscopPosition().getY();
    }

    default float getGyroscopZ() {
        return getGyroscopPosition().getZ();
    }

    int getMaxTouchPoints();

    Vector2 getCursorPosition();

    default float getCursorX() {
        return getCursorPosition().getX();
    }

    default float getCursorY() {
        return getCursorPosition().getY();
    }

    Vector2 getDeltaCursorPosition();

    default float getDeltaCursorX() {
        return getDeltaCursorPosition().getX();
    }

    default float getDeltaCursorY() {
        return getDeltaCursorPosition().getY();
    }

    Vector2 getDeltaCursorPosition(int touchPointNumber);

    default float getDeltaCursorX(int touchPointNumber) {
        return getDeltaCursorPosition(touchPointNumber).getX();
    }

    default float getDeltaCursorY(int touchPointNumber) {
        return getDeltaCursorPosition(touchPointNumber).getY();
    }

    boolean isTouched();

    boolean justTouched();

    boolean isTouched(int touchPointNumber);

    float getTouchPressure();

    float getTouchPressure(int pointer);

    boolean isMouseButtonPressed(MouseButton button);

    boolean isMouseButtonJustPressed(MouseButton button);

    boolean isKeyboardKeyPressed(KeyboardKey key);

    boolean isKeyboardKeyJustPressed(KeyboardKey key);

    void setOnscreenKeyboardVisible(boolean visible);

    void setOnscreenKeyboardVisible(boolean visible, OnScreenKeyboardType type);

    void vibrate(int milliseconds);

    void vibrate(int milliseconds, boolean fallback);

    void vibrate(int milliseconds, int amplitude, boolean fallback);

    void vibrate(VibrationType vibrationType);

    Vector3 getOrientationAngles();
    //pitch

    default float getOrientationAngleX() {
        return getOrientationAngles().getX();
    }
//roll

    default float getOrientationAngleY() {
        return getOrientationAngles().getY();
    }

    //azimuth
    default float getOrientationAngleZ() {
        return getOrientationAngles().getZ();
    }

    public void setInputProcessor(InputProcessor processor);

    public InputProcessor getInputProcessor();

    boolean isPeripheralAvailable(Peripheral peripheral);

    DeviceOrientation getDeviceOrientation();
}
