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

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author robertvokac
 * @param <T>
 */
public class Animation<T> {

    T[] keyFrames;

    private float frameDuration;
    @Getter
    @Setter
    private AnimationMode animationMode = AnimationMode.NORMAL;

    public Animation(int frameDuration, Iterable<T> keyFrames) {

    }

    public Animation(int frameDuration, Iterable<T> keyFrames, AnimationMode animationMode) {

    }

    public Animation(int frameDuration, T... keyFrames) {

    }

    public Animation(int frameDuration, AnimationMode animationMode, T... keyFrames) {

    }

    public T getKeyFrame(long gameTime) {
        return null;//todo
    }

    public int getKeyFrameIndex(long gameTime) {

        return 0;//todo
    }

    public T[] getKeyFrames() {
        return null;//
    }

    public boolean isAnimationFinished(float gameTime) {
        return true;//todo
    }

    public void setFrameDuration(float frameDuration) {
    }

    public float getFrameDuration() {
        return 0;//todo
    }

    public float getAnimationDuration() {
        return 0;//todo
    }

}
