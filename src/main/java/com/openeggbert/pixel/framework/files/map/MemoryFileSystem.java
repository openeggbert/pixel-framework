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

package com.openeggbert.pixel.framework.files.map;

import com.openeggbert.pixel.framework.Platform;

/**
 * Implementation of FileSystem that uses an in-memory map for storing data.
 * Extends the MapFileSystem class to utilize a SimpleJavaMap for internal file system.
 * 
 * This class is used when you need a temporary file system solution that
 * does not persist data beyond the runtime of the application.
 * 
 * @author robertvokac
 */
public class MemoryFileSystem extends MapFileSystem {

    /**
     * Constructs a MemoryFileSystem instance using a SimpleJavaMap.
     * Initializes the parent MapFileSystem with an in-memory map implementation.
     */
    public MemoryFileSystem() {
        super(new SimpleJavaMap());
    }

    @Override
    public Platform getPlatform() {
        // Returns null as this implementation does not specify a platform
        return null;
    }
}
