package com.pixelgamelibrary.api.files.command;

import com.pixelgamelibrary.api.files.command.StorageCommandResult;
import com.pixelgamelibrary.api.files.command.StorageCommandLine;
import com.pixelgamelibrary.api.files.Storage;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StorageCommandLineTest {

    private StorageCommandLine commandLine;
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        mockStorage = mock(Storage.class);
        when(mockStorage.printWorkingDirectory()).thenReturn("/mock/path");
        when(mockStorage.list()).thenReturn(Arrays.asList("file1.txt", "file2.txt"));
        commandLine = new StorageCommandLine("user", "hostname", mockStorage);
    }

    @Test
    void testGetCommandLineStart() {
        assertEquals("user@hostname:/mock/path$ ", commandLine.getCommandLineStart());
    }

    @Test
    void testExtractArgument() {
        String result = commandLine.extractArgument("cmd arg1 arg2", 1);
        assertEquals("arg2", result);
    }

    @Test
    void testExecuteDateCommand() {
        StorageCommandResult result = commandLine.execute("date");
        assertEquals(new Date().toString(), result.getOutput().trim());
    }

    @Test
    void testExecuteWhoamiCommand() {
        StorageCommandResult result = commandLine.execute("whoami");
        assertEquals("user", result.getOutput().trim());
    }

    @Test
    void testExecuteUptimeCommand() {
        // Assuming uptime command result should be similar to "HH:MM up X minutes, 1 user"
        String expectedOutput = new Date().toString().substring(11, 19) + " up "
                + (System.nanoTime() - commandLine.startNanoTime) / 1000000000L / 60L
                + " minutes, 1 user";
        StorageCommandResult result = commandLine.execute("uptime");
        assertEquals(expectedOutput, result.getOutput().trim());
    }

    @Test
    void testExecuteHostnameCommand() {
        StorageCommandResult result = commandLine.execute("hostname");
        assertEquals("hostname", result.getOutput().trim());
    }

    @Test
    void testExecuteTestCommand() {
        StorageCommandResult result = commandLine.execute("test arg1");
        assertEquals("arg1", result.getOutput().trim());
    }

    @Test
    void testExecuteUnameCommand() {
        StorageCommandResult result = commandLine.execute("uname -a");
        assertEquals("LinuxBashCommandLinePartialEmulation hostname 0.0.0 ("
                + new Date().toString() + ")", result.getOutput().trim());
    }

    @Test
    void testExecuteLsCommand() {
        StorageCommandResult result = commandLine.execute("ls");
        assertEquals("file1.txt\nfile2.txt", result.getOutput().trim());
    }

    @Test
    void testExecutePwdCommand() {
        StorageCommandResult result = commandLine.execute("pwd");
        assertEquals("/mock/path", result.getOutput().trim());
    }

    @Test
    void testExecuteDepthCommand() {
        when(mockStorage.depth()).thenReturn(4);
        StorageCommandResult result = commandLine.execute("depth");
        assertEquals(4, Integer.valueOf(result.getOutput().trim()));
    }

    @Test
    void testExecuteMkdirCommand() {
        when(mockStorage.createDirectories(any())).thenReturn("");
        StorageCommandResult result = commandLine.execute("mkdir newDir");
        assertEquals("New directory was successfully created", result.getOutput().trim());
    }

    @Test
    void testExecuteCdCommand() {
        when(mockStorage.changeDirectory(any())).thenReturn("");
        StorageCommandResult result = commandLine.execute("cd newDir");
        assertEquals("Changing working directory was successfully created", result.getOutput().trim());
    }

    @Test
    void testExecuteTouchCommand() {
        when(mockStorage.touch(any())).thenReturn("");
        StorageCommandResult result = commandLine.execute("touch newFile.txt");
        assertEquals("New file was successfully created", result.getOutput().trim());
    }

    @Test
    void testExecuteReadtextCommand() {
        when(mockStorage.readString(any())).thenReturn("file content");
        StorageCommandResult result = commandLine.execute("readtext file.txt");
        assertEquals("Text file was successfully loaded\n\nfile content", result.getOutput().trim());
    }

    @Test
    void testExecuteSavetextCommand() {
        when(mockStorage.writeString(any(), any())).thenReturn("");
        StorageCommandResult result = commandLine.execute("savetext file.txt content");
        assertEquals("Text file was successfully saved", result.getOutput().trim());
    }

    @Test
    void testExecuteDebugCommand() {
        when(mockStorage.debug()).thenReturn("debug info");
        StorageCommandResult result = commandLine.execute("debug");
        assertEquals("debug info", result.getOutput().trim());
    }

    @Test
    void testExecuteExitCommand() {
        StorageCommandResult result = commandLine.execute("exit");
        assertTrue(commandLine.isExited());
        assertEquals("Exited", result.getOutput().trim());
    }

    @Test
    void testExecuteUnsupportedCommand() {
        StorageCommandResult result = commandLine.execute("unsupported");
        assertEquals("Unsupported command: unsupported", result.getOutput().trim());
    }
}
