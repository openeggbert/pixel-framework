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
package com.pixelgamelibrary.api.interfaces;

import com.pixelgamelibrary.api.files.FileHandle;
import com.pixelgamelibrary.api.files.Storage;
import com.pixelgamelibrary.api.files.StorageException;
import com.pixelgamelibrary.api.files.StorageType;
import static com.pixelgamelibrary.api.files.StorageType.ASSETS;
import static com.pixelgamelibrary.api.files.StorageType.EXTERNAL;

/**
 *
 * @author robertvokac
 */
public interface Files {

    Storage assetsStorage();
    
    Storage localStorage();

    Storage externalStorage();

    Storage relativeStorage();

    Storage absoluteStorage();

    Storage tmpStorage();
    
    default FileHandle assets(String path) {
        return assetsStorage().file(path); 
    }
    default FileHandle local(String path) {
        return localStorage().file(path); 
    }
    default FileHandle external(String path) {
        return externalStorage().file(path); 
    }
    default FileHandle relative(String path) {
        return relativeStorage().file(path); 
    }
    default FileHandle absolute(String path) {
        return absoluteStorage().file(path); 
    }
    default FileHandle tmp(String path) {
        return tmpStorage().file(path); 
    }
    
    

    default FileHandle file​(java.lang.String path, StorageType type) {
        switch (type) {
            case ASSETS:
                return assetsStorage().file(path);
            case LOCAL:
                return localStorage().file(path);
            case EXTERNAL:
                return externalStorage().file(path);
            case RELATIVE:
                return relativeStorage().file(path);
            case ABSOLUTE:
                return absoluteStorage().file(path);
            case TMP:
                return tmpStorage().file(path);
            default:
                throw new StorageException("Unsupported StorageType: " + type);
        }
    }

    boolean isExternalStorageAvailable();

    String getLocalStoragePath();

    String getExternalStoragePath();
}
