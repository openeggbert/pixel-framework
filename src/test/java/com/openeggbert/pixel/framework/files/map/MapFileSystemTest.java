package com.openeggbert.pixel.framework.files.map;

import com.openeggbert.pixel.framework.files.map.MapFileSystem;
import com.openeggbert.pixel.framework.files.map.SimpleMap;
import com.openeggbert.pixel.framework.Pixel;
import com.openeggbert.pixel.framework.Platform;
import com.openeggbert.pixel.framework.interfaces.PixelBackend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.openeggbert.pixel.framework.interfaces.Files;
import com.openeggbert.pixel.framework.interfaces.Audio;
import com.openeggbert.pixel.framework.interfaces.Graphics;
import com.openeggbert.pixel.framework.interfaces.Input;
import com.openeggbert.pixel.framework.interfaces.Internal;
import com.openeggbert.pixel.framework.interfaces.Net;
import com.openeggbert.pixel.framework.interfaces.Utils;
import com.openeggbert.pixel.framework.interfaces.App;
import com.openeggbert.pixel.framework.game.Game;
import com.openeggbert.pixel.framework.PixelFeature;
import com.openeggbert.pixel.framework.app.ClipBoard;
import com.openeggbert.pixel.framework.app.LogLevel;
import com.openeggbert.pixel.framework.app.Preferences;

// Tests for MapFileSystem class
public class MapFileSystemTest {

    private SimpleMap mockMap;
    private MapFileSystem fs;

    @org.junit.jupiter.api.BeforeAll public static void setupStart() {
        
        PixelBackend dummyPixelBackend = new PixelBackend() {
            @Override
            public App app() {
                return new App() {
                    @Override
                    public Platform getPlatform() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void exit() {
                    }

                    @Override
                    public void log(String msg) {
                        
                    }

                    @Override
                    public void error(String msg) {
                        
                    }

                    @Override
                    public void debug(String msg) {
                        
                    }

                    @Override
                    public void warn(String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setAppName(String appName) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public String getAppName() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean isAppNameSet() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setGame(Game game) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public Game getGame() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setLogLevel(LogLevel logLevel) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public Preferences getPreferences(String preferencesName) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean isFeatureEnabled(PixelFeature feature) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean isMobileDevice() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void runLater(Runnable runnable) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public ClipBoard getClipBoard() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean isFeatureEnabled(String feature) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void fatal(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void error(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void log(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void warn(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void debug(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void trace(String tag, String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                
            };
            }

            @Override
            public Graphics graphics() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Audio audio() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Input input() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Net net() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Files files() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Utils utils() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public Internal internal() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        Pixel.initBackend(dummyPixelBackend);
    }
    @BeforeEach
    public void setup() {
        mockMap = Mockito.mock(SimpleMap.class); // Mocking the SimpleMap class
        fs = new MapFileSystem(mockMap); // Initialize MapFileSystem with the mocked map
    }

    @Test
    public void testMkdirSuccess() {
        when(mockMap.contains("/")).thenReturn(true); // Simulate no directory exists
        when(mockMap.contains("/newDir")).thenReturn(false); // Simulate no directory exists

        String result = fs.createDirectory("/newDir");

        assertEquals("", result); // Success should return an empty string
        verify(mockMap).putString("/newDir", "DIRECTORY::::::::");
    }

    @Test
    public void testMkdirAlreadyExists() {
        when(mockMap.contains("/")).thenReturn(true); // Root irectory already exists
        when(mockMap.contains("/newDir")).thenReturn(true); // Directory already exists

        String result = fs.createDirectory("/newDir");

        assertEquals("Cannot create new directory, because path already exists: /newDir", result);
    }

    @Test
    public void testCdChangeDirectorySuccess() {
        when(mockMap.contains("/")).thenReturn(true);
        when(mockMap.contains("/newDir")).thenReturn(true);
        when(mockMap.getString("/")).thenReturn("DIRECTORY::::::::");
        when(mockMap.getString("/newDir")).thenReturn("DIRECTORY::::::::");

        String result = fs.changeDirectory("/newDir");

        assertEquals("", result); // Success should return an empty string
    }

    @Test
    public void testCdPathDoesNotExist() {
        when(mockMap.contains("/nonExistent")).thenReturn(false);

        String result = fs.changeDirectory("/nonExistent");

        assertEquals("Path does not exist: /nonExistent", result);
    }

    @Test
    public void testTouchCreateFileSuccess() {
        when(mockMap.contains("/")).thenReturn(true); // Root exists
        when(mockMap.getString("/")).thenReturn("DIRECTORY::::::::");

        String result = fs.touch("/newFile.txt", "Test content");

        assertEquals("", result); // Success should return an empty string
        verify(mockMap).putString("/newFile.txt", "FILE::::::::Test content");
    }

    @Test
    public void testTouchFileAlreadyExists() {
        when(mockMap.contains("/")).thenReturn(true); // File already exists
        when(mockMap.contains("/newFile.txt")).thenReturn(true); // File already exists

        String result = fs.touch("/newFile.txt", "Test content");

        assertEquals("Cannot create new file, because path already exists: /newFile.txt", result);
    }

    @Test
    public void testReadTextFile() {
        when(mockMap.contains("/file.txt")).thenReturn(true);
        when(mockMap.getString("/file.txt")).thenReturn("FILE::::::::Hello World");

        String content = fs.readString("/file.txt");

        assertEquals("Hello World", content);
    }

    @Test
    public void testReadTextFileDoesNotExist() {
        when(mockMap.contains("/file.txt")).thenReturn(false);

        String content = fs.readString("/file.txt");

        assertNull(content);
    }

    @Test
    public void testRmFile() {
        when(mockMap.contains("/file.txt")).thenReturn(true);
        when(mockMap.getString("/file.txt")).thenReturn("FILE::::::::Hello World");

        boolean result = fs.remove("/file.txt");

        assertTrue(result); // File successfully removed
        verify(mockMap).remove("/file.txt");
    }

    @Test
    public void testRmFileDoesNotExist() {
        when(mockMap.contains("/file.txt")).thenReturn(false);

        boolean result = fs.remove("/file.txt");

        assertFalse(result); // File does not exist, so removal fails
    }

    @Test
    public void testDepth() {
        assertEquals(0, fs.depth("/"));
        assertEquals(1, fs.depth("/dir"));
        assertEquals(2, fs.depth("/dir/subdir"));
    }

    @Test
    public void testLs() {
        when(mockMap.keyList()).thenReturn(List.of("/dir/file1", "/dir/file2", "/dir/subdir/file3"));

        List<String> files = fs.list("/dir");

        assertEquals(2, files.size());
        assertTrue(files.contains("/dir/file1"));
        assertTrue(files.contains("/dir/file2"));
    }
}
