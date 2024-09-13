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

import com.pixelgamelibrary.api.Platform;

/**
 * Implementation of Storage that uses an in-memory map for storing data.
 * Extends the MapStorage class to utilize a SimpleJavaMap for internal storage.
 * 
 * This class is used when you need a temporary storage solution that
 * does not persist data beyond the runtime of the application.
 * 
 * @author robertvokac
 */
public class MemoryStorage extends MapStorage {

    /**
     * Constructs a MemoryStorage instance using a SimpleJavaMap.
     * Initializes the parent MapStorage with an in-memory map implementation.
     */
    public MemoryStorage() {
        super(new SimpleJavaMap());
    }

    @Override
    public Platform getPlatform() {
        // Returns null as this implementation does not specify a platform
        return null;
    }
}
