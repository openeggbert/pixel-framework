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

import com.pixelgamelibrary.api.storage.FileHandle;
import com.pixelgamelibrary.api.storage.Storage;
import com.pixelgamelibrary.api.storage.StorageException;
import com.pixelgamelibrary.api.storage.StorageType;
import static com.pixelgamelibrary.api.storage.StorageType.ASSETS;
import static com.pixelgamelibrary.api.storage.StorageType.EXTERNAL;

/**
 *
 * @author robertvokac
 */
public interface Files {

    Storage assets();
    
    Storage local();

    Storage external();

    Storage relative();

    Storage absolute();

    Storage tmp();
    
    default FileHandle assetsFile(String path) {
        return assets().file(path); 
    }
    default FileHandle localFile(String path) {
        return local().file(path); 
    }
    default FileHandle externalFile(String path) {
        return external().file(path); 
    }
    default FileHandle relativeFile(String path) {
        return relative().file(path); 
    }
    default FileHandle absoluteFile(String path) {
        return absolute().file(path); 
    }
    default FileHandle tmpFile(String path) {
        return tmp().file(path); 
    }
    
    

    default FileHandle file​(java.lang.String path, StorageType type) {
        switch (type) {
            case ASSETS:
                return assets().file(path);
            case LOCAL:
                return local().file(path);
            case EXTERNAL:
                return external().file(path);
            case RELATIVE:
                return relative().file(path);
            case ABSOLUTE:
                return absolute().file(path);
            case TMP:
                return tmp().file(path);
            default:
                throw new StorageException("Unsupported StorageType: " + type);
        }
    }

    boolean isExternalStorageAvailable();

    String getLocalStoragePath();

    String getExternalStoragePath();
}
