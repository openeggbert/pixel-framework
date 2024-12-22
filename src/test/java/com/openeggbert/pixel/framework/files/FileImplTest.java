package com.openeggbert.pixel.framework.files;

import com.openeggbert.pixel.framework.files.FileImpl;
import com.openeggbert.pixel.framework.files.RegularFileType;
import com.openeggbert.pixel.framework.files.FileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.openeggbert.pixel.framework.files.FileSystem;
import com.openeggbert.pixel.framework.files.File;

public class FileImplTest {

    private FileSystem mockFileSystem;
    private FileImpl fileHandle;

    @BeforeEach
    public void setUp() {
        mockFileSystem = mock(FileSystem.class);
        fileHandle = new FileImpl(mockFileSystem, "/example/path/file.txt");
    }

    @Test
    public void testType() {
        // Arrange
        when(mockFileSystem.type("/example/path/file.txt")).thenReturn(FileType.FILE);

        // Act
        FileType result = fileHandle.type();

        // Assert
        assertEquals(FileType.FILE, result);
    }

    @Test
    public void testPath() {
        assertEquals("/example/path/file.txt", fileHandle.path());
    }

    @Test
    public void testName() {
        assertEquals("file.txt", fileHandle.name());
    }

    @Test
    public void testExtension() {
        assertEquals("txt", fileHandle.extension());
    }

    @Test
    public void testNameWithoutExtension() {
        assertEquals("file", fileHandle.nameWithoutExtension());
    }

    @Test
    public void testList() {
        // Arrange
        when(mockFileSystem.list("/example/path/file.txt")).thenReturn(Arrays.asList("child1", "child2"));

        // Act
        List<File> files = fileHandle.list();

        // Assert
        assertEquals(2, files.size());
        assertEquals("child1", files.get(0).name());
        assertEquals("child2", files.get(1).name());
    }

    @Test
    public void testChild() {
        // Act
        File child = fileHandle.child("child.txt");

        // Assert
        assertEquals("/example/path/file.txt/child.txt", child.path());
    }

    @Test
    public void testSibling() {
        // Act
        File sibling = fileHandle.sibling("sibling.txt");

        // Assert
        assertEquals("/example/path/sibling.txt", sibling.path());
    }

    @Test
    public void testParent() {
        // Act
        File parent = fileHandle.parent();

        // Assert
        assertEquals("/example/path", parent.path());
    }

    @Test
    public void testMkdir() {
        // Arrange
        when(mockFileSystem.createDirectory("/example/path/file.txt")).thenReturn("");

        // Act
        boolean result = fileHandle.mkdir();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testExists() {
        // Arrange
        when(mockFileSystem.exists("/example/path/file.txt")).thenReturn(true);

        // Act
        boolean result = fileHandle.exists();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testDelete() {
        // Arrange
        when(mockFileSystem.remove("/example/path/file.txt")).thenReturn(true);

        // Act
        boolean result = fileHandle.delete();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCopyTo() {
        // Arrange
        FileImpl destination = new FileImpl(mockFileSystem, "/destination/path");
        when(mockFileSystem.copy("/example/path/file.txt", "/destination/path")).thenReturn("");

        // Act
        boolean result = fileHandle.copyTo(destination);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMoveTo() {
        // Arrange
        FileImpl destination = new FileImpl(mockFileSystem, "/destination/path");
        when(mockFileSystem.move("/example/path/file.txt", "/destination/path")).thenReturn("");

        // Act
        boolean result = fileHandle.moveTo(destination);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testLength() {
        // Arrange
        when(mockFileSystem.getRegularFileType("/example/path/file.txt")).thenReturn(RegularFileType.TEXT);
        when(mockFileSystem.readString("/example/path/file.txt")).thenReturn("Hello, World!");

        // Act
        long length = fileHandle.length();

        // Assert
        assertEquals(13, length);
    }
}
