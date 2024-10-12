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
package com.pixelgamelibrary.api.files;

import static com.pixelgamelibrary.api.files.RegularFileType.TEXT;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robertvokac
 */
public class FileImpl implements File {

    private final FileSystem fs;
    private final String path;
    private String name;

    public FileImpl(FileSystem fsIn, String path) {
        this.fs = fsIn;
        this.path = path.equals(".") ? fsIn.printWorkingDirectory() : path;
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
        return fs.type(path);
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
    public List<File> list() {
        List<String> list = fs.list(path);
        List<File> files = new ArrayList<>();
        for(String s:list) {
            files.add(new FileImpl(fs, s));
        }
        return files;
    }

    @Override
    public File child(String name) {
        return new FileImpl(fs, path + "/" + name);
    }

    @Override
    public File sibling(String siblingName) {
        int nameLength = name.length();
        String f = path.substring(0, path.length() - nameLength - 1) + "/" + siblingName;
        
        return new FileImpl(fs, f);
    }

    @Override
    public File parent() {
        return new FileImpl(fs, path.substring(0, path.length() - name.length() - 1));
    }

    @Override
    public boolean mkdir() {
        return fs.createDirectory(path).isEmpty();
    }

    @Override
    public boolean mkdirs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean exists() {
        return fs.exists(path);
    }

    @Override
    public boolean delete() {
        return fs.remove(path);
    }

    @Override
    public boolean deleteDirectory() {
        return fs.removeDirectory(path);
    }

    @Override
    public boolean emptyDirectory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean copyTo(File destination) {
        return fs.copy(path, destination.path()).isEmpty();
    }

    @Override
    public boolean moveTo(File destination) {
        return fs.move(path, destination.path()).isEmpty();
    }

    @Override
    public long length() {
        if(isDirectory()) {
            return 0;
        }
        RegularFileType rft = fs.getRegularFileType(path);
        switch(rft){
            case TEXT: return readString().length();
            case BINARY: return readBytes().length;
            default: throw new UnsupportedOperationException("Unsupported RegularFileType: " + rft);
        }
    }

    @Override
    public File tempFile(String prefix) {
        createTmpDirectoryIfDoesNotYetExist();
        String r = createRandomName();
        fs.touch(r);
        return new FileImpl(fs, "/tmp/"+r);
    }
 
    private String createRandomName() {
        return
                String.valueOf((int)(Math.random() * 1000000)) +
                String.valueOf((int)(Math.random() * 1000000)) +
                String.valueOf((int)(Math.random() * 1000000));
    }

    private void createTmpDirectoryIfDoesNotYetExist() {
        if(!fs.exists("/tmp")) {
            fs.createDirectory("/tmp");
        }
    }

    @Override
    public File tempDirectory(String prefix) {
        createTmpDirectoryIfDoesNotYetExist();
        
        String r = createRandomName();
        fs.createDirectory(r);
        return new FileImpl(fs, "/tmp/"+r);
    }

    @Override
    public int depth() {
        return fs.depth(path);
    }

    @Override
    public boolean writeString(String text) {
        return fs.writeString(path, text).isEmpty();
    }

    @Override
    public boolean appendString(String text
    ) {
        String textCurrent = readString();
        return fs.writeString(path, textCurrent + text).isEmpty();
    }

    @Override
    public String readString() {
        return fs.readString(path);
    }

    @Override
    public boolean writeBytes(byte[] data
    ) {
        return fs.writeBytes(path, data).isEmpty();
    }

    @Override
    public byte[] readBytes() {
        return fs.readBytes(path);
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FileSystem getFileSystem() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
