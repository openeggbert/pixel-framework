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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An implementation of SimpleMap using a HashMap for internal storage.
 * This class provides basic operations for storing and retrieving key-value pairs.
 * It implements the SimpleMap interface.
 * 
 * @author robertvokac
 */
public class SimpleJavaMap implements SimpleMap {

    // Internal map for storing key-value pairs
    private final Map<String, String> map;

    /**
     * Constructs a SimpleJavaMap instance with an empty HashMap.
     */
    public SimpleJavaMap() {
        this(new HashMap<>());
    }

    /**
     * Constructs a SimpleJavaMap instance with a provided map.
     * 
     * @param mapIn Initial map to use for storage
     */
    public SimpleJavaMap(Map<String, String> mapIn) {
        this.map = mapIn;
    }

    /**
     * Puts a key-value pair into the map.
     * 
     * @param key Key to store
     * @param val Value to store
     */
    @Override
    public void putString(String key, String val) {
        map.put(key, val);
    }

    /**
     * Puts multiple key-value pairs into the map.
     * 
     * @param map Key-value pairs to store
     */
    @Override
    public void put(Map<String, String> map) {
        for (String key : map.keySet()) {
            putString(key, map.get(key));
        }
    }

    /**
     * Retrieves the value associated with the given key.
     * 
     * @param key Key to retrieve value for
     * @return Value associated with the key, or null if not found
     */
    @Override
    public String getString(String key) {
        return map.get(key);
    }

    /**
     * Retrieves the value associated with the given key, or returns a default value if not found.
     * 
     * @param key Key to retrieve value for
     * @param defaultValue Default value to return if key is not found
     * @return Value associated with the key, or default value if key is not found
     */
    @Override
    public String getString(String key, String defaultValue) {
        return contains(key) ? getString(key) : defaultValue;
    }

    /**
     * Retrieves an unmodifiable view of the map.
     * 
     * @return Unmodifiable map
     */
    @Override
    public Map<String, String> getReadOnlyMap() {
        return Collections.unmodifiableMap(map);
    }

    /**
     * Checks if the map contains the given key.
     * 
     * @param key Key to check
     * @return True if the key is present, false otherwise
     */
    @Override
    public boolean contains(String key) {
        return map.containsKey(key);
    }

    /**
     * Clears all key-value pairs from the map.
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * Removes the key-value pair associated with the given key.
     * 
     * @param key Key to remove
     */
    @Override
    public void remove(String key) {
        map.remove(key);
    }

    /**
     * No-op method for flushing the map. This implementation does nothing.
     */
    @Override
    public void flush() {
        //nothing to do
    }

    /**
     * Retrieves a list of all keys in the map.
     * 
     * @return List of keys
     */
    @Override
    public List<String> keyList() {
        return map.keySet().stream().collect(Collectors.toList());
    }
}
