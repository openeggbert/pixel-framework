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
package com.openeggbert.pixel.framework.audio;

/**
 * The Music interface represents a longer audio track that can be controlled with playback functions.
 * It provides methods for playing, pausing, stopping, and controlling playback settings like looping, volume, and panning.
 * This interface allows for precise manipulation of the music playback within a game or application.
 * 
 * Implementations of this interface should handle music resources, and the {@link #dispose()} method
 * should be called when the music is no longer needed to free up resources.
 * 
 * @author robertvokac
 */
public interface Music {

    /**
     * Starts or resumes playback of the music track.
     */
    public void play();

    /**
     * Pauses the playback of the music track.
     */
    public void pause();

    /**
     * Stops the playback of the music track and resets its position to the beginning.
     */
    public void stop();

    /**
     * Checks if the music track is currently playing.
     * @return true if the music is playing, false otherwise.
     */
    public boolean isPlaying();

    /**
     * Sets whether the music track should loop when it reaches the end.
     * @param isLooping true to loop the music, false to play it once.
     */
    public void setLooping(boolean isLooping);

    /**
     * Checks if the music track is set to loop.
     * @return true if looping is enabled, false otherwise.
     */
    public boolean isLooping();

    /**
     * Sets the volume of the music playback.
     * @param volume the volume level in the range [0, 1].
     */
    public void setVolume(float volume);

    /**
     * Gets the current volume of the music playback.
     * @return the volume level in the range [0, 1].
     */
    public float getVolume();

    /**
     * Sets the panning and volume for the music playback.
     * Panning works in the range -1 (left) to 1 (right), with 0 being the center.
     * @param pan panning in the range -1 to 1.
     * @param volume the volume level in the range [0, 1].
     */
    public void setPan(float pan, float volume);

    /**
     * Sets the playback position of the music track.
     * @param position the playback position in seconds.
     */
    public void setPosition(float position);

    /**
     * Gets the current playback position of the music track.
     * @return the playback position in seconds.
     */
    public float getPosition();

    /**
     * Releases all resources associated with this music instance.
     */
    public void dispose();

    /**
     * Sets a listener that will be notified when the music track finishes playing.
     * @param listener the listener to be notified upon completion.
     */
    public void setOnCompletionListener(OnCompletionListener listener);

    /**
     * The OnCompletionListener interface provides a callback method to notify when the music has finished playing.
     */
    public interface OnCompletionListener {
        
        /**
         * Invoked when the music track has finished playing.
         *
         * @param music the Music instance that has reached the end of the track
         */
        public abstract void onCompletion(Music music);
    }

}
