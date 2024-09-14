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

import com.pixelgamelibrary.api.Platform;
import java.util.List;

/**
 * This interface provides the methods to interact with the underlying storage system.
 * It supports basic file system operations such as navigating directories, creating files
 * and directories, and reading/writing data.
 * 
 * @author robertvokac
 */
public interface Storage {

    public String SLASH = "/";

    /**
     * Returns the platform associated with this storage.
     * 
     * @return the platform object.
     */
    Platform getPlatform();

    /**
     * Changes the current working directory to the specified path.
     * 
     * @param path the path to change to.
     * @return a result message or an empty string if successful.
     */
    public String changeDirectory(String path);

    /**
     * Changes the directory to the default "home/user" directory, creating the necessary
     * directories if they do not exist.
     * 
     * @return a result message or an empty string if successful.
     */
    default String changeDirectory() {
        Storage.this.changeDirectory("/");
        createDirectory("home");
        Storage.this.changeDirectory("home");
        createDirectory(getUserName());
        Storage.this.changeDirectory(getUserName());
        return "";
    }

    /**
     * Creates a directory with the specified name.
     * 
     * @param argument the name of the directory to create.
     * @return a result message or an empty string if successful.
     */
    public String createDirectory(String argument);

    /**
     * Creates multiple directories specified by the arguments.
     * 
     * @param argument the names of the directories to create.
     * @return a result message or an empty string if successful.
     */
    default String createDirectories(String... argument) {
        if (argument.length == 0) {
            return "Missing argument";
        }
        for (String n : argument) {
            String result = createDirectory(n);
            if (!result.isEmpty()) {
                return result;
            }
        }
        return "";
    }

    /**
     * Returns the current working directory.
     * 
     * @return the path of the current working directory.
     */
    public String printWorkingDirectory();

    /**
     * Lists the contents of the specified directory.
     * 
     * @param workingDirectory the directory to list.
     * @return a list of file and directory names in the specified directory.
     */
    public List<String> list(String workingDirectory);

    /**
     * Lists the contents of the current working directory.
     * 
     * @return a list of file and directory names in the current working directory.
     */
    default List<String> list() {
        return list(printWorkingDirectory());
    }

    /**
     * Returns the depth of the specified directory path in the directory tree.
     * 
     * @param path the path to calculate depth for.
     * @return the depth of the path.
     */
    default int depth(String path) {
        // Return the depth of the given path
        String absolutePath = convertToAbsolutePathIfNeeded(path);
        if (absolutePath.equals(SLASH)) {
            return 0;
        }
        String[] array = absolutePath.split(SLASH);
        return array.length - 1;
    }

    /**
     * Returns the depth of the current working directory in the directory tree.
     * 
     * @return the depth of the current working directory.
     */
    default int depth() {
        return depth(printWorkingDirectory());
    }

    /**
     * Creates an empty file with the specified name.
     * 
     * @param name the name of the file to create.
     * @return a result message or an empty string if successful.
     */
    public String touch(String name);

    /**
     * Removes the file with the specified name.
     * 
     * @param name the name of the file to remove.
     * @return true if the file was successfully removed, false otherwise.
     */
    public boolean remove(String name);

    /**
     * Removes the directory with the specified name.
     * 
     * @param dirname the name of the directory to remove.
     * @return true if the directory was successfully removed, false otherwise.
     */
    public boolean removeDirectory(String dirname);

    /**
     * Copies a file from the source path to the target path.
     * 
     * @param source the source file path.
     * @param target the target file path.
     * @return a result message or an empty string if successful.
     */
    public String copy(String source, String target);

    /**
     * Moves a file from the source path to the target path.
     * 
     * @param source the source file path.
     * @param target the target file path.
     * @return a result message or an empty string if successful.
     */
    public String move(String source, String target);

    /**
     * Reads the contents of a text file with the specified name.
     * 
     * @param name the name of the file to read.
     * @return the text content of the file.
     */
    public String readString(String name);

    /**
     * Reads the contents of a binary file with the specified name.
     * 
     * @param name the name of the file to read.
     * @return the binary content of the file.
     */
    public byte[] readBytes(String name);

    /**
     * Saves the specified text content to a file with the given name.
     * 
     * @param name the name of the file to save.
     * @param text the text content to save.
     * @return a result message or an empty string if successful.
     */
    public String writeString(String name, String text);

    /**
     * Saves the specified binary data to a file with the given name.
     * 
     * @param name the name of the file to save.
     * @param data the binary data to save.
     * @return a result message or an empty string if successful.
     */
    public String writeBytes(String name, byte[] data);

    /**
     * Checks whether a file or directory with the specified name exists.
     * 
     * @param name the name to check for existence.
     * @return true if the file or directory exists, false otherwise.
     */
    public boolean exists(String name);

    /**
     * Checks whether the specified name refers to a file.
     * 
     * @param name the name to check.
     * @return true if the name refers to a file, false otherwise.
     */
    public boolean isFile(String name);

    /**
     * Checks whether the specified name refers to a directory.
     * 
     * @param name the name to check.
     * @return true if the name refers to a directory, false otherwise.
     */
    public boolean isDirectory(String name);

    /**
     * Returns a debug string with information about the current state of the storage.
     * 
     * @return a debug string.
     */
    public String debug();

    /**
     * Flushes any pending writes to the storage.
     */
    public void flush();

    /**
     * Returns the username associated with this storage.
     * 
     * @return the username.
     */
    default String getUserName() {
        return USER;
    }

    /**
     * If the size of this storage is limited, returns the number of bytes it is limited to.
     * Otherwise, returns 0.
     * 
     * @return the size limit in bytes, or 0 if there is no limit.
     */
    default long getSizeLimit() {
        return 0;
    }

    /**
     * The default username for the storage.
     */
    static final String USER = "user";
    
    default FileHandle file(String path) {
        path = convertToAbsolutePathIfNeeded(path);
        return new FileHandleImpl(this, path);
    }
    default FileHandle file() {
        return file(printWorkingDirectory());
    }
    
    FileType type(String path);
    
    RegularFileType getRegularFileType(String path);
    
     
    /**
     * Converts a path to an absolute path if it is not already absolute.
     *
     * @param path the path to convert
     * @return the absolute path
     */
    default String convertToAbsolutePathIfNeeded(String path) {
        if (path.startsWith(SLASH)) {
            return path;
        }
        return printWorkingDirectory() + (printWorkingDirectory().equals("/") ? "" : SLASH) + path;
    }
    default boolean isTextFile(String content) {
        try {
            
            // Check if the content contains any non-printable characters
            for (int i = 0; i < content.length(); i++) {
                char c = content.charAt(i);
                // In GWT, use a simpler check for control characters
                if (c < 32 && !Character.isWhitespace(c)) {
                    return false; // Likely a binary file due to control characters
                }
            }
            return true;
        } catch (Exception e) {
            // If there's an exception while reading the file as a string, assume it's binary
            return false;
        }
    }
}
