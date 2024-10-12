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

package com.pixelgamelibrary.api.files.map;

import com.pixelgamelibrary.api.files.FileType;
import com.pixelgamelibrary.api.files.StorageException;
import static com.pixelgamelibrary.api.files.FileType.DIRECTORY;
import static com.pixelgamelibrary.api.files.FileType.FILE;

/**
 * 
 * @author robertvokac
 */
public class MapFileType {
    private MapFileType() {
        //Not meant to be instantiated.
    }

    /**
     * Determines the MapFileType based on the value associated with the specified key in the map.
     * Throws a StorageException if the key is not found or if the value does not match any known type.
     * 
     * @param key the key whose associated value determines the file type
     * @param map the map from which to retrieve the value
     * @return the MapFileType corresponding to the value in the map
     * @throws StorageException if the key is not present in the map or if the value does not match FILE or DIRECTORY
     */
    public static FileType ofKey(String key, SimpleMap map) {
        // Check if the map contains the specified key
        if (!map.contains(key)) {
            throw new StorageException("Map does not contain key: " + key);
        }
        // Retrieve the value associated with the key
        String value = map.getString(key);
        if(value == null) {
            throw new StorageException("Value is null for key: " + key);
        }
        // Determine the MapFileType based on the value
        if (value.startsWith(FILE.name())) {
            return FILE;
        }
        if (value.startsWith(DIRECTORY.name())) {
            return DIRECTORY;
        }
        // Throw an exception if the value does not match known types
        throw new StorageException("Unsupported MapFileType for key in the map: " + key);
    }
}
