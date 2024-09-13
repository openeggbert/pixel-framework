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

package com.pixelgamelibrary.api.storage.map;

import java.util.List;
import java.util.Map;

/**
 * Interface for a simple key-value map storage.
 * Provides methods for basic operations on a key-value map.
 * 
 * Implementations of this interface should define how key-value pairs
 * are stored and managed.
 * 
 * @author robertvokac
 */
public interface SimpleMap {

    /**
     * Stores a key-value pair in the map.
     * 
     * @param key Key to be stored
     * @param val Value to be associated with the key
     */
    public void putString(String key, String val);

    /**
     * Stores multiple key-value pairs in the map.
     * 
     * @param map Key-value pairs to be stored
     */
    public void put(Map<String, String> map);

    /**
     * Retrieves the value associated with the given key.
     * 
     * @param key Key whose associated value is to be returned
     * @return Value associated with the key, or null if the key is not found
     */
    public String getString(String key);

    /**
     * Retrieves the value associated with the given key, or returns a default value if the key is not found.
     * 
     * @param key Key whose associated value is to be returned
     * @param defaultValue Default value to return if the key is not found
     * @return Value associated with the key, or default value if the key is not found
     */
    public String getString(String key, String defaultValue);

    /**
     * Retrieves an unmodifiable view of the map.
     * 
     * @return Unmodifiable map
     */
    public Map<String, String> getReadOnlyMap();

    /**
     * Checks if the map contains the given key.
     * 
     * @param key Key to be checked
     * @return True if the key is present in the map, false otherwise
     */
    public boolean contains(String key);

    /**
     * Clears all key-value pairs from the map.
     */
    public void clear();

    /**
     * Removes the key-value pair associated with the given key.
     * 
     * @param key Key to be removed
     */
    public void remove(String key);

    /**
     * No-operation method for flushing the map. Implementations may use this method if needed.
     * This default implementation does nothing.
     */
    public void flush();

    /**
     * Retrieves a list of all keys present in the map.
     * 
     * @return List of keys in the map
     */
    public List<String> keyList();
}
