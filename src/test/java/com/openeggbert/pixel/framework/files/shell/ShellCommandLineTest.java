package com.openeggbert.pixel.framework.files.shell;

import com.openeggbert.pixel.framework.files.shell.ShellCommandResult;
import com.openeggbert.pixel.framework.files.shell.ShellCommandLine;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.openeggbert.pixel.framework.files.FileSystem;

class ShellCommandLineTest {

    private ShellCommandLine commandLine;
    private FileSystem mockFileSystem;

    @BeforeEach
    void setUp() {
        mockFileSystem = mock(FileSystem.class);
        when(mockFileSystem.printWorkingDirectory()).thenReturn("/mock/path");
        when(mockFileSystem.list()).thenReturn(Arrays.asList("file1.txt", "file2.txt"));
        commandLine = new ShellCommandLine("user", "hostname", mockFileSystem);
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
        ShellCommandResult result = commandLine.execute("date");
        assertEquals(new Date().toString(), result.getOutput().trim());
    }

    @Test
    void testExecuteWhoamiCommand() {
        ShellCommandResult result = commandLine.execute("whoami");
        assertEquals("user", result.getOutput().trim());
    }

    @Test
    void testExecuteUptimeCommand() {
        // Assuming uptime command result should be similar to "HH:MM up X minutes, 1 user"
        String expectedOutput = new Date().toString().substring(11, 19) + " up "
                + (System.nanoTime() - commandLine.startNanoTime) / 1000000000L / 60L
                + " minutes, 1 user";
        ShellCommandResult result = commandLine.execute("uptime");
        assertEquals(expectedOutput, result.getOutput().trim());
    }

    @Test
    void testExecuteHostnameCommand() {
        ShellCommandResult result = commandLine.execute("hostname");
        assertEquals("hostname", result.getOutput().trim());
    }

    @Test
    void testExecuteTestCommand() {
        ShellCommandResult result = commandLine.execute("test arg1");
        assertEquals("arg1", result.getOutput().trim());
    }

    @Test
    void testExecuteUnameCommand() {
        ShellCommandResult result = commandLine.execute("uname -a");
        assertEquals("LinuxBashCommandLinePartialEmulation hostname 0.0.0 ("
                + new Date().toString() + ")", result.getOutput().trim());
    }

    @Test
    void testExecuteLsCommand() {
        ShellCommandResult result = commandLine.execute("ls");
        assertEquals("file1.txt\nfile2.txt", result.getOutput().trim());
    }

    @Test
    void testExecutePwdCommand() {
        ShellCommandResult result = commandLine.execute("pwd");
        assertEquals("/mock/path", result.getOutput().trim());
    }

    @Test
    void testExecuteDepthCommand() {
        when(mockFileSystem.depth()).thenReturn(4);
        ShellCommandResult result = commandLine.execute("depth");
        assertEquals(4, Integer.valueOf(result.getOutput().trim()));
    }

    @Test
    void testExecuteMkdirCommand() {
        when(mockFileSystem.createDirectories(any())).thenReturn("");
        ShellCommandResult result = commandLine.execute("mkdir newDir");
        assertEquals("New directory was successfully created", result.getOutput().trim());
    }

    @Test
    void testExecuteCdCommand() {
        when(mockFileSystem.changeDirectory(any())).thenReturn("");
        ShellCommandResult result = commandLine.execute("cd newDir");
        assertEquals("Changing working directory was successfully created", result.getOutput().trim());
    }

    @Test
    void testExecuteTouchCommand() {
        when(mockFileSystem.touch(any())).thenReturn("");
        ShellCommandResult result = commandLine.execute("touch newFile.txt");
        assertEquals("New file was successfully created", result.getOutput().trim());
    }

    @Test
    void testExecuteReadtextCommand() {
        when(mockFileSystem.readString(any())).thenReturn("file content");
        ShellCommandResult result = commandLine.execute("readtext file.txt");
        assertEquals("Text file was successfully loaded\n\nfile content", result.getOutput().trim());
    }

    @Test
    void testExecuteSavetextCommand() {
        when(mockFileSystem.writeString(any(), any())).thenReturn("");
        ShellCommandResult result = commandLine.execute("savetext file.txt content");
        assertEquals("Text file was successfully saved", result.getOutput().trim());
    }

    @Test
    void testExecuteDebugCommand() {
        when(mockFileSystem.debug()).thenReturn("debug info");
        ShellCommandResult result = commandLine.execute("debug");
        assertEquals("debug info", result.getOutput().trim());
    }

    @Test
    void testExecuteExitCommand() {
        ShellCommandResult result = commandLine.execute("exit");
        assertTrue(commandLine.isExited());
        assertEquals("Exited", result.getOutput().trim());
    }

    @Test
    void testExecuteUnsupportedCommand() {
        ShellCommandResult result = commandLine.execute("unsupported");
        assertEquals("Unsupported command: unsupported", result.getOutput().trim());
    }
}
