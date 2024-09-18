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
package com.pixelgamelibrary.api.storage;

import java.util.List;

/**
 *
 * @author robertvokac
 */
public interface FileHandle {

    FileType type();

    String path();

    String name();

    String extension();

    String nameWithoutExtension();

    List<FileHandle> list();

    default boolean isDirectory() {
        return type() == FileType.DIRECTORY;
    }

    default boolean isRegularFile() {
        return type() == FileType.FILE;
    }

    FileHandle child(String name);

    FileHandle sibling(String name);

    FileHandle parent();

    boolean mkdir();

    boolean mkdirs();

    boolean exists();

    boolean delete();

    boolean deleteDirectory();

    boolean emptyDirectory();

    boolean copyTo(FileHandle destination);

    boolean moveTo(FileHandle destination);

    long length();

    FileHandle tempFile(String prefix);

    FileHandle tempDirectory(String prefix);

    int depth();

    boolean writeString(String text);

    boolean appendString(String text);

    String readString();

    boolean writeBytes(byte[] data);

    byte[] readBytes();

    void flush();

    Storage getStorage();
}
