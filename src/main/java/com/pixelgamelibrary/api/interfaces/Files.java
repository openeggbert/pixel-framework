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

import com.pixelgamelibrary.api.files.FileException;
import com.pixelgamelibrary.api.files.FileSystemType;
import static com.pixelgamelibrary.api.files.FileSystemType.ASSETS;
import static com.pixelgamelibrary.api.files.FileSystemType.EXTERNAL;
import com.pixelgamelibrary.api.files.FileSystem;
import com.pixelgamelibrary.api.files.File;
import static com.pixelgamelibrary.api.files.FileSystemType.ABSOLUTE;
import static com.pixelgamelibrary.api.files.FileSystemType.ASSETS;
import static com.pixelgamelibrary.api.files.FileSystemType.EXTERNAL;
import static com.pixelgamelibrary.api.files.FileSystemType.LOCAL;
import static com.pixelgamelibrary.api.files.FileSystemType.RELATIVE;
import static com.pixelgamelibrary.api.files.FileSystemType.TMP;

/**
 *
 * @author robertvokac
 */
public interface Files {

    FileSystem assetsFileSystem();
    
    FileSystem localFileSystem();

    FileSystem externalFileSystem();

    FileSystem relativeFileSystem();

    FileSystem absoluteFileSystem();

    FileSystem tmpFileSystem();
    
    default File assets(String path) {
        return assetsFileSystem().file(path); 
    }
    default File local(String path) {
        return localFileSystem().file(path); 
    }
    default File external(String path) {
        return externalFileSystem().file(path); 
    }
    default File relative(String path) {
        return relativeFileSystem().file(path); 
    }
    default File absolute(String path) {
        return absoluteFileSystem().file(path); 
    }
    default File tmp(String path) {
        return tmpFileSystem().file(path); 
    }
    
    default FileSystem fileSystem​(FileSystemType type) {
        switch (type) {
            case ASSETS:
                return assetsFileSystem();
            case LOCAL:
                return localFileSystem();
            case EXTERNAL:
                return externalFileSystem();
            case RELATIVE:
                return relativeFileSystem();
            case ABSOLUTE:
                return absoluteFileSystem();
            case TMP:
                return tmpFileSystem();
            default:
                throw new FileException("Unsupported FileSystemType: " + type);
        }
    }
        
    default File file​(java.lang.String path, FileSystemType type) {
        //return fileSystem(type).file(path);
        switch (type) {
            case ASSETS:
                return assetsFileSystem().file(path);
            case LOCAL:
                return localFileSystem().file(path);
            case EXTERNAL:
                return externalFileSystem().file(path);
            case RELATIVE:
                return relativeFileSystem().file(path);
            case ABSOLUTE:
                return absoluteFileSystem().file(path);
            case TMP:
                return tmpFileSystem().file(path);
            default:
                throw new FileException("Unsupported FileSystemType: " + type);
        }

    }

    boolean isExternalStorageAvailable();

    String getLocalStoragePath();

    String getExternalStoragePath();
}
