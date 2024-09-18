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

import static com.pixelgamelibrary.api.storage.RegularFileType.TEXT;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robertvokac
 */
public class FileHandleImpl implements FileHandle {

    private final Storage storage;
    private final String path;
    private String name;

    public FileHandleImpl(Storage storage, String path) {
        this.storage = storage;
        this.path = path.equals(".") ? storage.printWorkingDirectory() : path;
        {
            if (path.equals("/")) {
                name = path;
            } else {
                String[] array = path.split("/");
                name = array[array.length - 1];
            }
        }
    }

    @Override
    public FileType type() {
        return storage.type(path);
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String extension() {
        String extension = "";

        int i = name.lastIndexOf('.');
        if (i > 0) {
            extension = name.substring(i + 1);
        }
        return extension;
    }

    @Override
    public String nameWithoutExtension() {
        return name.substring(0, name.length() - extension().length() -1);
    }

    @Override
    public List<FileHandle> list() {
        List<String> list = storage.list(path);
        List<FileHandle> files = new ArrayList<>();
        for(String s:list) {
            files.add(new FileHandleImpl(storage, s));
        }
        return files;
    }

    @Override
    public FileHandle child(String name) {
        return new FileHandleImpl(storage, path + "/" + name);
    }

    @Override
    public FileHandle sibling(String siblingName) {
        int nameLength = name.length();
        String f = path.substring(0, path.length() - nameLength - 1) + "/" + siblingName;
        
        return new FileHandleImpl(storage, f);
    }

    @Override
    public FileHandle parent() {
        return new FileHandleImpl(storage, path.substring(0, path.length() - name.length() - 1));
    }

    @Override
    public boolean mkdir() {
        return storage.createDirectory(path).isEmpty();
    }

    @Override
    public boolean mkdirs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean exists() {
        return storage.exists(path);
    }

    @Override
    public boolean delete() {
        return storage.remove(path);
    }

    @Override
    public boolean deleteDirectory() {
        return storage.removeDirectory(path);
    }

    @Override
    public boolean emptyDirectory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean copyTo(FileHandle destination) {
        return storage.copy(path, destination.path()).isEmpty();
    }

    @Override
    public boolean moveTo(FileHandle destination) {
        return storage.move(path, destination.path()).isEmpty();
    }

    @Override
    public long length() {
        if(isDirectory()) {
            return 0;
        }
        RegularFileType rft = storage.getRegularFileType(path);
        switch(rft){
            case TEXT: return readString().length();
            case BINARY: return readBytes().length;
            default: throw new UnsupportedOperationException("Unsupported RegularFileType: " + rft);
        }
    }

    @Override
    public FileHandle tempFile(String prefix) {
        createTmpDirectoryIfDoesNotYetExist();
        String r = createRandomName();
        storage.touch(r);
        return new FileHandleImpl(storage, "/tmp/"+r);
    }
 
    private String createRandomName() {
        return
                String.valueOf((int)(Math.random() * 1000000)) +
                String.valueOf((int)(Math.random() * 1000000)) +
                String.valueOf((int)(Math.random() * 1000000));
    }

    private void createTmpDirectoryIfDoesNotYetExist() {
        if(!storage.exists("/tmp")) {
            storage.createDirectory("/tmp");
        }
    }

    @Override
    public FileHandle tempDirectory(String prefix) {
        createTmpDirectoryIfDoesNotYetExist();
        
        String r = createRandomName();
        storage.createDirectory(r);
        return new FileHandleImpl(storage, "/tmp/"+r);
    }

    @Override
    public int depth() {
        return storage.depth(path);
    }

    @Override
    public boolean writeString(String text) {
        return storage.writeString(path, text).isEmpty();
    }

    @Override
    public boolean appendString(String text
    ) {
        String textCurrent = readString();
        return storage.writeString(path, textCurrent + text).isEmpty();
    }

    @Override
    public String readString() {
        return storage.readString(path);
    }

    @Override
    public boolean writeBytes(byte[] data
    ) {
        return storage.writeBytes(path, data).isEmpty();
    }

    @Override
    public byte[] readBytes() {
        return storage.readBytes(path);
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Storage getStorage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
