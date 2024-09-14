package com.pixelgamelibrary.api.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileHandleImplTest {

    private Storage mockStorage;
    private FileHandleImpl fileHandle;

    @BeforeEach
    public void setUp() {
        mockStorage = mock(Storage.class);
        fileHandle = new FileHandleImpl(mockStorage, "/example/path/file.txt");
    }

    @Test
    public void testType() {
        // Arrange
        when(mockStorage.type("/example/path/file.txt")).thenReturn(FileType.FILE);

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
        when(mockStorage.list("/example/path/file.txt")).thenReturn(Arrays.asList("child1", "child2"));

        // Act
        List<FileHandle> files = fileHandle.list();

        // Assert
        assertEquals(2, files.size());
        assertEquals("child1", files.get(0).name());
        assertEquals("child2", files.get(1).name());
    }

    @Test
    public void testChild() {
        // Act
        FileHandle child = fileHandle.child("child.txt");

        // Assert
        assertEquals("/example/path/file.txt/child.txt", child.path());
    }

    @Test
    public void testSibling() {
        // Act
        FileHandle sibling = fileHandle.sibling("sibling.txt");

        // Assert
        assertEquals("/example/path/sibling.txt", sibling.path());
    }

    @Test
    public void testParent() {
        // Act
        FileHandle parent = fileHandle.parent();

        // Assert
        assertEquals("/example/path", parent.path());
    }

    @Test
    public void testMkdir() {
        // Arrange
        when(mockStorage.createDirectory("/example/path/file.txt")).thenReturn("");

        // Act
        boolean result = fileHandle.mkdir();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testExists() {
        // Arrange
        when(mockStorage.exists("/example/path/file.txt")).thenReturn(true);

        // Act
        boolean result = fileHandle.exists();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testDelete() {
        // Arrange
        when(mockStorage.remove("/example/path/file.txt")).thenReturn(true);

        // Act
        boolean result = fileHandle.delete();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCopyTo() {
        // Arrange
        FileHandleImpl destination = new FileHandleImpl(mockStorage, "/destination/path");
        when(mockStorage.copy("/example/path/file.txt", "/destination/path")).thenReturn("");

        // Act
        boolean result = fileHandle.copyTo(destination);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testMoveTo() {
        // Arrange
        FileHandleImpl destination = new FileHandleImpl(mockStorage, "/destination/path");
        when(mockStorage.move("/example/path/file.txt", "/destination/path")).thenReturn("");

        // Act
        boolean result = fileHandle.moveTo(destination);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testLength() {
        // Arrange
        when(mockStorage.getRegularFileType("/example/path/file.txt")).thenReturn(RegularFileType.TEXT);
        when(mockStorage.readString("/example/path/file.txt")).thenReturn("Hello, World!");

        // Act
        long length = fileHandle.length();

        // Assert
        assertEquals(13, length);
    }
}
