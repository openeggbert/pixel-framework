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
package com.pixelgamelibrary.api.audio;

import com.pixelgamelibrary.api.Disposable;

/**
 * The Sound interface represents a short audio clip that can be played and controlled in various ways.
 * It provides methods for playback, looping, volume control, pitch adjustment, and panning.
 * 
 * The sound clip can be played multiple times concurrently and requires manual disposal
 * to free up resources once it is no longer needed.
 * 
 * This interface extends Disposable, so the {@link #dispose()} method should be called
 * when the sound instance is no longer needed.
 * 
 * @author robertvokac
 */
public interface Sound extends Disposable {

    /**
     * Plays the sound. If the sound is already playing, it will be played again concurrently.
     * @return the ID of the sound instance if successful, or -1 on failure.
     */
    public long play();

    /**
     * Plays the sound with the specified volume.
     * @param volume the volume level in the range [0, 1]
     * @return the ID of the sound instance if successful, or -1 on failure.
     */
    public long play(float volume);

    /**
     * Plays the sound with specified volume, pitch, and pan.
     * @param volume the volume level in the range [0, 1]
     * @param pitch the pitch multiplier, where 1 is the default, &gt;1 is faster, &lt;1 is slower
     * @param pan panning in the range -1 (left) to 1 (right), 0 is center
     * @return the ID of the sound instance if successful, or -1 on failure.
     */
    public long play(float volume, float pitch, float pan);

    /**
     * Plays the sound in a loop. The sound will continue playing until stopped manually.
     * @return the ID of the sound instance if successful, or -1 on failure.
     */
    public long loop();

    /**
     * Plays the sound in a loop with the specified volume.
     * @param volume the volume level in the range [0, 1]
     * @return the ID of the sound instance if successful, or -1 on failure.
     */
    public long loop(float volume);

    /**
     * Plays the sound in a loop with specified volume, pitch, and pan.
     * @param volume the volume level in the range [0, 1]
     * @param pitch the pitch multiplier, where 1 is the default, &gt;1 is faster, &lt;1 is slower
     * @param pan panning in the range -1 (left) to 1 (right), 0 is center
     * @return the ID of the sound instance if successful, or -1 on failure.
     */
    public long loop(float volume, float pitch, float pan);

    /**
     * Stops playing all instances of this sound.
     */
    public void stop();

    /**
     * Pauses all instances of this sound.
     */
    public void pause();

    /**
     * Resumes all paused instances of this sound.
     */
    public void resume();

    /**
     * Disposes of the sound and releases any system resources associated with it.
     */
    @Override
    public void dispose();

    /**
     * Stops the sound instance with the specified ID.
     * @param soundId the ID of the sound instance to stop
     */
    public void stop(long soundId);

    /**
     * Pauses the sound instance with the specified ID.
     * @param soundId the ID of the sound instance to pause
     */
    public void pause(long soundId);

    /**
     * Resumes the sound instance with the specified ID.
     * @param soundId the ID of the sound instance to resume
     */
    public void resume(long soundId);

    /**
     * Sets whether the sound instance with the specified ID should loop.
     * @param soundId the ID of the sound instance
     * @param looping true to loop the sound, false to stop looping
     */
    public void setLooping(long soundId, boolean looping);

    /**
     * Sets the pitch for the sound instance with the specified ID.
     * @param soundId the ID of the sound instance
     * @param pitch the pitch multiplier, where 1 is the default, &gt;1 is faster, &lt;1 is slower
     */
    public void setPitch(long soundId, float pitch);

    /**
     * Sets the volume for the sound instance with the specified ID.
     * @param soundId the ID of the sound instance
     * @param volume the volume level in the range [0, 1]
     */
    public void setVolume(long soundId, float volume);

    /**
     * Sets the panning and volume for the sound instance with the specified ID.
     * Panning only works for mono sounds.
     * @param soundId the ID of the sound instance
     * @param pan panning in the range -1 (left) to 1 (right), 0 is center
     * @param volume the volume level in the range [0, 1]
     */
    public void setPan(long soundId, float pan, float volume);

}
